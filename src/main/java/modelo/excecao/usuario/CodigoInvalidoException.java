package model.exception.usuario;

public class CodigoInvalidoException extends Exception {
	public CodigoInvalidoException(String codigo) {
		super(codigo);
	}
}
