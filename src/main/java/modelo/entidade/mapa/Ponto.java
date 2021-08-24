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

	public Ponto(double latitude, double longitude,double altitude)
			throws StatusInvalidoException, JsonMappingException, JsonProcessingException {
		this.setLatitude(latitude);
		this.setLongitude(longitude);
		this.setAltitude(altitude);
	}

	public Ponto() {
	}

	public static Ponto informatLocal(String local)
			throws JsonMappingException, JsonProcessingException, StatusInvalidoException {
		return ConsultaPonto.informatLocal(local);
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

	public void setAltitude(double altitude) {

		this.LongLatAlt.getCoordinates().setAltitude(altitude);

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

	public ArrayList<Double> transformarPontoEmVetorComElevacao() {
		ArrayList<Double> pontoVetro = new ArrayList<Double>(3);
		pontoVetro.add(this.getLongitude());
		pontoVetro.add(this.getLatitude());
		pontoVetro.add((double) this.getAltitude());
		return pontoVetro;
	}

	public String TransformarVetorEmString() {
		return transformarPontoEmVetor().toString();
	}

	public String TransformarVetorComElevacaoEmString() {
		return transformarPontoEmVetorComElevacao().toString();
	}

}
