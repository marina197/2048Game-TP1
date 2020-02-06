package tp.pr3;

/**
 * <h1>Clase Position</h1>
 * 
 * <p>Permite representar posiciones del tablero</p>
 *
 * @author Marina Lozano Lahuerta
 * @author Pablo López Veleiro
 */
public class Position {
    /**
     * Fila
     */
    private int x;
    /**
     * Columna
     */
    private int y;

    /**
     * Constuctor de la clase Position
     *
     * @param x fila del tablero
     * @param y columna del tablero
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Devuelve la fila del tablero
     *
     * @return fila del tablero
     */
    public int getX() {
        return x;
    }

    /**
     * Devuelve la columna del tablero
     *
     * @return columna del tablero
     */
    public int getY() {
        return y;
    }

    /**
     * Devuelve la posición de la baldosa vecina en la dirección indicada.
     *
     * @param dir  dirección indicada
     * @param size tamaño del tablero
     * @return posición de la baldosa vecina en la dirección indicada.
     */
    public Position neighbour(Direction dir, int size) {
        Position pos = null;

        switch (dir) {
            case UP: {
                if (x > 0)
                    pos = new Position(x - 1, y);
            }
            break;
            case DOWN: {
                if (x < size - 1)
                    pos = new Position(x + 1, y);
            }
            break;
            case RIGHT: {
                if (y < size - 1)
                    pos = new Position(x, y + 1);
            }
            break;
            case LEFT: {
                if (y > 0)
                    pos = new Position(x, y - 1);
            }
            break;
        }

        return pos;
    }

}