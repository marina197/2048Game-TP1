package tp.pr3;

import tp.pr3.logic.multigames.GameRules;

/**
 * <h1>Clase Cell</h1>
 * 
 * <p>Representa cada una de las baldosas del tablero</p>
 *
 * @author Marina Lozano Lahuerta
 * @author Pablo López Veleiro
 */
public class Cell {
    /**
     * Valor de la baldosa
     */
    private int value;

    /**
     * Contructor de la clase Cell
     *
     * @param value valor de la baldosa
     */
    public Cell(int value) {
        this.value = value;
    }

    /**
     * Método accedente para consultar el valor de una baldosa.
     *
     * @return valor de la baldosa
     */
    public int getValue() {
        return value;
    }

    /**
     * Método mutador para modificar el valor de una baldosa.
     *
     * @param value valor de la baldosa
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Comprueba si una baldosa está vacía.
     *
     * @return <ul>
     * <li>true: si la baldosa está vacía</li>
     * <li>false: si la baldosa no está vacía</li> </ul>
     */
    public boolean isEmpty() {
        return value == 0;
    }

    /**
     * Si es posible realiza la fusión
     *
     * @param neighbour baldosa vecina
     * @param rules     reglas del juego actual
     * @return resultado de la fusión, en caso de no haber sido posible devuelve 0.
     */
    public int doMerge(Cell neighbour, GameRules rules) {
        return rules.merge(this, neighbour);
    }

    /**
     * Imprime el valor de la baldosa.
     */
    @Override
    public String toString() {
        return Integer.toString(value);
    }
}