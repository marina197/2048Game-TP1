package tp.pr3.control.commands;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import tp.pr3.exceptions.ParseExeExceptions;
import tp.pr3.logic.multigames.Game;
import tp.pr3.logic.multigames.GameType;
import tp.pr3.util.MyStringUtils;

/**
 * <h1>Clase LoadCommand</h1>
 * 
 * <p>
 * Clase que ejecuta el comando Load, carga el estado completo de una partida
 * almacenada previamente en un fichero.
 * </p>
 *
 * @author Marina Lozano Lahuerta
 * @author Pablo López Veleiro
 */
public class LoadCommand extends Command {
	/**
	 * Indica si el nombre del archivo es válido.
	 */
	private boolean filename_confirmed;
	/**
	 * Nombre del archivo del que cargar el juego.
	 */
	private String filename;
	/**
	 * tipo de juego a cargar.
	 */
	private GameType gtype;
	/**
	 * Texto con el nombre del comando
	 */
	private static String commandInfo = "load";
	/**
	 * Texto con la descripción del comando
	 */
	private static String helpInfo = "loads a game from a file.";

	/**
	 * Contructor de la clase LoadCommand
	 */
	public LoadCommand() {
		super(commandInfo + " <filename>", helpInfo);
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public boolean execute(Game game) throws ParseExeExceptions {
		String s;
		String string = "This file stores a saved 2048 game";

		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			s = br.readLine();
			if (s.equals(string)) {
				gtype = game.load(br);
			} else
				throw new ParseExeExceptions("Load failed: invalid file format.");
			br.close();
		} catch (IOException io) {
			throw new ParseExeExceptions("Load failed: invalid file format.");
		}
		
		System.out.println("Game successfully loaded from file; " + gtype + "\n");
		return true;
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public Command parse(String[] commandWords, Scanner in) throws ParseExeExceptions {
		LoadCommand load;

		if (commandWords[0].contentEquals(commandInfo.toUpperCase())) {
			if (commandWords.length < 2) {
				throw new ParseExeExceptions("Load must be followed by a filename.");
			} else {
				if (commandWords.length > 2)
					throw new ParseExeExceptions("Invalid filename: the filename contains spaces.");
				else {
					load = new LoadCommand();
					load.filename = confirmFileNameStringForWrite(commandWords[1], in);
					return load;
				}
			}
		} else {
			return null;
		}
	}

	/**
	 * Comprueba si se puede cargar de un archivo
	 * 
	 * @param ﬁlenameString nombre del archivo desde donde cargar una partida.
	 * @param in objeto Scanner para leer de consola
	 * @return nombre del archivo del que cargar una partida.
	 * @throws ParseExeExceptions
	 */
	private String confirmFileNameStringForWrite(String filenameString, Scanner in) throws ParseExeExceptions {
		String loadName = filenameString;
		filename_confirmed = false;

		while (!filename_confirmed) {
			if (MyStringUtils.validFileName(loadName)) {
				File file = new File(loadName);
				if (file.exists()) {
					filename_confirmed = true;
				} else {
					throw new ParseExeExceptions("File not found");
				}
			} else {
				for (int i = 0; i < filenameString.length(); i++) {
					char caracter = filenameString.charAt(i);
					if (!Character.isLetterOrDigit(caracter)) {
						throw new ParseExeExceptions("Invalid filename: the filename contains invalid characters.");

					}
				}
			}
		}
		return loadName;
	}

}
