package tp.pr3.control.commands;

import tp.pr3.logic.multigames.Game;

/**
 * <h1>Clase ResetCommand</h1>
 * 
 * <p>
 * Clase que ejecuta el comando Reset, inicializando el juego
 * </p>
 *
 * @author Marina Lozano Lahuerta
 * @author Pablo López Veleiro
 */
public class ResetCommand extends NoParamsCommand {
    /**
     * Texto con el nombre del comando
     */
    private static String commandInfo = "reset";
    /**
     * Texto con la descripción del comando
     */
    private static String helpInfo = "start a new game.";

    /**
     * Contructor de la clase ResetCommand
     */
    public ResetCommand() {
        super(commandInfo, helpInfo);
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public Command createCommand() {
        return new ResetCommand();
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public boolean execute(Game game) {
        game.reset();
		return true;
    }
}
