package tp.pr3.logic;

import java.util.EmptyStackException;
import tp.pr3.GameState;

/**
 * <h1>Clase GameStateStack</h1>
 * 
 * <p>
 * Almacena las sucesiones del representaciones compactas de estados
 * </p>
 *
 * @author Marina Lozano Lahuerta
 * @author Pablo López Veleiro
 */
public class GameStateStack {
    /**
     * Capacidad de la pila
     */
    private static final int CAPACITY = 20;
    /**
     * Estructura de datos que almacena las representaciones de los estados
     */
    private GameState[] aState = new GameState[CAPACITY];
    /**
     * Contador
     */
    private int count = 0;

    /**
     * Devuelve el último estado almacenado
     *
     * @return Representación compacta del último estado almacenado
     * @throws EmptyStackException Se produce cuando la pila está vacía.
     */
    public GameState pop() throws EmptyStackException{
        GameState state;
        if (isEmpty()) {
        	throw new EmptyStackException();
        } else {
            count--;
            state = aState[count];
        }
        return state;
    }

    /**
     * Almacena un nuevo estado
     *
     * @param state Representación compacta de un estado
     */
    public void push(GameState state) {
        if (count == CAPACITY) {
            for (int i = 1; i < CAPACITY; i++) {
                aState[i - 1] = aState[i];
            }
            aState[count - 1] = state;
        } else {
            aState[count] = state;
            count++;
        }
    }

    /**
     * Comprueba si la estructura de datos está vacía.
     *
     * @return <ul>
     * <li>true: si la estructura de datos está vacía</li>
     * <li>false: si la estructura de datos no está vacía</li> </ul>
     */
    public boolean isEmpty() {
        return count == 0;
    }
    
	/**
	 * Método que pone el contador a cero.
	 */
	public void clear() {
		count = 0;
	}

}
