package tp.pr3.util;

import java.util.Random;

/**
 * <h1>Clase ArrayAsList</h1>
 * 
 * <p>Generador de arrays de elementos del tipo objeto</p>
 *
 * @author Marina Lozano Lahuerta
 * @author Pablo López Veleiro
 */
public class ArrayAsList {
	/**
	 * capacidad del array
	 */
    private final static int CAPACIDAD = 100;
    /**
     * array de objetos
     */
    private Object[] arrayAsList; 
    /**
     * Indica el número de elementos, coincide con la 
     * primera posición libre del array
     */
    private int contador = 0;

    /**
     * El constructor sin argumentos crea el array.
     */
    public ArrayAsList() {
        arrayAsList = new Object[CAPACIDAD];
        
    }
    /**
     * Devuelve el objeto de la posición indicada.
     * 
     * @param pos posición de la que obtener el objeto
     * @return objeto almacenado en el array
     */
    private Object get(int pos) {
        return arrayAsList[pos];
    }

    /**
     * Comprueba si la lista está llena.
     * 
     * @return <ul>
	 *         <li>true: si la lista está llena</li>
	 *         <li>false: si la lista no está llena</li>
	 *         </ul>
     */
    public boolean llena() {
        return contador == CAPACIDAD;
    }

    /**
     * Comprueba si la lista está vacía.
     * 
     * @return <ul>
	 *         <li>true: si la lista está vacía</li>
	 *         <li>false: si la lista no está vacía</li>
	 *         </ul>
     */
    public boolean vacia() {
        return contador == 0;
    }

    /**
     * Devuelve el tamaño actual de la lista.
     * 
     * @return tamaño actual de la lista
     */
    public int size() {
        return contador;
    }

    /**
     * Indica si es posible insertar un objeto a la lista. Si es posible lo inserta.
     * 
     * @param o objeto a insertar
     * @return <ul>
	 *         <li>true: si es posible insertar un objeto en la lista</li>
	 *         <li>false: si no es posible insertar un objeto en la lista</li>
	 *         </ul>
     */
    public boolean insertar(Object o) {
        if (llena())
            return false;
        arrayAsList[contador] = o;
        contador++;
        return true;
    }

    /**
     * Shuffle the list of objects.
     * This method is static in order to be similar to the "shuffle ()" method of
     * the standard library class "Collections ".
     * 
     * @param list lista de posiciones vacías
     * @param random Comportamiento aleatorio del juego
     */
    public static void shuffle(ArrayAsList list, Random random) {
        for (int i = list.size(); i > 1; i--) {
            swap(list.arrayAsList, i - 1, random.nextInt(i));
        }
    }

    /**
     * Chose a random position of the list.
     * This method is static in order to be similar to the "shuffle ()" method.
     * 
     * @param list lista de posiciones vacías
     * @param random Comportamiento aleatorio del juego
     * @return objeto de una posición aleatoria
     */
    public static Object choice(ArrayAsList list, Random random) {
        return list.get(random.nextInt(list.size()));
    }
    
    /**
     * swap the positions of the list.
     * 
     * @param anArray array de objetos
     * @param i posición de la lista
     * @param j posición de la lista
     */
    private static void swap(Object[] anArray, int i, int j) {
        Object temp = anArray[i];
        anArray[i] = anArray[j];
        anArray[j] = temp;
    }

}