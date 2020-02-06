package tp.pr3.logic.multigames;

/**
 * <h1>Clase GameType</h1>
 * 
 * <p>
 * Enumerado que representa el tipo de juego
 * </p>
 *
 * @author Marina Lozano Lahuerta
 * @author Pablo López Veleiro
 */
public enum GameType {
	ORIG("2048, original version", "original", new Rules2048()), FIB("2048, Fibonacci version", "fib",
			new RulesFib()), INV("2048, inverse version", "inverse", new RulesInverse());

	/**
	 * Nombre del juego.
	 */
	private String userFriendlyName;
	/**
	 * Nombre del juego que introduce el usuario.
	 */
	private String parameterName;
	/**
	 * objeto reglas del juego actual
	 */
	private GameRules correspondingRules;

	/**
	 * Constructor de la clase
	 * 
	 * @param friendly nombre del juego.
	 * @param param nombre del juego dado por el usuario.
	 * @param rules reglas del juego actual.
	 */
	private GameType(String friendly, String param, GameRules rules) {
		userFriendlyName = friendly;
		parameterName = param;
		correspondingRules = rules;
	}

	// precondition: param string contains only lower−case characters
	// used in PlayCommand and Game, in parse method and load method, respectively
	/**
	 * Método usado en PlayCommand y Game, en los métodos parse y load respectivamente
	 * para comprobar si existe en el enumerado
	 * 
	 * @param param nombre del parámetro proporcionado por el usuario.
	 * @return tipo de juego correspondiente.
	 */
	public static GameType parse(String param) {
		for (GameType gameType : GameType.values()) {
			if (gameType.parameterName.equals(param))
				return gameType;
		}
		return null;
	}

	// used in PlayCommand to build help message, and in parse method exception msg
	/**
	 * Método usado en PlayCommand para construir el mensaje de "help" y en el 
	 * método parse 
	 * 
	 * @return nombre de todos los tipos de juego disponibles.
	 */
	public static String externaliseAll() {
		String s = "";
		for (GameType type : GameType.values())
			s = s + " " + type.parameterName + ",";
		return s.substring(1, s.length() - 1);
	}

	/**
	 * Método usado en Game al construir un objeto y al ejecutar PlayCommand
	 * que devuelve las reglas del juego actual.
	 * 
	 * @return reglas correspondientes al juego actual.
	 */
	public GameRules getRules() {
		return correspondingRules;
	}

	/**
	 * Método que devuelve el nombre de un tipo de juego del enumerado,
	 * usado en el método "store" de Game.
	 * 
	 * @return nombre del juego actual
	 */
	public String externalise() {
		return parameterName;
	}

	/**
	 * used PlayCommand and LoadCommand, in parse methods
	 * in ack message and success message, respectively
	 */
	public String toString() {
		return userFriendlyName;
	}

}
