package controlador;

import java.util.Scanner;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import org.geojson.FeatureCollection;
import org.geojson.GeoJsonObject;
import org.geojson.Point;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import modelo.dao.Usuario.UsuarioDAO;
import modelo.dao.Usuario.UsuarioDAOImpl;
import modelo.entidade.mapa.Ponto;
import modelo.entidade.usuario.UsuarioCadastrado;
import modelo.excecao.mapa.StatusInvalidoException;
import modelo.excecao.usuario.EmailInvalidoException;
import modelo.excecao.usuario.SenhaPequenaException;
import modelo.excecao.usuario.StringVaziaException;

public class Principal {

	public static void main(String[] args) throws StringVaziaException, EmailInvalidoException, SenhaPequenaException, JsonMappingException, JsonProcessingException, StatusInvalidoException {

		try {
			
			String a = "Rua fernandes marques brinhosa, 18 blumenau";
			Ponto p = Ponto.informatLocal(a);

			System.out.println(p.getLatitude());
			System.out.println(p.getLongitude());
			
			//p.setAltitude();
			
			System.out.println(p.getAltitude());

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String a = "Rua fernandes marques brinhosa, 18 blumenau";
		Ponto p = Ponto.informatLocal(a);
		
		Client client = ClientBuilder.newClient();
		Entity<String> payload = Entity
				.json("{\"format_in\":\"point\",\"geometry\":" + p.TransformarVetorEmString() + "}");
		Response response = client.target("https://api.openrouteservice.org/elevation/point").request()
				.header("Authorization", "5b3ce3597851110001cf624839b64a140f534a82a4750d447a4df110")
				.header("Accept", "application/json, application/geo+json, application/gpx+xml, img/png; charset=utf-8")
				.header("Content-Type", "application/json; charset=utf-8").post(payload);

		if (response.getStatus() == 4001) {
			throw new StatusInvalidoException("Erro de valor no paremetro");
		} else if (response.getStatus() == 4002) {
			throw new StatusInvalidoException("CabeÃ§alhos de HTTP errados");
		} else if (response.getStatus() == 4003) {
			throw new StatusInvalidoException("Problemas ao prover a geometria");
		} else if (response.getStatus() == 4004) {
			throw new StatusInvalidoException("Excedeu o nÃºmero de vÃ©rtices permitidos");
		} else if (response.getStatus() != 200) {
			throw new StatusInvalidoException("Ocoreu um erro no requrimento da API");
		}

		System.out.println(response.readEntity(String.class));
		
		GeoJsonObject object = new ObjectMapper().readValue(response.readEntity(String.class), GeoJsonObject.class);

		//Ponto ponto = new Ponto();
		
		//System.out.println(object.getClass());

		//ponto.setLongLatAlt((Point) ((FeatureCollection) object).getFeatures().get(0).getGeometry());

		
	}

}
