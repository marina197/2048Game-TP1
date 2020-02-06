package tp.pr3;

/**
 * <h1>Clase Direction</h1>
 * 
 * <p>Enumerado que representa la dirección de un movimiento</p>
 *
 * @author Marina Lozano Lahuerta
 * @author Pablo López Veleiro
 */
public enum Direction {
    UP("up"), DOWN("down"), RIGHT("right"), LEFT("left");
 
    /**
     * Nombre del parametro move.
     */
    private String parameterName;
    
    /**
     * Constructor de la clase
     * 
     * @param param movimiento indicado por el usuario.
     */
	private Direction(String param) {
		parameterName = param;
	}
    
	/**
	 * Comprueba si la dirección está entre las enumeradas.
	 * 
	 * @param param dirección indicado por el usuario.
	 * @return dirección del movimiento
	 */
	public static Direction parse(String param) {
		for (Direction dir : Direction.values()) {
			if (dir.parameterName.equals(param))
				return dir;
		}
		return null;
	}
	
	/**
	 * Devuelve todas las direcciones disponibles.
	 * 
	 * @return string con las direcciones disponibles.
	 */
	public static String externaliseAll() {
		String s = "";
		for (Direction dir : Direction.values())
			s = s + " " + dir.parameterName + ",";
		return s.substring(1, s.length() - 1);
	}
}