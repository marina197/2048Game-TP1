package tp.pr3;

/**
 * <h1>Clase MoveResults</h1>
 * 
 * <p>Representa los resultados de la ejecución de un movimiento.</p>
 *
 * @author Marina Lozano Lahuerta
 * @author Pablo López Veleiro
 */
public class MoveResults {
    /**
     * identifica si ha habido movimiento
     */
    private boolean moved;
    /**
     * almacena el número de puntos obtenidos en el movimiento
     */
    private int points;

    /**
     * Contructor de la clase MoveResults
     *
     * @param moved  indica si se ha producido un movimiento
     * @param points puntos acumulados en el juego
     */
    public MoveResults(boolean moved, int points) {
        this.moved = moved;
        this.points = points;
    }

    /**
     * Comprueba si se ha producido un movimiento
     *
     * @return <ul>
     * <li>true: se ha producido un movimiento</li>
     * <li>false: no se ha producido un movimiento</li>
     * </ul>
     */
    public boolean isMoved() {
        return moved;
    }

    /**
     * Devuelve los puntos acumulados en la partida
     *
     * @return puntos acumulados en la partida
     */
    public int getPoints() {
        return points;
    }
}