package tp.pr3.control.commands;

import java.util.EmptyStackException;

import tp.pr3.exceptions.ParseExeExceptions;
import tp.pr3.logic.multigames.Game;

/**
 * <h1>Clase UndoCommand</h1>
 * 
 * <p>
 * Clase que ejecuta el comando Undo, deshace un moviento
 * </p>
 *
 * @author Marina Lozano Lahuerta
 * @author Pablo López Veleiro
 */
public class UndoCommand extends NoParamsCommand {
    /**
     * Texto con el nombre del comando
     */
    private static String commandInfo = "undo";
    /**
     * Texto con la descripción del comando
     */
    private static String helpInfo = "undo the last command.";

    /**
     * Contructor de la clase UndoCommand
     */
    public UndoCommand() {
        super(commandInfo, helpInfo);
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public Command createCommand() {
        return new UndoCommand();
    }

    @Override
    /**
     * {@inheritDoc}
     */
	public boolean execute(Game game) throws ParseExeExceptions {
		try {
			game.undo();
			System.out.println("Undoing one move...");
			return true;
		} catch (EmptyStackException ese) {
			throw new ParseExeExceptions("Undo is not available.");
		}
	}

}
