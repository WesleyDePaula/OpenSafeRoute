package modelo.entidade.mapa;

import modelo.excecao.mapa.StatusInvalidoException;

public class PontoFavorito extends Ponto {

	private String nomePonto;

	public PontoFavorito(Ponto ponto, String nomePonto) throws StatusInvalidoException {
		super(ponto.getLatitude(), ponto.getLongitude());
		setNomePonto(nomePonto);
	}

	public PontoFavorito(Ponto ponto) throws StatusInvalidoException {
		super(ponto.getLatitude(), ponto.getLongitude());

	}

	public String getNomePonto() {
		return nomePonto;
	}

	public void setNomePonto(String nomePonto) {
		this.nomePonto = nomePonto;
	}

	public static PontoFavorito favoritarPontoENomear(Ponto ponto, String nomePonto) throws StatusInvalidoException {

		return new PontoFavorito(ponto, nomePonto);

	}
	
	public static PontoFavorito favoritarPonto(Ponto ponto) throws StatusInvalidoException {

		return new PontoFavorito(ponto);

	}


	public static PontoFavorito desfavoritarPonto(PontoFavorito ponto) throws StatusInvalidoException {

		return new PontoFavorito(ponto);
	}
	
	
}
