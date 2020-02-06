package tp.pr3.logic.multigames;

import java.util.Random;
import tp.pr3.Board;
import tp.pr3.Cell;
import tp.pr3.Position;

/**
 * <h1>Clase RulesInverse</h1>
 * 
 * <p>Características propias del juego inverso</p>
 *
 * @author Marina Lozano Lahuerta
 * @author Pablo López Veleiro
 */
public class RulesInverse implements GameRules {
	private final int GANA = 1;

	/**
	 * {@inheritDoc}
	 */
	public void addNewCellAt(Board board, Position pos, Random rand) {
		int valor = rand.nextInt(10); // Genera aleatoriamente un número del 0 al 9.
		if (valor > 8) {
			board.setCell(pos, 1024); // 10%
		} else {
			board.setCell(pos, 2048); // 90%
		}
	}

    /**
     * {@inheritDoc}
     */
	public int merge(Cell self, Cell other) {
		if (canMergeNeighbours(self, other)) {
			// En caso de fusión, la baldosa neighbour pasada como argumento es la baldosa
			// resultante de la fusión.
			other.setValue(self.getValue() / 2);
			self.setValue(0);
			return other.getValue(); // Devuelve el valor de la fusión, si no hay fusión devuelve 0.
		} else
			return 0;
	}

    /**
     * {@inheritDoc}
     */
	public int getWinValue(Board board) {
		// Devuelve el valor mínimo del tablero.
		return board.minValue();
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
		int i = 1024;
		int points = 2;

		while (i > merge) {
			i = i / 2;
			points = points * 2;
		}

		return points;

	}

}
