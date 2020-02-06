package tp.pr3.control.commands;

import java.io.*;
import java.util.Scanner;
import tp.pr3.exceptions.ParseExeExceptions;
import tp.pr3.logic.multigames.Game;
import tp.pr3.util.MyStringUtils;

/**
 * <h1>Clase SaveCommand</h1>
 * 
 * <p>
 * Clase que ejecuta el comando Save, guarda en un fichero el estado completo de
 * una partida.
 * </p>
 *
 * @author Marina Lozano Lahuerta
 * @author Pablo López Veleiro
 */
public class SaveCommand extends Command {
	/**
	 * Indica si el nombre del archivo es válido.
	 */
	private boolean filename_confirmed;
	/**
	 * Mensaje de error si el nombre del archivo dado ya existe.
	 */
	public static final String filenameInUseMsg = "The file already exists, do you want to overwrite it ? (Y/N)";
	/**
	 * Nombre del archivo del que cargar el juego.
	 */
	private String filename;
	/**
	 * Texto con el nombre del comando
	 */
	private static String commandInfo = "save";
	/**
	 * Texto con la descripción del comando
	 */
	private static String helpInfo = "saves a game in a file";

	/**
	 * Contructor de la clase SaveCommand
	 */
	public SaveCommand() {
		super(commandInfo + " <filename>", helpInfo);
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public boolean execute(Game game) throws ParseExeExceptions {
		String string = "This file stores a saved 2048 game\r\n" + "";
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
			bw.write(string);
			game.store(bw);
			bw.flush();
		} catch (IOException ioe) {
			throw new ParseExeExceptions("No se ha podido guardar el juego.");
		}
		System.out.println("Game successfully saved to file; use load command to reload it.");
		return false;
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public Command parse(String[] commandWords, Scanner in) throws ParseExeExceptions {
		SaveCommand save;

		if (commandWords[0].contentEquals(commandInfo.toUpperCase())) {
			if (commandWords.length < 2) {
				throw new ParseExeExceptions("Save must be followed by a filename.");
			} else {
				if (commandWords.length > 2)
					throw new ParseExeExceptions("Invalid filename: the filename contains spaces.");
				else {
					save = new SaveCommand();
					save.filename = confirmFileNameStringForWrite(commandWords[1], in);
					return save;
				}
			}
		} else {
			return null;
		}
	}

	/**
	 * Comprueba si se puede escribir en el archivo.
	 * 
	 * @param ﬁlenameString nombre del archivo proporcionado por el usuario.
	 * @param in objeto Scanner para leer de consola
	 * @return nombre del archivo donde se va a guardar la partida.
	 * @throws ParseExeExceptions
	 */
	private String confirmFileNameStringForWrite(String filenameString, Scanner in) throws ParseExeExceptions {
		String loadName = filenameString;
		filename_confirmed = false;

		while (!filename_confirmed) {
			if (MyStringUtils.validFileName(loadName)) {
				File ﬁle = new File(loadName);
				if (!ﬁle.exists()) {
					filename_confirmed = true;
				} else {
					loadName = getLoadName(filenameString, in);
					if (loadName == filenameString)
						filename_confirmed = true;
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

	/**
	 * Proporciona el nombre del archivo donde escribir el juego.
	 * 
	 * @param filenameString nombre del archivo proporcionado por el usuario.
	 * @param in objeto Scanner para leer de consola
	 * @return nombre del archivo en el que escribir.
	 * @throws ParseExeExceptions Lanza un mesaje cuando se produce un error.
	 */
	public String getLoadName(String filenameString, Scanner in) throws ParseExeExceptions {
		String newFilename = null;
		boolean yesOrNo = false;
		
		while (!yesOrNo) {
			System.out.print(filenameInUseMsg + ": ");
			String[] responseYorN = in.nextLine().toLowerCase().trim().split("\\s+");
			if (responseYorN.length == 1) {
				switch (responseYorN[0]) {
				case "y":
					yesOrNo = true;
					newFilename = filenameString;
					break;
				case "n":
					yesOrNo = true;
					String[] s;
					System.out.print("Please enter another filename: ");
					s = in.nextLine().trim().split("\\s+");
					if (s.length > 2)
						throw new ParseExeExceptions("Invalid filename: the filename contains spaces.");
					newFilename = s[0];
					break;
				default:
					System.out.println("Please answer ’Y’ or ’N’");
					break;
				}
			} else {
				System.out.println("Please answer ’Y’ or ’N’");

			}
		}
		return newFilename;
	}

}
