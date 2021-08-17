package modelo.entidade.mapa;

import org.geojson.LineString;

import modelo.enumeracao.mapa.MeioDeTransporte;
import modelo.excecao.mapa.StatusInvalidoException;


public class Trajeto {

	private Ponto inicio;
	private LineString pontos;
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

	public void setChegada(String chegada) throws StatusInvalidoException {
		this.chegada = Ponto.informatLocal(chegada);
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
