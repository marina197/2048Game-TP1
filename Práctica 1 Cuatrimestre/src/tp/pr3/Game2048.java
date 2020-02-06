package tp.pr3;

import java.util.Random;
import java.util.Scanner;

import tp.pr3.control.Controller;
import tp.pr3.exceptions.GameOverException;
import tp.pr3.exceptions.ParseExeExceptions;
import tp.pr3.logic.multigames.Game;
import tp.pr3.logic.multigames.GameType;

/**
 * <h1>Clase Game2048</h1>
 * 
 * <p>
 * Crea un juego con los datos proporcionados
 * </p>
 *
 * @author Marina Lozano Lahuerta
 * @author Pablo López Veleiro
 */
public class Game2048 {
	/**
	 * Método main donde se crean el juego inicial y el controller
	 *
	 * @param args datos sobre el tamaño del tablero, número de baldosas iniciales y
	 *             la semilla random
	 * @throws GameOverException Excepción lanzada para finalizar el juego.
	 * @throws ParseExeExceptions Lanza un mesaje cuando se produce un error.
	 */
	public static void main(String[] args) throws ParseExeExceptions, GameOverException {

		int size = 0, initCell = 0;
		long seed = 0;
		Scanner leer = new Scanner(System.in);

		try {
			size = Integer.parseInt(args[0]);
			initCell = Integer.parseInt(args[1]);

			if (args.length > 2) {
				// Para crear la misma secuencia de números aleatorios
				seed = Long.parseLong(args[2]);
			} else {
				seed = new Random().nextInt(1000);
			}

		} catch (NumberFormatException nfe) {
			System.out.println("The command-line arguments must be numbers");
			System.exit(0);
		}
		
		Game g = new Game(GameType.ORIG, seed, size, initCell);
		Controller c = new Controller(g, leer);
		c.run();
		System.exit(0);
	}

}