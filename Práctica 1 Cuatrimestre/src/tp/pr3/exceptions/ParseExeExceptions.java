package tp.pr3.exceptions;
/**
 * <h1>Clase GameOverException</h1>
 * 
 * <p>
 * Excepción lanzada cuando se produce un error.
 * </p>
 */
public class ParseExeExceptions extends Exception {
	/**
	 * Constructor de la clase
	 * 
	 * @param mensaje mensaje provocado por una excepción
	 */
	public ParseExeExceptions(String mensaje) {
		super(mensaje);
	}

	public ParseExeExceptions() {
	}
}
