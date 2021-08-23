package modelo.dao.Formulario;

import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import modelo.entidade.formulario.Formulario;

public class FormularioDAOImpl implements FormularioDAO {

	private SessionFactory conectarBanco() throws SQLException {

		Configuration configuracao = new Configuration();

		configuracao.addAnnotatedClass(modelo.entidade.formulario.Formulario.class);
		configuracao.addAnnotatedClass(modelo.entidade.mapa.Ponto.class);
		configuracao.addAnnotatedClass(modelo.entidade.mapa.PontoAvaliado.class);
		configuracao.addAnnotatedClass(modelo.entidade.usuario.UsuarioCadastrado.class);

		configuracao.configure("hibernate.cfg.xml");

		ServiceRegistry servico = new StandardServiceRegistryBuilder().applySettings(configuracao.getProperties())
				.build();

		SessionFactory fabricaSessao = configuracao.buildSessionFactory(servico);

		return fabricaSessao;
	}

	@Override
	public void registrarAvaliacao(Formulario formulario) {

		Session sessao = null;

		try {

			sessao = conectarBanco().openSession();
			sessao.beginTransaction();

			sessao.save(formulario);

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
	
	public void deletarAvaliacao(Formulario formulario) {
		 
		Session sessao = null;
		
		try {
			
			sessao = conectarBanco().openSession();
			sessao.beginTransaction();
			
			sessao.delete(formulario);
			
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
	
	public void atualizarAvaliacao(Formulario formlario) {
		
		Session sessao = null;
		
		try {
			
			sessao = conectarBanco().openSession();
			sessao.beginTransaction();
			
			sessao.update(formlario);
			
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














