package tp.pr3.logic.multigames;

import java.util.Random;

import tp.pr3.Board;
import tp.pr3.Cell;
import tp.pr3.Position;

/**
 * <h1>Clase Rules2048</h1>
 * 
 * <p>Clase que contiene la parte específica del juego clásico</p>
 *
 * @author Marina Lozano Lahuerta
 * @author Pablo López Veleiro
 */
public class Rules2048 implements GameRules {
    private final int GANA = 2048;

    /**
     * {@inheritDoc}
     */
    public void addNewCellAt(Board board, Position pos, Random rand) {
        int valor = rand.nextInt(10); // Genera aleatoriamente un número del 0 al 9.
        if (valor > 8) {
            board.setCell(pos, 4); // 10%
        } else {
            board.setCell(pos, 2); // 90%
        }
    }

    /**
     * {@inheritDoc}
     */
    public int merge(Cell self, Cell other) {
        if (canMergeNeighbours(self, other)) {
            // En caso de fusión, la baldosa neighbour pasada como argumento es la baldosa
            // resultante de la fusión.
            other.setValue(self.getValue() + self.getValue());
            self.setValue(0);
            return other.getValue(); // Devuelve el valor de la fusión, si no hay fusión devuelve 0.
        } else
            return 0;
    }

    /**
     * {@inheritDoc}
     */
    public int getWinValue(Board board) {
        // Devuelve el valor máximo del tablero.
        return board.maxValue();
    }

    /**
     * {@inheritDoc}
     */
    public boolean win(Board board) {
        return getWinValue(board) == GANA;
    }

    /**
     * {@inheritDoc}
     */
    public boolean canMergeNeighbours(Cell self, Cell other) {
        return other.getValue() == self.getValue();
    }

    /**
     * {@inheritDoc}
     */
    public int points(int merge) {
        return merge;
    }

}
