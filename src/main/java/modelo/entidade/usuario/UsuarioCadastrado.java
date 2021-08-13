package modelo.entidade.usuario;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import modelo.entidade.formulario.Formulario;
import modelo.entidade.mapa.Ponto;
import modelo.entidade.mapa.PontoFavorito;
import modelo.excecao.mapa.StatusInvalidoException;
import modelo.excecao.usuario.EmailInvalidoException;
import modelo.excecao.usuario.SenhaPequenaException;
import modelo.excecao.usuario.StringVaziaException;

public class UsuarioCadastrado extends Usuario {

	private int idUsuario;
	private String nome;
	private String senha;
	private String email;
	private List<PontoFavorito> favoritos;

	public UsuarioCadastrado(int idUsuario, String nome, String senha, String email)
			throws StringVaziaException, EmailInvalidoException, SenhaPequenaException {
		super();
		
		this.setNome(nome);
		this.setSenha(senha);
		this.setEmail(email);
	}
	
	public UsuarioCadastrado(String nome, String senha, String email)
			throws StringVaziaException, EmailInvalidoException, SenhaPequenaException {
		super();
		this.setNome(nome);
		this.setSenha(senha);
		this.setEmail(email);
	}
	
	public int getIdUsuario() {
		return idUsuario;
	}
	
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNome() {
		return nome;

	}

	public void setNome(String nome) throws StringVaziaException {

		if (nome.isEmpty()) {
			throw new StringVaziaException("O nome de Usuï¿½rio ï¿½ invï¿½lido!");
		}

		this.nome = nome;

		// (FALTA FAZER) verificar nomes iguais
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) throws StringVaziaException, SenhaPequenaException {

		if (senha.length() < 8) {
			throw new SenhaPequenaException("A senha nï¿½o pode ter menos que 8 digitos");
		}

		if (nome.isEmpty()) {
			throw new StringVaziaException("A senha nï¿½o pode ser vazia!");
		}

		this.senha = senha;

	}

	public String getEmail() {
		return email;
	}

	public List<PontoFavorito> getFavoritos() {
		return favoritos;
	}

	private void setEmail(String email) throws EmailInvalidoException, StringVaziaException {

		if (nome.isEmpty()) {
			throw new StringVaziaException("O nome de Usuï¿½rio ï¿½ invï¿½lido!");
		}

		if (validarEmail(email) == false) {
			throw new EmailInvalidoException("Email Invï¿½lido!");
		}

		this.email = email;

	}

	public boolean validarEmail(String email) {

		boolean isEmailValid = false;

		if (email != null && email.length() > 0) {
			String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
			Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(email);
			if (matcher.matches()) {
				isEmailValid = true;
			}
		}

		return isEmailValid;
	}

	public Formulario avaliar(Formulario avaliacao) {
		return avaliacao;
	}

	public void favoritarENomear(Ponto ponto, String nomePonto) throws StatusInvalidoException {
		PontoFavorito.favoritarPontoENomear(ponto, nomePonto);
		favoritos.add((PontoFavorito) ponto);

	}
}
