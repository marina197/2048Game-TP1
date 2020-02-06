package tp.pr3.logic.multigames;

import java.util.Random;

import tp.pr3.Board;
import tp.pr3.Cell;
import tp.pr3.Position;
import tp.pr3.util.MyMathsUtil;

/**
 * <h1>Clase RulesFib</h1>
 * 
 * <p>Características propias del juego Fibonacci</p>
 *
 * @author Marina Lozano Lahuerta
 * @author Pablo López Veleiro
 */
public class RulesFib implements GameRules {
    private final int GANA = 144;

    @Override
    /**
     * {@inheritDoc}
     */
    public void addNewCellAt(Board board, Position pos, Random rand) {
        int valor = rand.nextInt(10); // Genera aleatoriamente un número del 0 al 9.
        if (valor > 8) {
            board.setCell(pos, 2); // 10%
        } else {
            board.setCell(pos, 1); // 90%
        }
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public int merge(Cell self, Cell other) {
        if (canMergeNeighbours(self, other)) {
            other.setValue(self.getValue() + other.getValue());
            self.setValue(0);
            return other.getValue(); // Devuelve el valor de la fusión, si no hay fusión devuelve 0.
        } else
            return 0;
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public int getWinValue(Board board) {
        // Devuelve el valor máximo del tablero.
        return board.maxValue();
    }

    @Override
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
        return (self.getValue() == 1 && other.getValue() == 1)
                || (self.getValue() == MyMathsUtil.nextFib(other.getValue()))
                || (other.getValue() == MyMathsUtil.nextFib(self.getValue()));
    }

    /**
     * {@inheritDoc}
     */
    public int points(int merge) {
        return merge;
    }
}
