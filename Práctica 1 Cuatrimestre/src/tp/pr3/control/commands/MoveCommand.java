package tp.pr3.control.commands;

import tp.pr3.logic.multigames.Game;
import java.util.Scanner;
import tp.pr3.Direction;
import tp.pr3.exceptions.GameOverException;
import tp.pr3.exceptions.ParseExeExceptions;

/**
 * <h1>Clase MoveCommand</h1>
 * 
 * <p>
 * Clase que ejecuta el comando Move, realizando la ejecución del movimiento
 * </p>
 *
 * @author Marina Lozano Lahuerta
 * @author Pablo López Veleiro
 */
public class MoveCommand extends Command {
	/**
	 * Texto con el nombre del comando
	 */
	private static String commandInfo = "move";
	/**
	 * Texto con la descripción del comando
	 */
	private static String helpInfo = "execute a move in one of the directions: " + Direction.externaliseAll();
	/**
	 * Guarda la dirección indicada por el usuario
	 */
	private Direction dir;

	/**
	 * Contructor de la clase MoveCommand
	 */
	public MoveCommand() {
		super(commandInfo + " <direction>", helpInfo);
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public boolean execute(Game game) throws GameOverException {
		game.move(dir);
		return true;
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public Command parse(String[] commandWords, Scanner in) throws ParseExeExceptions {
		MoveCommand move;

		if (commandWords[0].contentEquals(commandInfo.toUpperCase())) {
			if (commandWords.length < 2) {
				throw new ParseExeExceptions("Move must be followed by a direction: " + Direction.externaliseAll());
			} else {
				move = new MoveCommand();
				commandWords[1] = commandWords[1].toLowerCase();
				move.dir = Direction.parse(commandWords[1]);
				if (move.dir == null) {
					throw new ParseExeExceptions("Unknown direction for move command.");
				} else {

					return move;
				}
			}

		} else {
			return null;
		}
	}

}
