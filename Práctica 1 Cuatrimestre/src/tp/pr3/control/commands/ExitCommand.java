package tp.pr3.control.commands;

import tp.pr3.exceptions.GameOverException;
import tp.pr3.logic.multigames.Game;

/**
 * <h1>Clase ExitCommand</h1>
 * 
 * <p>
 * Clase que ejecuta el comando Exit, saliendo de la aplicación
 * </p>
 *
 * @author Marina Lozano Lahuerta
 * @author Pablo López Veleiro
 */
public class ExitCommand extends NoParamsCommand {
	/**
	 * Texto con el nombre del comando
	 */
	private static String commandInfo = "exit";
	/**
	 * Texto con la descripción del comando
	 */
	private static String helpInfo = "terminate the program.";

	/**
	 * Contructor de la clase ExitCommand
	 */
	public ExitCommand() {
		super(commandInfo, helpInfo);
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public Command createCommand() {
		return new ExitCommand();
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public boolean execute(Game game) throws GameOverException {
		throw new GameOverException("Game Over.");
	}

}
