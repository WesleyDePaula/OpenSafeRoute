package controlador;

import java.util.Scanner;

import modelo.dao.Usuario.UsuarioDAO;
import modelo.dao.Usuario.UsuarioDAOImpl;
import modelo.entidade.usuario.UsuarioCadastrado;
import modelo.excecao.usuario.EmailInvalidoException;
import modelo.excecao.usuario.SenhaPequenaException;
import modelo.excecao.usuario.StringVaziaException;

public class Principal {

	public static void main(String[] args) throws StringVaziaException, EmailInvalidoException, SenhaPequenaException {
		Scanner leitor = new Scanner(System.in);

		UsuarioDAO dao = new UsuarioDAOImpl();
		UsuarioCadastrado usuario = new UsuarioCadastrado("2222", "333333333", "444@gmail.com");

		dao.cadastrarUsuario(usuario);

		System.out.println(usuario.getIdUsuario());
		
		System.out.println("Quer deletar um usuario? [1=S][2=N]");
		int resp = leitor.nextInt();

		if (resp == 1) {

			dao.deletarUsuario(usuario);
			System.out.println("Deletado");
			
		}

	}

}
