package modelo.entidade.mapa;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import org.geojson.LineString;

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
	
	@Column(name = "pontos_trajeto" ) //FALTA FAZER O TIPO DO ATRIBUTO
	private LineString pontos;
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_chegada_trajeto")
	private Ponto chegada;
	
	private MeioDeTransporte transporteUsado;

	public Trajeto(String inicio, String chegada,MeioDeTransporte transporteUsado) throws StatusInvalidoException {
		this.setInicio(inicio);
		this.setChegada(chegada);
		this.setTransporteUsado(transporteUsado);
		this.setPontos();
		
	}


	public Ponto getInicio() {
		return inicio;
	}

	public void setInicio(String inicio) throws StatusInvalidoException {
		this.inicio = Ponto.informatLocal(inicio);
	}

	public LineString getPontos() {
		return pontos;
	}

	public void setPontos() {
		Client client = ClientBuilder.newClient();
		Entity<String> payload = Entity.json("{\"coordinates\":["+getInicio().TransformarVetorEmString()+","+getChegada().TransformarVetorEmString()+"],\"elevation\":\"true\",\"extra_info\":[\"roadaccessrestrictions\"]}");
		Response response = client.target("https://api.openrouteservice.org/v2/directions/"+getTransporteUsado().getDescricao()+"/geojson")
		  .request()
		  .header("Authorization", "5b3ce3597851110001cf624839b64a140f534a82a4750d447a4df110")
		  .header("Accept", "application/json, application/geo+json, application/gpx+xml, img/png; charset=utf-8")
		  .header("Content-Type", "application/json; charset=utf-8")
		  .post(payload);

		// Tratar GeoJSON

	}

	public Ponto getChegada() {
		return chegada;
	}

	public void setChegada(String chegada) throws StatusInvalidoException {
		this.chegada = Ponto.informatLocal(chegada);
	}

	public MeioDeTransporte getTransporteUsado() {
		return transporteUsado;
	}

	public void setTransporteUsado(MeioDeTransporte transporteUsado) {
		this.transporteUsado = transporteUsado;
	}

	private ArrayList<PontoAvaliado> evitarPontos (ArrayList<PontoAvaliado> pontos){
		ArrayList<PontoAvaliado> evitar = new ArrayList<>();
		
		for (int nota = 10; nota >= 0; nota--){

			for (int i = 1; i < pontos.size(); i++){
				if (pontos.get(i).getMediaDeAvaliacao() < nota){
					evitar.add(pontos.get(i));
				}
			}

			try {
				
				Client client = ClientBuilder.newClient();
				Entity<String> payload = Entity.json("{\"coordinates\":["+getInicio().TransformarVetorEmString()+","+getChegada().TransformarVetorEmString()+"],\"elevation\":\"true\",\"extra_info\":[\"roadaccessrestrictions\"]}");
				Response response = client.target("https://api.openrouteservice.org/v2/directions/"+getTransporteUsado().getDescricao()+"/geojson")
				.request()
				.header("Authorization", "5b3ce3597851110001cf624839b64a140f534a82a4750d447a4df110")
				.header("Accept", "application/json, application/geo+json, application/gpx+xml, img/png; charset=utf-8")
				.header("Content-Type", "application/json; charset=utf-8")
				.post(payload);

				//evitar Pontos na varavel evitar 

				if (response.getStatus() != 200) {
					throw new StatusInvalidoException("Ocoreu um erro no requrimento da API");
				}

			} catch (StatusInvalidoException e) {
				evitar.removeAll(evitar);
			}
		}
		return evitar;
	}

}
