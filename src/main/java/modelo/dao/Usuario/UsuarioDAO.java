package modelo.dao.Usuario;

import modelo.entidade.usuario.UsuarioCadastrado;

public interface UsuarioDAO {
	
	void cadastrarUsuario(UsuarioCadastrado usuario);
	
	void deletarUsuario(UsuarioCadastrado usuario);
	
	void atualizarUsuario(UsuarioCadastrado usuario);
	
}