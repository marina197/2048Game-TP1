package tp.pr3.logic.multigames;

import java.util.Random;

import tp.pr3.*;
import tp.pr3.util.ArrayAsList;

/**
 * <h1>Clase GameRules</h1>
 * 
 * <p>Interfaz base para las caracteristicas de cada tipo de juego</p>
 *
 * @author Marina Lozano Lahuerta
 * @author Pablo López Veleiro
 */
public interface GameRules {

	/**
	 * Establece el valor de la posición pos del tablero
     * board dado el número pseudo-aleatorio
	 * 
	 * @param board tablero del juego actual
	 * @param pos posición aleatoria donse poner el valor
	 * @param rand Comportamiento aleatorio del juego
	 */
    void addNewCellAt(Board board, Position pos, Random rand);

    /**
     * Fusiona las celdas dadas y devuelve el valor de la unión
     * 
     * @param self celda de la posición actual
     * @param other celda vecina con la que realizar la fusión
     * @return valor de la baldosa resultante de la fusión
     */
    int merge(Cell self, Cell other);


    /**
     * Devuelve el mejor valor del tablero, según las reglas de ese juego,
     * comprobándose si es un valor ganador y se ha ganado el juego
     * 
     * @param board tablero del juego actual
     * @return valor de la baldosa con la que se ganaría el juego
     */
    int getWinValue(Board board);


    /**
     * Devuelve si el juego se ha ganado o no
     * 
     * @param board tablero del juego actual
     * @return <ul>
	 *         <li>true: si el juego se ha ganado</li>
	 *         <li>false: si el juego no se ha ganado</li>
	 *         </ul>
     */
    boolean win(Board board);

    /**
     * Comprueba si la fusión en la dirección RIGHT es posible.
     * 
     * @param self celda de la posición actual
     * @param other celda vecina con la que comprobar si la fusión es posible
     * @return <ul>
	 *         <li>true: si la fusión es posible</li>
	 *         <li>false: si la fusión no es posible</li>
	 *         </ul>
     */
    boolean canMergeNeighbours(Cell self, Cell other);

    /**
     * Devuelve los puntos ganados en el movimiento
     * 
     * @param merge puntuación ganada en la fusión
     * @return puntos ganados
     */
    int points(int merge);

	/**
	 * Devuelve si el juego de ha perdido o no porque no hay ningún movimiento
	 * posible.
	 * 
	 * @param board tablero del juego actual
	 * @return <ul>
	 *         <li>true: si el juego se ha perdido</li>
	 *         <li>false: si el juego no se ha perdido</li>
	 *         </ul>
	 */
    default boolean lose(Board board) {
        // COMPRUEBA SI HAY ALGÚN DESPLAZAMIENTO POSIBLE.
        Position p;
        boolean move = false;
        for (int i = 0; i < board.get_boardSize(); i++) {
            for (int j = 0; j < board.get_boardSize(); j++) {
                p = new Position(i, j);
                if (board.isEmpty(p))
                    move = true;
            }
        }
        if (move)
            return false;
        else {
            // COMPRUEBA SI HAY ALGUNA FUSIÓN POSIBLE.
            Position pos;
            int z = 0;
            boolean fusion = false;
            Direction dir = null;
            do {
                switch (z) {
                    case 0: // UP
                        dir = Direction.UP;
                        break;
                    case 1:// RIGHT
                        dir = Direction.RIGHT;
                        break;
                    case 2:// LEFT
                        dir = Direction.LEFT;
                        break;
                    case 3:// DOWN
                        dir = Direction.DOWN;
                        break;
                }
                for (int i = 0; i < board.get_boardSize(); i++) {
                    for (int j = 0; j < board.get_boardSize(); j++) {
                        pos = new Position(i, j);
                        if (!fusion)
                            fusion = board.fusionPosible(pos, dir, this);
                    }
                }
                z++;
            } while (z < 4 && !fusion);

            return !fusion;
        }
    }

    /**
     * Crea y devuelve un nuevo tablero de tamaño size * size
     * 
     * @param size tamaño del tablero
     * @return tablero con dimensión size * size
     */
    default Board createBoard(int size) {
        return new Board(size);
    }

    /**
     * Elige una posicion libre de board e invoca el método addNewCellAt() 
     * para añadir una célula en esa posición.
     * 
     * @param board tablero del juego actual
     * @param rand Comportamiento aleatorio del juego
     */
    default void addNewCell(Board board, Random rand) {
        Position pos;

        ArrayAsList lista = new ArrayAsList();
        for (int i = 0; i < board.get_boardSize(); i++) {
            for (int j = 0; j < board.get_boardSize(); j++) {
                pos = new Position(i, j);
                if (board.isEmpty(pos))
                    lista.insertar(pos);
            }
        }

        // Elige aleatoriamente una posición vacía del tablero.
        pos = (Position) ArrayAsList.choice(lista, rand);
        addNewCellAt(board, pos, rand);
    }

    /**
     * Inicializa board eligiendo numCells posiciones libres, e invoca
     * el método addNewCellAt() para añadir nuevas células en esas posiciones
     * 
     * @param board tablero del juego actual
     * @param numCells número de baldosas iniciales
     * @param rand Comportamiento aleatorio del juego
     */
    default void initBoard(Board board, int numCells, Random rand) {
        for (int c = 0; c < numCells; c++) {
            addNewCell(board, rand);
        }
    }

}
