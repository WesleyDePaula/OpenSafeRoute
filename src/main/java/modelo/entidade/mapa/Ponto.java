package modelo.entidade.mapa;

import java.util.ArrayList;

import org.geojson.Point;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import modelo.consultaAPI.ConsultaPonto;
import modelo.excecao.mapa.StatusInvalidoException;

public class Ponto {

	private int idPonto;
	private Point LongLatAlt;

	public Ponto(double latitude, double longitude)
			throws StatusInvalidoException, JsonMappingException, JsonProcessingException {
		this.setLatitude(latitude);
		this.setLongitude(longitude);
	}

	public Ponto() {
	}

	public static Ponto informatLocal(String local)
			throws JsonMappingException, JsonProcessingException, StatusInvalidoException {
		return ConsultaPonto.informatLocal(local);
	}

	public void setId(int id) {
		this.idPonto = id;
	}

	public int getId() {
		return idPonto;
	}

	public Point getLongLatAlt() {
		return LongLatAlt;
	}

	public void setLongLatAlt(Point ponto) {
		this.LongLatAlt = ponto;
	}


	private void setLatitude(double latitude) {
		this.getLongLatAlt().getCoordinates().setLatitude(latitude);
	}

	public double getLatitude() {
		return getLongLatAlt().getCoordinates().getLatitude();
	}

	private void setLongitude(double longitude) {
		this.getLongLatAlt().getCoordinates().setLongitude(longitude);
		;
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


	public String TransformarVetorEmString() {
		return transformarPontoEmVetor().toString();
	}

}
