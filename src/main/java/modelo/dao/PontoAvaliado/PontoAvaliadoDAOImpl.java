package modelo.dao.PontoAvaliado;

import org.hibernate.Session;

import modelo.entidade.mapa.PontoAvaliado;
import modelo.factory.conexao.ConexaoFactory;

public class PontoAvaliadoDAOImpl implements PontoAvaliadoDAO {

	private ConexaoFactory fabrica;

	public PontoAvaliadoDAOImpl() {
		fabrica = new ConexaoFactory();
	}

	@Override
	public void adicionarPontoAvaliado(PontoAvaliado pontoAvaliado) {
		Session sessao = null;

		try {

			sessao = fabrica.getConexao().openSession();
			sessao.beginTransaction();

			sessao.save(pontoAvaliado);

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

	@Override
	public void deletarPontoAvaliado(PontoAvaliado pontoAvaliado) {

		Session sessao = null;

		try {

			sessao = fabrica.getConexao().openSession();
			sessao.beginTransaction();

			sessao.delete(pontoAvaliado);

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
