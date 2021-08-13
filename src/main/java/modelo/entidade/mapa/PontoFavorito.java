package modelo.entidade.mapa;

import modelo.excecao.mapa.StatusInvalidoException;

public class PontoFavorito extends Ponto {

	private int idPontoFav;
	private String nomePonto;

	public PontoFavorito(int idPontoFav, Ponto ponto, String nomePonto) throws StatusInvalidoException {
		super(ponto.getLatitude(), ponto.getLongitude());
		
		setIdPontoFav(idPontoFav);
		setNomePonto(nomePonto);
	}
	
	public PontoFavorito(Ponto ponto, String nomePonto) throws StatusInvalidoException {
		super(ponto.getLatitude(), ponto.getLongitude());
		setNomePonto(nomePonto);
	}

	
	public int getIdPontoFav() {
		return idPontoFav;
	}
	
	public void setIdPontoFav(int idPontoFav) {
		this.idPontoFav = idPontoFav;
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
	
	
}
