package modelo.dao.Usuario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import modelo.entidade.usuario.UsuarioCadastrado;

public class UsuarioDAOImpl implements UsuarioDAO {

	private Connection conectarBanco() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://localhost/opensaferoutes?useTimezone=true&serverTimezone=UTC&user=root&password=root");
	}

	public void cadastrarUsuario(UsuarioCadastrado usuario) {
		
		Connection conexao = null;
		PreparedStatement insert = null;

		try {

			conexao = conectarBanco();
			insert = conexao.prepareStatement(
					"INSERT INTO usuario (nome_usuario, senha_usuario, email_usuario) VALUES (?,?,?)");

			insert.setString(1, usuario.getNome());
			insert.setString(2, usuario.getSenha());
			insert.setString(3, usuario.getEmail());

			insert.execute();

		} catch (SQLException erro) {
			erro.printStackTrace();
		}

		finally {

			try {

				if (insert != null)
					insert.close();

				if (conexao != null)
					conexao.close();

			} catch (SQLException erro) {

				erro.printStackTrace();
			}

		}
		
	}

	public void deletarUsuario(UsuarioCadastrado usuario) {
		
		Connection conexao = null;
		PreparedStatement delete = null;
		
		try {
			
			conexao = conectarBanco();
			delete = conexao.prepareStatement("DELETE FROM usuario WHERE (id_usuario = ?)");
			
			delete.setInt(1, usuario.getIdUsuario());
			
			delete.execute();
			
		} catch (SQLException erro) {
			erro.printStackTrace();
		}
		
		finally {
			
			try {

				if (delete != null)
					delete.close();

				if (conexao != null)
					conexao.close();

			} catch (SQLException erro) {

				erro.printStackTrace();
			}
			
		}
		
	}
	
}
