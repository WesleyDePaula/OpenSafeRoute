package controlador;

import java.util.Scanner;

import modelo.dao.Usuario.UsuarioDAO;
import modelo.dao.Usuario.UsuarioDAOImpl;
import modelo.entidade.mapa.Ponto;
import modelo.entidade.usuario.UsuarioCadastrado;
import modelo.excecao.usuario.EmailInvalidoException;
import modelo.excecao.usuario.SenhaPequenaException;
import modelo.excecao.usuario.StringVaziaException;

public class Principal {

	public static void main(String[] args) throws StringVaziaException, EmailInvalidoException, SenhaPequenaException {

		try {
			
			String a = "Rua fernandes marques brinhosa, 18 blumenau";

			System.out.println(Ponto.informatLocal(a).getLatitude());
			System.out.println(Ponto.informatLocal(a).getLongitude());
			System.out.println(Ponto.informatLocal(a).getAltitude());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
