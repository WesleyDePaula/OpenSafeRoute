package modelo.entidade.mapa;

import java.util.List;

import modelo.entidade.formulario.Formulario;
import modelo.enumeracao.mapa.NivelBloqueio;
import modelo.excecao.mapa.StatusInvalidoException;

public class PontoAvaliado extends Ponto {

	private int idPontoAvaliado;
	private List<Formulario> avaliacoes;
	private double nivelDeCriminalidade;
	private int nivelDeEstruturaDaRua;
	private int nivelDeIluminacao;
	private int nivelDeTransito;
	private NivelBloqueio bloqueio;
	private int mediaDeAvaliacao;

	public PontoAvaliado(int idPontoAvaliado, Ponto ponto, Formulario avaliacao) throws StatusInvalidoException, NullPointerException {
		super(ponto.getLatitude(), ponto.getLatitude());
		
		this.setIdPontoAvaliado(idPontoAvaliado);
		this.addAvaliacao(avaliacao);

	}
	
	public PontoAvaliado(Ponto ponto, Formulario avaliacao) throws StatusInvalidoException, NullPointerException {
		super(ponto.getLatitude(), ponto.getLatitude());
		this.addAvaliacao(avaliacao);

	}
	
	public int getIdPontoAvaliado() {
		return idPontoAvaliado;
	}
	
	public void setIdPontoAvaliado(int idPontoAvaliado) {
		this.idPontoAvaliado = idPontoAvaliado;
	}

	public int getMediaDeAvaliacao() {
		return mediaDeAvaliacao;
	}

	private void setMediaDeAvaliacao() {
		double soma = 0;
		for (Formulario formulario : getAvaliacoes()) {
			soma += formulario.getMedia();
		}
		this.nivelDeCriminalidade = soma / getAvaliacoes().size();
	}

	public List<Formulario> getAvaliacoes() {
		return avaliacoes;
	}

	public void addAvaliacao(Formulario avaliacao) throws NullPointerException {

		if (avaliacao == null) {
			throw new NullPointerException();
		}

		this.avaliacoes.add(avaliacao);
		this.setNivelDeCriminalidade();
		this.setNivelDeEstruturaDaRua();
		this.setNivelDeIluminacao();
		this.setBloqueio();
		this.setNivelDeTransito();
		this.setMediaDeAvaliacao();
	}

	public void removeAvaliacao(Formulario avaliacao) throws NullPointerException {

		if (avaliacao == null) {
			throw new NullPointerException();
		}

		this.avaliacoes.remove(avaliacao);

		if (getAvaliacoes().size() > 0) {

			this.setNivelDeCriminalidade();
			this.setNivelDeEstruturaDaRua();
			this.setNivelDeIluminacao();
			this.setBloqueio();
			this.setNivelDeTransito();
			this.setMediaDeAvaliacao();
		
		}
	}

	public double getNivelDeCriminalidade() {
		return nivelDeCriminalidade;
	}

	private void setNivelDeCriminalidade() {

		double soma = 0;
		for (Formulario formulario : getAvaliacoes()) {
			soma += formulario.getOcorrencia().getPeso();
		}
		this.nivelDeCriminalidade = soma / getAvaliacoes().size();
	}

	public int getNivelDeEstruturaDaRua() {
		return nivelDeEstruturaDaRua;
	}

	private void setNivelDeEstruturaDaRua() {
		double soma = 0;
		for (Formulario formulario : getAvaliacoes()) {
			soma += formulario.getNivelEstrutura().getPeso();
		}
		this.nivelDeCriminalidade = soma / getAvaliacoes().size();
	}

	public int getNivelDeIluminacao() {
		return nivelDeIluminacao;
	}

	private void setNivelDeIluminacao() {

		double soma = 0;
		for (Formulario formulario : getAvaliacoes()) {
			soma += formulario.getNivelIluminacao().getPeso();
		}
		this.nivelDeCriminalidade = soma / getAvaliacoes().size();
	}

	public NivelBloqueio getBloqueio() {
		return bloqueio;
	}

	private void setBloqueio() {
		this.bloqueio = getAvaliacoes().get(getAvaliacoes().size()).getNivelBloqueio();
	}

	public int getNivelDeTransito() {
		return nivelDeTransito;
	}

	private void setNivelDeTransito() {

		double soma = 0;
		for (Formulario formulario : getAvaliacoes()) {
			soma += formulario.getNivelTransito().getPeso();
		}
		this.nivelDeCriminalidade = soma / getAvaliacoes().size();
	}

}
