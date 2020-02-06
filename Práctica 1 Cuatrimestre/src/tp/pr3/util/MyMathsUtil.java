package tp.pr3.util;

/**
 * <h1>Clase MyMathsUtil</h1>
 * 
 * <p>Ayuda para el modo de juego Fibonacci, proporciona
 * el número siguiente de la sucesión</p>
 *
 * @author Profesor
 */
public class MyMathsUtil {

	/**
	 * Proporciona el número siguiente de la sucesión de Fibonacci
	 * 
	 * @param previous número previo de la sucesión de Fibonacci
	 * @return número siguiente de la sucesión de Fibonacci
	 */
    public static int nextFib(int previous) {
        double phi = (1 + Math.sqrt(5)) / 2;
        return (int) Math.round(phi * previous);
    }
}
