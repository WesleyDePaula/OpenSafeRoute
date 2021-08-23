package modelo.dao.Usuario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import modelo.entidade.usuario.UsuarioCadastrado;

public class UsuarioDAOImpl implements UsuarioDAO {

	private SessionFactory conectarBanco() throws SQLException {

		Configuration configuracao = new Configuration();

		configuracao.addAnnotatedClass(modelo.entidade.formulario.Formulario.class);
		configuracao.addAnnotatedClass(modelo.entidade.mapa.PontoAvaliado.class);
		configuracao.addAnnotatedClass(modelo.entidade.mapa.PontoFavorito.class);
		configuracao.addAnnotatedClass(modelo.entidade.mapa.Trajeto.class);
		configuracao.addAnnotatedClass(modelo.entidade.usuario.UsuarioCadastrado.class);

		configuracao.configure("hibernate.cfg.xml");

		ServiceRegistry servico = new StandardServiceRegistryBuilder().applySettings(configuracao.getProperties()).build();

		SessionFactory fabricaSessao = configuracao.buildSessionFactory(servico);

		return fabricaSessao;
	}

	public void cadastrarUsuario(UsuarioCadastrado usuario) {

		Session sessao = null;

		try {

			sessao = conectarBanco().openSession();
			sessao.beginTransaction();

			sessao.save(usuario);

			sessao.getTransaction().commit();

		} catch (Exception erro) {
			erro.printStackTrace();

			if (sessao.getTransaction() != null) {
				sessao.getTransaction().rollback();
			}

		} finally {

			if (sessao != null) {
				sessao.close();
			}
		}
	}

	public void deletarUsuario(UsuarioCadastrado usuario) {

		Session sessao = null;

		try {

			sessao = conectarBanco().openSession();
			sessao.beginTransaction();

			sessao.delete(usuario);

			sessao.getTransaction().commit();

		} catch (Exception erro) {
			erro.printStackTrace();

			if (sessao.getTransaction() != null) {
				sessao.getTransaction().rollback();
			}

		} finally {

			if (sessao != null) {
				sessao.close();
			}
		}
	}
	
	public void atualizarUsuario(UsuarioCadastrado usuario) {
		
		Session sessao = null;
		
		try {
			
			sessao = conectarBanco().openSession();
			sessao.beginTransaction();
			
			sessao.update(usuario);
		
			sessao.getTransaction().commit();
			
		} catch (Exception sqlException) {

			sqlException.printStackTrace();

			if (sessao.getTransaction() != null) {
				sessao.getTransaction().rollback();
			}

		} finally {

			if (sessao != null) {
				sessao.close();
			}
		}
		
	}

}
