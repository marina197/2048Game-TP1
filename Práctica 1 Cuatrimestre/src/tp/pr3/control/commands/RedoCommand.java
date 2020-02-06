package tp.pr3.control.commands;

import java.util.EmptyStackException;

import tp.pr3.exceptions.ParseExeExceptions;
import tp.pr3.logic.multigames.Game;

/**
 * <h1>Clase RedoCommand</h1>
 * 
 * <p>
 * Clase que ejecuta el comando Redo, rehace un movimiento
 * </p>
 *
 * @author Marina Lozano Lahuerta
 * @author Pablo López Veleiro
 */
public class RedoCommand extends NoParamsCommand {
    /**
     * Texto con el nombre del comando
     */
    private static String commandInfo = "redo";
    /**
     * Texto con la descripción del comando
     */
    private static String helpInfo = "redo the last undone command.";

    /**
     * Contructor de la clase RedoCommand
     */
    public RedoCommand() {
        super(commandInfo, helpInfo);
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public Command createCommand() {
        return new RedoCommand();
    }

    @Override
    /**
     * {@inheritDoc}
     */
	public boolean execute(Game game) throws ParseExeExceptions {
		try {
			game.redo();
			System.out.println("Redoing one move...");
			return true;
		} catch (EmptyStackException ese) {
			throw new ParseExeExceptions("Nothing to redo.");
		}
	}
}
