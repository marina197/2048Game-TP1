package tp.pr3.control.commands;

import java.util.Scanner;

/**
 * <h1>Clase NoParamsCommand</h1>
 * 
 * <p>
 * Comprueba si el comando introducido por el usuario corresponde con un comando
 * sin parametros disponibles en la aplicación.
 * </p>
 *
 * @author Marina Lozano Lahuerta
 * @author Pablo López Veleiro
 */
public abstract class NoParamsCommand extends Command {

	/**
	 * Contructor de la clase NoParamsCommand
	 *
	 * @param commandInfo Texto del nombre del comando
	 * @param helpInfo Texto de la descripción del comando
	 */
	public NoParamsCommand(String commandInfo, String helpInfo) {
		super(commandInfo, helpInfo);
	}

	/**
	 * Devuelve un objeto de la clase correspondiente.
	 *
	 * @return Objeto del comando indicado por el usuario
	 */
	public abstract Command createCommand();

	@Override
	/**
	 * {@inheritDoc}
	 */
	public Command parse(String[] commandWords, Scanner in) {
		String commandN = commandName.toUpperCase();

		if (commandWords[0].contentEquals(commandN)) {
			return createCommand();
		} else
			return null;
	}

}