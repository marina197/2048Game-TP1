package tp.pr3.control.commands;

import java.util.Scanner;
import tp.pr3.exceptions.GameOverException;
import tp.pr3.exceptions.ParseExeExceptions;
import tp.pr3.logic.multigames.Game;

/**
 * <h1>Clase Command</h1>
 *
 * @author Marina Lozano Lahuerta
 * @author Pablo López Veleiro
 */
public abstract class Command {
    /**
     * Texto de la descripción del comando
     */
    private String helpText;
    /**
     * Texto del nombre del comando
     */
    private String commandText;
    /**
     * Texto del nombre del comando introducido por el usuario
     */
    protected final String commandName;

    /**
     * Constructor de la clase Command
     *
     * @param commandInfo Texto del nombre del comando
     * @param helpInfo    Texto de la descripción del comando
     */
    public Command(String commandInfo, String helpInfo) {
        commandText = commandInfo;
        helpText = helpInfo;
        String[] commandInfoWords = commandText.split("\\s+");
        commandName = commandInfoWords[0];
    }

    /**
     * Método abstracto que ejecuta el comando.
     * 
     * @param game juego actual
     * @return <ul>
     * <li>true: si se imprime el tablero</li>
     * <li>false: si no se tiene que imprimir el tablero</li> </ul>
     * @throws GameOverException Excepción lanzada para finalizar el juego.
     * @throws ParseExeExceptions Lanza un mesaje cuando se produce un error.
     */
    public abstract boolean execute(Game game) throws GameOverException, ParseExeExceptions;

    /**
     * Método que comprueba si el comando escrito por el usuario corresponde con
     * algún comando de la aplicación.
     *
     * @param commandWords Texto del comando introducido por el usuario
     * @param in objeto Scanner para leer de consola
     * @return un objeto del comando correspondiente o null si no corresponde con
     * ningún comando.
     * @throws ParseExeExceptions Lanza un mesaje cuando se produce un error.
     */
    public abstract Command parse(String[] commandWords, Scanner in) throws ParseExeExceptions;

    /**
     * Devuelve el texto con la ayuda del juego.
     *
     * @return texto con la información de los comandos
     */
    public String helpText() {
        return "  " + commandText + ": " + helpText;
    }
}