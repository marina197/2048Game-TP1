package tp.pr3.control.commands;

import tp.pr3.logic.multigames.Game;

/**
 * <h1>Clase HelpCommand</h1>
 * 
 * <p>
 * Clase que ejecuta el comando Help, mostrando el mensaje de ayuda
 * </p>
 *
 * @author Marina Lozano Lahuerta
 * @author Pablo López Veleiro
 */
public class HelpCommand extends NoParamsCommand {
    /**
     * Texto con el nombre del comando
     */
    private static String commandInfo = "help";
    /**
     * Texto con la descripción del comando
     */
    private static String helpInfo = "Muestra este mensaje de ayuda.";

    /**
     * Contructor de la clase ExitCommand
     */
    public HelpCommand() {
        super(commandInfo, helpInfo);
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public Command createCommand() {
        return new HelpCommand();
    }

    @Override
    /**
     * {@inheritDoc}
     */
    public boolean execute(Game game) {
    	String help = "The available commands are:";
        System.out.println(help);
        System.out.println(CommandParser.commandHelp());
        return true;
    }

}
