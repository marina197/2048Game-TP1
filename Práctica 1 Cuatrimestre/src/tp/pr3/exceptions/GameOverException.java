package tp.pr3.exceptions;
/**
 * <h1>Clase GameOverException</h1>
 * 
 * <p>
 * Excepción lanzada para finalizar el juego.
 * </p>
 *
 * @author Marina Lozano Lahuerta
 * @author Pablo López Veleiro
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
