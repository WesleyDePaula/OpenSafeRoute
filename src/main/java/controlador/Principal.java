package controlador;

import modelo.dao.Usuario.UsuarioDAO;
import modelo.dao.Usuario.UsuarioDAOImpl;
import modelo.entidade.usuario.UsuarioCadastrado;
import modelo.excecao.usuario.EmailInvalidoException;
import modelo.excecao.usuario.SenhaPequenaException;
import modelo.excecao.usuario.StringVaziaException;


public class Principal {
	
	public static void main(String[] args) throws StringVaziaException, EmailInvalidoException, SenhaPequenaException {
		
		UsuarioDAO dao = new UsuarioDAOImpl();
		UsuarioCadastrado usuario = new UsuarioCadastrado("Ruan", "12345678", "ruan@gmail.com");
		
		dao.cadastrarUsuario(usuario);
		
	
	}

}
