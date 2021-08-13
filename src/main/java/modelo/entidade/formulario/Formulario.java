package modelo.entidade.formulario;

import modelo.enumeracao.mapa.Estrelas;
import modelo.enumeracao.mapa.NivelBloqueio;
import modelo.enumeracao.mapa.Ocorrencia;

public class Formulario {

	private int idFormulario;
	private double media;
	private Ocorrencia ocorrencia;
	private Estrelas nivelEstrutura;
	private Estrelas nivelIluminacao;
	private NivelBloqueio bloqueioRuas;
	private Estrelas NivelTransito;
	private String comentario;

	public Formulario(Ocorrencia ocorrencia, Estrelas nivelEstrutura, Estrelas nivelIluminacao, NivelBloqueio bloqueioRuas,
			Estrelas NivelTransito, String comentario) {

		setOcorrencia(ocorrencia);
		setNivelEstrutura(nivelEstrutura);
		setNivelIluminacao(nivelIluminacao);
		setNivelBloqueio(bloqueioRuas);
		setNivelTransito(NivelTransito);
		setComentario(comentario);
		setMedia();

	}
	
	public Formulario(int idFormulario, Ocorrencia ocorrencia, Estrelas nivelEstrutura, Estrelas nivelIluminacao, NivelBloqueio bloqueioRuas,
			Estrelas NivelTransito, String comentario) {

		setIdFormulario(idFormulario);
		setOcorrencia(ocorrencia);
		setNivelEstrutura(nivelEstrutura);
		setNivelIluminacao(nivelIluminacao);
		setNivelBloqueio(bloqueioRuas);
		setNivelTransito(NivelTransito);
		setComentario(comentario);
		setMedia();

	}
	
	public int getIdFormulario() {
		return idFormulario;
	}
	
	public void setIdFormulario(int idFormulario) {
		this.idFormulario = idFormulario;
	}

	public Estrelas getNivelEstrutura() {
		return nivelEstrutura;
	}

	private void setNivelEstrutura(Estrelas nivelEstrutura) {
		this.nivelEstrutura = nivelEstrutura;
	}

	public Estrelas getNivelIluminacao() {
		return nivelIluminacao;
	}

	private void setNivelIluminacao(Estrelas nivelIluminacao) {
		this.nivelIluminacao = nivelIluminacao;
	}

	public NivelBloqueio getNivelBloqueio() {
		return bloqueioRuas;
	}

	private void setNivelBloqueio(NivelBloqueio bloqueioRuas) {
		this.bloqueioRuas = bloqueioRuas;
	}

	public Estrelas getNivelTransito() {
		return NivelTransito;
	}

	private void setNivelTransito(Estrelas nivelTransito) {
		this.NivelTransito = nivelTransito;
	}

	public String getComentario() {
		return comentario;
	}

	private void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Ocorrencia getOcorrencia() {
		return ocorrencia;
	}

	private void setOcorrencia(Ocorrencia ocorrencia) {
		this.ocorrencia = ocorrencia;
	}

	public double getMedia() {
		return media;
	}

	private void setMedia() {
		this.media = (((getNivelEstrutura().getPeso() + getNivelTransito().getPeso() + getNivelIluminacao().getPeso()) / 3)
				- getOcorrencia().getPeso());
	}

}
