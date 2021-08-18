package modelo.entidade.formulario;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import modelo.enumeracao.mapa.Estrelas;
import modelo.enumeracao.mapa.NivelBloqueio;
import modelo.enumeracao.mapa.Ocorrencia;


@Entity
@Table(name = "Formulario")
public class Formulario implements Serializable {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTYTY)
	@Column(name = "id_formulario")
	private int idFormulario;
	
	@Column(name "media_formulario", scale = 2, precision = 1  , nullable = false, unique = true )
	private double media;
	
	@Column (name = "ocorrencia_Formulario", nullable = false, unique = false )
	@Enumerated(EnumType.STRING)
	private Ocorrencia ocorrencia;
	
	@Column (name = "nivel_estrutura_Formulario", nullable = false, unique = false )
	@Enumerated(EnumType.STRING)
	private Estrelas nivelEstrutura;
	
	@Column (name = "nivel_Iluminacao_Formulario", nullable = false, unique = false)
	@Enumerated(EnumType.STRING)
	private Estrelas nivelIluminacao;
	
	@Column (name = "bloqueio_Ruas_Formulario", nullable = false, unique = false
	@Enumerated(EnumType.STRING)		
	private NivelBloqueio bloqueioRuas;
	
	@Column (name = "nivel_Transito_Formulario", nullable = false, unique = false)
	@Enumerated(EnumType.STRING)
	private Estrelas NivelTransito;
	
	
	@Column (name = "comentario_Formulario", length = 300, nullable = true, unique = true  )
	private String comentario;
	
	

	public Formulario () {}
	
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
