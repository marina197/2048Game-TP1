package tp.pr3.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * <h1>Clase MyStringUtils</h1>
 * 
 * <p>
 * Ayuda para imprimir en pantalla el tablero y a comprobar si el nombre de un
 * archivo es válido.
 * </p>
 *
 * @author Profesor
 */
public class MyStringUtils {

	public static final int MARGIN_SIZE = 4;
	
	/**
	 * Repite el elemento dado el número de veces establecido en los comandos
	 * 
	 * @param elmnt elemento a repetir
	 * @param length número de veces que hay que repetir el elemento
	 * @return Cadena de texto que incluye el elemento el número de veces que se ha establecido
	 */
	public static String repeat(String elmnt, int length) {
		String result = "";
		for (int i = 0; i < length; i++) {
			result += elmnt;
		}
		return result;
	}

	/**
	 * Centra el texto
	 * 
	 * @param text texto a centrar
	 * @param len tamaño total disponible
	 * @return texto centrado dado el tamaño total disponible
	 */
	public static String centre(String text, int len) {
		String out = String.format("%" + len + "s%s%" + len + "s", "", text, "");
		float mid = (out.length() / 2);
		float start = mid - (len / 2);
		float end = start + len;
		return out.substring((int) start, (int) end);
	}
	
	// Used to exist method: org.eclipse.core.internal.resources.OS.isNameValid(filename).
	// This method is not completely reliable since exception could also be thrown due to:
	// incorrect permissions , no space on disk , problem accessing the device ,...
	/**
	 * Comprueba si el archivo se puede crear.
	 * 
	 * @param filename nombre del archivo
	 * @return <ul>
     * <li>true: si el nombre del archivo es válido</li>
     * <li>false: si el nombre del archivo no es válido</li> </ul>
	 */
	public static boolean validFileName(String filename) {
		File file = new File(filename);
		if (file.exists()) {
			return canWriteLocal(file);
		} else {
			try {
				file.createNewFile();
				file.delete();
				return true;
			} catch (Exception e) {
				return false;
			}
		}
	}
	
	/**
	 * Comprueba se puede escribir en el archivo.
	 * 
	 * @param file archivo
	 * @return <ul>
     * <li>true: si el archivo se puede escribir</li>
     * <li>false: si el archivo no se puede escribir</li> </ul>
	 */
	public static boolean canWriteLocal(File file) {
		// works OK on Linux but not on Windows (apparently!)
		if (!file.canWrite()) {
			return false;
		}
		// works on Windows
		try {
			new FileOutputStream(file, true).close();
		} catch (IOException e) {
			return false;
		}
		return true;
	}
	
}