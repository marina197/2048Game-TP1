package tp.pr3.control;

import java.util.Scanner;

import tp.pr3.control.commands.Command;
import tp.pr3.control.commands.CommandParser;
import tp.pr3.exceptions.GameOverException;
import tp.pr3.exceptions.ParseExeExceptions;
import tp.pr3.logic.multigames.Game;

/**
 * <h1>Clase Controller</h1>
 * 
 * <p>
 * Controla la ejecución del juego, preguntando al usuario qué quiere hacer y
 * actualizando la partida de acuerdo a lo que éste indique.
 * </p>
 *
 * @author Marina Lozano Lahuerta
 * @author Pablo López Veleiro
 */
public class Controller {
	/**
	 * Mensaje de error
	 */
	public static final String commandError = "Unknown command. Use 'help' to see the available commands";
	/**
	 * Partida
	 */
	private Game game;
	/**
	 * Objeto Scanner para leer de la consola las órdenes del usuario.
	 */
	private Scanner in;

	/**
	 * Contructor de la clase Controller
	 *
	 * @param game juego
	 * @param in objeto Scanner para leer de consola.
	 */
	public Controller(Game game, Scanner in) {
		this.game = game;
		this.in = in;
	}

	/**
	 * Comienza su ejecución del juego
	 */
	public void run() {
		System.out.println(game);
		boolean terminado = false;
		while (!terminado) {
			
			String command = "Command > ";
			System.out.print(" \n" + command);
			String leido = in.nextLine();
			String[] parts = leido.split(" ");
			parts[0] = parts[0].toUpperCase();

			try {
				Command com = CommandParser.parseCommand(parts, in);
				if (com.execute(game))
					System.out.println(game);
			} catch (ParseExeExceptions pee) {
				System.out.println(pee.getMessage());
			} catch (GameOverException go) {
				System.out.println(game);
				System.out.println(go.getMessage());
				terminado = true;
			}

		}
	}

}