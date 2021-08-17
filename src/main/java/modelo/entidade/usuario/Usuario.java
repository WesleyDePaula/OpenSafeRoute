package modelo.entidade.usuario;

import modelo.entidade.mapa.*;
import modelo.enumeracao.mapa.MeioDeTransporte;
import modelo.excecao.mapa.StatusInvalidoException;

public abstract class Usuario {

	public String DefinirPartida(String inicio) {
		return inicio;
	}

	public String DefinirDestino(String chegada) {
		return chegada;
	}

	public MeioDeTransporte DefinirTransporte(MeioDeTransporte transporte) {
		return transporte;
	} 

	public Trajeto trajeto(String inicio, String chegada, MeioDeTransporte transporte) throws StatusInvalidoException {

		return new Trajeto(DefinirPartida(inicio), DefinirDestino(chegada), DefinirTransporte(transporte));
	}

}

