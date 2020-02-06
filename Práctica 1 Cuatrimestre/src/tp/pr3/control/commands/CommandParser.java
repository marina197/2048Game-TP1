package tp.pr3.control.commands;

import java.util.Scanner;

import tp.pr3.control.Controller;
import tp.pr3.exceptions.ParseExeExceptions;

/**
 * <h1>Clase CommandParser</h1>
 * 
 * <p>
 * Comprueba si el comando introducido por el usuario corresponde con un comando
 * existente en la aplicación
 * </p>
 *
 * @author Marina Lozano Lahuerta
 * @author Pablo López Veleiro
 */
public class CommandParser {
	/**
	 * Array con objetos de cada comando existente en la aplicación.
	 */
	private static Command[] availableCommands = { new HelpCommand(), new ResetCommand(), new ExitCommand(),
			new MoveCommand(), new UndoCommand(), new RedoCommand(), new PlayCommand(), new SaveCommand(),
			new LoadCommand() };

	/**
	 * Método abstracto que comprueba si el comando escrito por el usuario
	 * corresponde con algún comando de la aplicación.
	 *
	 * @param commandWords Texto del comando introducido por el usuario
	 * @param in objeto Scanner para leer de consola
	 * @return un objeto del comando correspondiente o null si no corresponde con
	 *         ningún comando.
	 * @throws ParseExeExceptions Lanza un mesaje cuando se produce un error.
	 */
	public static Command parseCommand(String[] commandWords, Scanner in) throws ParseExeExceptions {
		Command command = null;
		for (Command i : availableCommands) {
			if (command == null) {
				command = i.parse(commandWords, in);
			}
		}

		if (command == null)
			throw new ParseExeExceptions(Controller.commandError);

		return command;

	}

	/**
	 * Pide la información de ayuda de cada comando
	 *
	 * @return texto con la información de los comandos.
	 */
	public static String commandHelp() {
		String help = "";
		for (Command i : availableCommands) {
			help += i.helpText() + "\n";
		}

		return help;
	}

}
