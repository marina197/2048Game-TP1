package tp.pr3.control.commands;

import tp.pr3.exceptions.ParseExeExceptions;
import tp.pr3.logic.multigames.Game;
import tp.pr3.logic.multigames.GameType;
import java.util.Random;
import java.util.Scanner;

/**
 * <h1>Clase PlayCommand</h1>
 * 
 * <p>
 * Clase que ejecuta el comando Play, cambia de juego
 * </p>
 *
 * @author Marina Lozano Lahuerta
 * @author Pablo López Veleiro
 */
public class PlayCommand extends Command {
	/**
	 * Tipo de juego introducido por el usuario
	 */
	private GameType gameType;
	/**
	 * Semilla, por defecto random
	 */
	private int randomSeed = new Random().nextInt(1000);
	/**
	 * Tamaño del tablero, por defecto 4
	 */
	private int boardSize = 4;
	/**
	 * número de baldosas iniciales, por defecto 2
	 */
	private int initialCells = 2;
	/**
	 * Texto con el nombre del comando
	 */
	private static String commandInfo = "play";
	/**
	 * Texto con la descripción del comando
	 */
	private static String helpInfo = "start a new game of one of the game types: " + GameType.externaliseAll();
	/**
	 * objeto Scanner para leer de consola
	 */
	private Scanner in;

	/**
	 * Contructor de la clase PlayCommand
	 */
	public PlayCommand() {
		super(commandInfo + " <game>", helpInfo);
	}

	/**
	 * Pide el tamaño del tablero al usuario, si no introduce un dato se usa el
	 * tamaño por defecto
	 *
	 * @param in objeto Scanner para leer de consola
	 */
	private void pedirSize(Scanner in) {
		String[] leer;
		String string;
		String space = "  ";
		boolean leido = false;
		int num;

		do {
			string = "Please, enter the SIZE of the board: ";
			System.out.print(string);
			leer = in.nextLine().trim().split("\\s+");

			if (leer[0].isEmpty()) {
				string = space + "Using the default size of the board: " + boardSize;
				System.out.println(string);
				leido = true;
			} else {
				try {
					if (leer.length > 1) {
						throw new ParseExeExceptions(space + "Please, provide a single positive integer or press return.");
					} else {
						num = Integer.parseInt(leer[0]);
						if (num <= 0) {
							throw new ParseExeExceptions(space + "The size of the board must be positive.");
						} else {
							boardSize = Integer.parseInt(leer[0]);
							leido = true;
						}
					}
				} catch (NumberFormatException nfe) {
					System.out.println(space + "The size of the board must be a number");
				} catch (ParseExeExceptions pee) {
					System.out.println(pee.getMessage());
				}
			}

		} while (!leido);

	}

	/**
	 * Pide el número de baldosas iniciales al usuario, si no introduce un dato se
	 * usa el número de baldosas por defecto por defecto
	 *
	 * @param in objeto Scanner para leer de consola
	 */
	private void pedirCells(Scanner in) {
		String[] leer;
		String string;
		String space = "  ";
		boolean leido = false;
		int num;

		do {
			string = "Please, enter the number of INITIAL CELLS: ";
			System.out.print(string);
			leer = in.nextLine().trim().split("\\s+");

			if (leer[0].isEmpty()) {
				string = space + "Using the default number of initial cells: " + initialCells;
				System.out.println(string);
				leido = true;
			} else {
				try {
					if (leer.length > 1) {
						throw new ParseExeExceptions (space + "Please, provide a single positive integer or press return.");
					}
					if (leer.length == 1) {
						num = Integer.parseInt(leer[0]);
						if (num <= 0) {
							throw new ParseExeExceptions (space + "The number of initial cells must be positive.");
						} else {
							if (num > boardSize * boardSize) {
								throw new ParseExeExceptions(space
										+ "The number of initial cells must be less than the number of cells on the board ("
										+ boardSize * boardSize + ")");
							} else {
								initialCells = Integer.parseInt(leer[0]);
								leido = true;
							}
						}

					}
				} catch (NumberFormatException nfe) {
					System.out.println("Initial cells must be a number");
					leido = false;
				} catch (ParseExeExceptions pee) {
					System.out.println(pee.getMessage());
				}

			}

		} while (!leido);
	}

	/**
	 * Pide la semilla al usuario, si no introduce un dato se usa la semilla por
	 * defecto
	 *
	 * @param in objeto Scanner para leer de consola
	 */
	private void pedirSeed(Scanner in) {
		String[] leer;
		String string, space = "  ";
		boolean leido = false;
		int num;

		do {
			string = "Please, enter the SEED for the pseudo-random number generator: ";
			System.out.print(string);
			leer = in.nextLine().trim().split("\\s+");

			if (leer[0].isEmpty()) {
				string = space + "Using the default seed for the pseudo-random number generator: " + randomSeed;
				System.out.println(string);
				leido = true;
			} else {
				try {
					if (leer.length > 1) {
						throw new ParseExeExceptions(
								space + "Please, provide a single positive integer or press return.");
					} else {
						num = Integer.parseInt(leer[0]);
						if (num <= 0) {
							throw new ParseExeExceptions(space + "The SEED must be positive.");
						} else {
							randomSeed = Integer.parseInt(leer[0]);
							leido = true;
						}
					}
				} catch (NumberFormatException nfe) {
					System.out.println("Initial cells must be a number");
				} catch (ParseExeExceptions pee) {
					System.out.println(pee.getMessage());
				}
			}

		} while (!leido);
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public boolean execute(Game game) {
		System.out.println("*** You have chosen to play the game: " + gameType + "***");

		pedirSize(in);
		pedirCells(in);
		pedirSeed(in);

		game.changeGame(gameType, initialCells, boardSize, randomSeed);
		return true;
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public Command parse(String[] commandWords, Scanner in) throws ParseExeExceptions {
		PlayCommand play;

		if (commandWords[0].contentEquals(commandInfo.toUpperCase())) {
			if (commandWords.length < 2) {
				throw new ParseExeExceptions("Play must be followed by a game type: " + GameType.externaliseAll());
			} else {
				play = new PlayCommand();
				commandWords[1] = commandWords[1].toLowerCase();
				play.gameType = GameType.parse(commandWords[1]);
				play.in = in;
				if (play.gameType == null) {
					throw new ParseExeExceptions("Unknown type for play command.");
				} else {
					return play;
				}
			}

		} else
			return null;
	}

}