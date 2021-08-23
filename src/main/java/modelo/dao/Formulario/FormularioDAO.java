package modelo.dao.Formulario;

import modelo.entidade.formulario.Formulario;

public interface FormularioDAO {
	
	void registrarAvaliacao(Formulario formulario);
	
	void deletarAvaliacao(Formulario formulario);
	
	void atualizarAvaliacao(Formulario formulario);

}
