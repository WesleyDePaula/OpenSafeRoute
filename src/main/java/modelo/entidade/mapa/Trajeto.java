package model.entities.map;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.enumeration.map.MeioDeTransporte;

public class Trajeto {

	private Ponto inicio;
	private List<Ponto> pontos;
	private Ponto chegada;
	private MeioDeTransporte transporteUsado;

	public Trajeto(ponto inicio,Ponto chegada,MeioDeTransporte transporteUsado) {
		this.setInicio(inicio);
		this.setChegada(chegada);
		this.setTransporteUsado(transporteUsado);
		this.setPontos();
		
	}

	public Ponto informatLocal(String local) {
//		String localParaURL = local.replaceAll(" ", "%20");
//		Client client = ClientBuilder.newClient();
//		Response response = client.target(
//				"https://api.openrouteservice.org/geocode/search?api_key=5b3ce3597851110001cf624839b64a140f534a82a4750d447a4df110&text="
//						+ localParaURL)
//				.request(MediaType.TEXT_PLAIN_TYPE)
//				.header("Accept", "application/json, application/geo+json, application/gpx+xml, img/png; charset=utf-8")
//				.get();
		// tratar para devolover latitude e longitude
		// return Ponto localTratado = new Ponto(latitude, longitude);

		return new Ponto(0, 0);
	}

	public Ponto getInicio() {
		return inicio;
	}

	public void setInicio(String inicio) {
		this.inicio = this.informatLocal(inicio);
	}

	public List<Ponto> getPontos() {
		return pontos;
	}

	public void setPontos() {
//		Client client = ClientBuilder.newClient();
//		Entity<String> payload = Entity.json("{\"coordinates\":["","",""],\"elevation\":\"true\",\"extra_info\":[\"roadaccessrestrictions\"]}");
//		Response response = client.target("https://api.openrouteservice.org/v2/directions/"+getTransporteUsado().getDescricao()+"/geojson")
//		  .request()
//		  .header("Authorization", "5b3ce3597851110001cf624839b64a140f534a82a4750d447a4df110")
//		  .header("Accept", "application/json, application/geo+json, application/gpx+xml, img/png; charset=utf-8")
//		  .header("Content-Type", "application/json; charset=utf-8")
//		  .post(payload);

		// Tratar GeoJSON

	}

	public Ponto getChegada() {
		return chegada;
	}

	public void setChegada(String chegada) {
		this.chegada = this.informatLocal(chegada);
	}

	public MeioDeTransporte getTransporteUsado() {
		return transporteUsado;
	}

	public void setTransporteUsado(MeioDeTransporte transporteUsado) {
		this.transporteUsado = transporteUsado;
	}

	public String[][] gerarLinhaParaDefinirTrajeto() {
//		ArrayList<ArrayList<String>> linhaDePontos = new ArrayList<ArrayList<String>>();
//		Scanner sc = new Scanner(System.in);
//
//		System.out.print("Inicio: ");
//		String local = sc.next();
//		linhaDePontos.add(this.setInicio(local));

		return null;

	}

}
