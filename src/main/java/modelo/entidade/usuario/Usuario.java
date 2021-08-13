package modelo.entidade.usuario;

import java.util.Set;
import modelo.entidade.mapa.*;
import modelo.enumeracao.mapa.MeioDeTransporte;

public abstract class Usuario {

	public String DefinirPartida(Ponto inicio) {
		return inicio;
	}

	public String DefinirDestino(Ponto chegada) {
		return chegada;
	}

	public MeioDeTrasporte DefinirTransporte(MeioDeTrasporte transporte) {
		return transporte;
	}

	public Trajeto trajeto(Ponto inicio, Ponto chegada, MeioDeTrasporte transporte) {

		return new Trajeto(DefinirPartida(inicio), DefinirDestino(chegada), DefinirTransporte(transporte));
	}

}

