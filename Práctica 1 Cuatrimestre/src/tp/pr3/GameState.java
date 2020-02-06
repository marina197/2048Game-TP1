package tp.pr3;

/**
 * <h1>Clase GameState</h1>
 * 
 * <p>Representa el estado del tablero</p>
 *
 * @author Marina Lozano Lahuerta
 * @author Pablo López Veleiro
 */
public class GameState {
    /**
     * Puntuación del juego
     */
    private int score;
    /**
     * Estado actual del tablero
     */
    private int[][] boardState;

    /**
     * Contructor de la clase GameState
     *
     * @param score      puntuación del juego
     * @param boardState estado del tablero
     */
    public GameState(int score, int[][] boardState) {
        this.score = score;
        this.boardState = boardState;
    }

    /**
     * Devuelve la puntuación del juego
     *
     * @return puntuación del juego
     */
    public int getScore() {
        return score;
    }

    /**
     * Devuelve el estado del tablero
     *
     * @return estado del tablero
     */
    public int[][] getBoardState() {
        return boardState;
    }
}
