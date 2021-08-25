package modelo.entidade.mapa;

import java.io.Serializable;

import java.io.IOException;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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

import modelo.consultaAPI.ConsultaTrajeto;
import modelo.enumeracao.mapa.MeioDeTransporte;
import modelo.excecao.mapa.StatusInvalidoException;

 
@Entity
@Table(name = "trajeto")
public class Trajeto implements Serializable {

	private static final long serialVersionUID = 1L;

public class Trajeto {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_trajeto")
	private int idTrajeto;


	@ManyToOne
	@JoinColumn(name = "id_partida_trajeto")
	private Ponto inicio;

	@JoinColumn(name = "pontos_trajeto") // FALTA FAZER O TIPO DO ATRIBUTO
	private LineString pontos;

	@ManyToOne

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
		this.criarLineString(inicio, chegada, transporteUsado);
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
		this.criarLineString(inicio, chegada, transporteUsado);
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

	public void setPontos(LineString pontos) {
		this.pontos = pontos;
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


	public void criarLineString(Ponto inicio, Ponto chegada, MeioDeTransporte transporteUsado)
			throws JsonParseException, org.codehaus.jackson.map.JsonMappingException, IOException {
		ConsultaTrajeto.criarLineString(inicio, chegada, transporteUsado);
	}

}}
