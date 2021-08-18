package modelo.entidade.mapa;

import java.util.ArrayList;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.geojson.Point;

import modelo.excecao.mapa.StatusInvalidoException;

public class Ponto {

	private int idPonto; 
	private Point LongLatAlt;

	public Ponto(double latitude, double longitude) throws StatusInvalidoException {
		this.setLatitude(latitude);
		this.setLongitude(longitude);
		this.setAltitude();
	}
	
	public Ponto() {}
	
	public static Ponto informatLocal(String local) throws  StatusInvalidoException{
		String localParaURL = local.replaceAll(" ", "%20");
		Client client = ClientBuilder.newClient();
		Response response = client.target(
				"https://api.openrouteservice.org/geocode/search?api_key=5b3ce3597851110001cf624839b64a140f534a82a4750d447a4df110&text="
						+ localParaURL)
				.request(MediaType.TEXT_PLAIN_TYPE)
				.header("Accept", "application/json, application/geo+json, application/gpx+xml, img/png; charset=utf-8")
				.get();
		
		if (response.getStatus() == 4001) {
			throw new StatusInvalidoException("Erro de valor no paremetro");
		}
		else if (response.getStatus() == 4002) {
			throw new StatusInvalidoException("CabeÃ§alhos de HTTP errados");
		}
		else if (response.getStatus() == 4003) {
			throw new StatusInvalidoException("Problemas ao prover a geometria");
		}
		else if (response.getStatus() == 4004) {
			throw new StatusInvalidoException("Excedeu o nÃºmero de vÃ©rtices permitidos");
		}
		else if (response.getStatus() != 200) {
			throw new StatusInvalidoException("Ocoreu um erro no requrimento da API");
		}
//		 tratar para devolover latitude e longitude
//		 return Ponto localTratado = new Ponto(latitude, longitude);
		
		return new Ponto(0,0);
	}

	public Point getLongLatAlt() {
		return LongLatAlt;
	}

	public void setLongLatAlt(Point ponto) {
		this.LongLatAlt = ponto;
	}

	public int getAltitude() {
		return (int) getLongLatAlt().getCoordinates().getAltitude();
	}

	private void setAltitude() throws  StatusInvalidoException {

		Client client = ClientBuilder.newClient();
		Entity<String> payload = Entity.json("{\"format_in\":\"point\",\"geometry\":"+this.TransformarVetorEmString()+"}");
		Response response = client.target("https://api.openrouteservice.org/elevation/point")
		  .request()
		  .header("Authorization", "5b3ce3597851110001cf624839b64a140f534a82a4750d447a4df110")
		  .header("Accept", "application/json, application/geo+json, application/gpx+xml, img/png; charset=utf-8")
		  .header("Content-Type", "application/json; charset=utf-8")
		  .post(payload);
		
		if (response.getStatus() == 4001) {
			throw new StatusInvalidoException("Erro de valor no paremetro");
		}
		else if (response.getStatus() == 4002) {
			throw new StatusInvalidoException("CabeÃ§alhos de HTTP errados");
		}
		else if (response.getStatus() == 4003) {
			throw new StatusInvalidoException("Problemas ao prover a geometria");
		}
		else if (response.getStatus() == 4004) {
			throw new StatusInvalidoException("Excedeu o nÃºmero de vÃ©rtices permitidos");
		}
		else if (response.getStatus() != 200) {
			throw new StatusInvalidoException("Ocoreu um erro no requrimento da API");
		}
		
		//tratamento GeoJSON
		
		this.getLongLatAlt().getCoordinates().setAltitude(0);
		
	}
	
	public void setId(int id) {
		this.idPonto = id;
	}
	
	public int getId() {
		return idPonto;
	}

	private void setLatitude(double latitude) {
		this.getLongLatAlt().getCoordinates().setLatitude(latitude);
	}

	public double getLatitude() {
		return getLongLatAlt().getCoordinates().getLatitude();
	}

	private void setLongitude(double longitude) {
		this.getLongLatAlt().getCoordinates().setLongitude(longitude);;
	}

	public double getLongitude() {
		return getLongLatAlt().getCoordinates().getLongitude();
	}

	public ArrayList<Double> transformarPontoEmVetor() {
		ArrayList<Double> pontoVetro = new ArrayList<Double>(2);
		pontoVetro.add(this.getLongitude());
		pontoVetro.add(this.getLatitude());
		return pontoVetro;
	}

	public ArrayList<Double> transformarPontoEmVetorComElevacao() {
		ArrayList<Double> pontoVetro = new ArrayList<Double>(3);
		pontoVetro.add(this.getLongitude());
		pontoVetro.add(this.getLatitude());
		pontoVetro.add((double)this.getAltitude());
		return pontoVetro;
	}
	
	public String TransformarVetorEmString() {
		return transformarPontoEmVetor().toString();
	}
	
	public String TransformarVetorComElevacaoEmString() {
		return transformarPontoEmVetorComElevacao().toString();
	}

}
