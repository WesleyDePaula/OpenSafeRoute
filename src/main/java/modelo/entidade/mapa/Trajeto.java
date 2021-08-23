package modelo.entidade.mapa;

import java.io.IOException;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.ObjectMapper;
import org.geojson.FeatureCollection;
import org.geojson.GeoJsonObject;
import org.geojson.LineString;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import modelo.enumeracao.mapa.MeioDeTransporte;
import modelo.excecao.mapa.StatusInvalidoException;

public class Trajeto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_trajeto")
	private int idTrajeto;

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_partida_trajeto")
	private Ponto inicio;

	@Column(name = "pontos_trajeto") // FALTA FAZER O TIPO DO ATRIBUTO
	private LineString pontos;

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_chegada_trajeto")
	private Ponto chegada;

	private MeioDeTransporte transporteUsado;

	public Trajeto() {
	}

	public Trajeto(int id, Ponto inicio, Ponto chegada, MeioDeTransporte transporteUsado)
			throws StatusInvalidoException, JsonParseException, org.codehaus.jackson.map.JsonMappingException,
			IOException {
		this.setIdTrajeto(id);
		this.setInicio(inicio);
		this.setChegada(chegada);
		this.setTransporteUsado(transporteUsado);
		this.setPontos();
	}

	public Trajeto(int id, String inicio, String chegada, MeioDeTransporte transporteUsado)
			throws JsonParseException, org.codehaus.jackson.map.JsonMappingException, JsonMappingException,
			JsonProcessingException, StatusInvalidoException, IOException {
		this(id, Ponto.informatLocal(inicio), Ponto.informatLocal(chegada), transporteUsado);
	}

	public Trajeto(Ponto inicio, Ponto chegada, MeioDeTransporte transporteUsado) throws StatusInvalidoException,
			JsonParseException, org.codehaus.jackson.map.JsonMappingException, IOException {
		this.setInicio(inicio);
		this.setChegada(chegada);
		this.setTransporteUsado(transporteUsado);
		this.setPontos();
	}

	public Trajeto(String inicio, String chegada, MeioDeTransporte transporteUsado)
			throws JsonParseException, org.codehaus.jackson.map.JsonMappingException, JsonMappingException,
			JsonProcessingException, StatusInvalidoException, IOException {
		this(Ponto.informatLocal(inicio), Ponto.informatLocal(chegada), transporteUsado);
	}

	public int getIdTrajeto() {
		return idTrajeto;
	}

	public void setIdTrajeto(int idTrajeto) {
		this.idTrajeto = idTrajeto;
	}

	public Ponto getInicio() {
		return inicio;
	}

	public void setInicio(String inicio) throws StatusInvalidoException, JsonMappingException, JsonProcessingException {
		this.inicio = Ponto.informatLocal(inicio);
	}

	private void setInicio(Ponto inicio) {
		this.inicio = inicio;
	}

	public LineString getPontos() {
		return pontos;
	}

	public void setPontos() throws JsonParseException, org.codehaus.jackson.map.JsonMappingException, IOException {
		Client client = ClientBuilder.newClient();
		Entity<String> payload = Entity.json("{\"coordinates\":[" + getInicio().TransformarVetorEmString() + ","
				+ getChegada().TransformarVetorEmString()
				+ "],\"elevation\":\"true\",\"extra_info\":[\"steepness\",\"waycategory\",\"waytype\",\"tollways\"]");
		Response response = client
				.target("https://api.openrouteservice.org/v2/directions/" + getTransporteUsado().getDescricao()
						+ "/geojson")
				.request().header("Authorization", "5b3ce3597851110001cf624839b64a140f534a82a4750d447a4df110")
				.header("Accept", "application/json, application/geo+json, application/gpx+xml, img/png; charset=utf-8")
				.header("Content-Type", "application/json; charset=utf-8").post(payload);

		GeoJsonObject object = new ObjectMapper().readValue(response.readEntity(String.class), GeoJsonObject.class);
		this.pontos = (LineString) ((FeatureCollection) object).getFeatures().get(0).getGeometry();

	}

	public Ponto getChegada() {
		return chegada;
	}

	public void setChegada(String chegada)
			throws StatusInvalidoException, JsonMappingException, JsonProcessingException {
		this.chegada = Ponto.informatLocal(chegada);
	}

	private void setChegada(Ponto chegada) {
		this.chegada = chegada;
	}

	public MeioDeTransporte getTransporteUsado() {
		return transporteUsado;
	}

	public void setTransporteUsado(MeioDeTransporte transporteUsado) {
		this.transporteUsado = transporteUsado;
	}

	private ArrayList<ArrayList<ArrayList<PontoAvaliado>>> evitarPontos(ArrayList<PontoAvaliado> pontos) {
		ArrayList<ArrayList<ArrayList<PontoAvaliado>>> evitar = new ArrayList<ArrayList<ArrayList<PontoAvaliado>>>();

		int contador = 0;
		
		for (int nota = 10; nota >= 0; nota--) {

			for (int i = 1; i < pontos.size(); i++) {
				if (pontos.get(i).getMediaDeAvaliacao() < nota) {
					evitar.get(contador).get(contador).add(pontos.get(i));
					contador++;
				}
			}

			try {

				Client client = ClientBuilder.newClient();
				Entity<String> payload = Entity.json("{\"coordinates\":[" + getInicio().TransformarVetorEmString() + ","
						+ getChegada().TransformarVetorEmString()
						+ "],\"elevation\":\"true\",\"extra_info\":[\"steepness\",\"waycategory\",\"waytype\",\"tollways\"],\"options\":{\"avoid_polygons\":{\"type\":\"MultiPolygon\",\"coordinates\":"
						+ evitar + "}}");
				Response response = client
						.target("https://api.openrouteservice.org/v2/directions/" + getTransporteUsado().getDescricao()
								+ "/geojson")
						.request().header("Authorization", "5b3ce3597851110001cf624839b64a140f534a82a4750d447a4df110")
						.header("Accept",
								"application/json, application/geo+json, application/gpx+xml, img/png; charset=utf-8")
						.header("Content-Type", "application/json; charset=utf-8").post(payload);

				// evitar Pontos na varavel evitar

				if (response.getStatus() != 200) {
					throw new StatusInvalidoException("Ocoreu um erro no requrimento da API");
				}

			} catch (Exception e) {
				evitar.removeAll(evitar);
			}
		}
		return evitar;
	}

}
