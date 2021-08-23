package modelo.dao.Ponto;

import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import modelo.entidade.mapa.Ponto;

public class PontoDAOImpl {

	private SessionFactory conectarBanco() throws SQLException {

		Configuration configuracao = new Configuration();

		configuracao.addAnnotatedClass(modelo.entidade.formulario.Formulario.class);
		configuracao.addAnnotatedClass(modelo.entidade.mapa.PontoAvaliado.class);
		configuracao.addAnnotatedClass(modelo.entidade.mapa.PontoFavorito.class);
		configuracao.addAnnotatedClass(modelo.entidade.mapa.Trajeto.class);

		configuracao.configure("hibernate.cfg.xml");

		ServiceRegistry servico = new StandardServiceRegistryBuilder().applySettings(configuracao.getProperties())
				.build();

		SessionFactory fabricaSessao = configuracao.buildSessionFactory(servico);

		return fabricaSessao;
	}

	public void adicionarPonto(Ponto ponto) {

		Session sessao = null;

		try {

			sessao = conectarBanco().openSession();
			sessao.beginTransaction();

			sessao.save(ponto);

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
	
	public void deletarPonto(Ponto ponto) {
		
		Session sessao = null;
		
		try {
			
			sessao = conectarBanco().openSession();
			sessao.beginTransaction();
			
			sessao.delete(ponto);
			
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

}
