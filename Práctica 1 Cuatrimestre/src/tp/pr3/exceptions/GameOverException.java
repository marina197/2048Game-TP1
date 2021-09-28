package tp.pr3.exceptions;
/**
 * <h1>Clase GameOverException</h1>
 * 
 * <p>
 * Excepción lanzada para finalizar el juego.
 * </p>
 */
public class GameOverException extends Exception {
	/**
	 * Constructor de la clase
	 * 
	 * @param msg mensaje de error
	 */
	public GameOverException(String msg) {
		super(msg);
	}
}
