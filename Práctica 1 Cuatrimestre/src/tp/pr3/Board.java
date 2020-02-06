package tp.pr3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import tp.pr3.logic.multigames.GameRules;
import tp.pr3.util.MyStringUtils;

/**
 * <h1>Clase Board</h1>
 * 
 * <p>
 * Almacena el estado de un tablero y proporciona métodos necesarios para la
 * gestión de dicho estado
 * </p>
 *
 * @author Marina Lozano Lahuerta
 * @author Pablo López Veleiro
 */
public class Board {
    /**
     * Tablero formado por un array bidimensional de celdas.
     */
    private Cell[][] _board;
    /**
     * Tamaño del tablero (dimensión)
     */
    private int _boardSize;

    /**
     * Contructora de la clase Board con un único argumento que fija la dimensión
     * del tablero e instancia el atributo _board
     *
     * @param _boardSize tamaño del tablero
     */
    public Board(int _boardSize) {
        this._boardSize = _boardSize;
        this._board = new Cell[_boardSize][_boardSize];

        // Inicializa array "_board" creando objetos Cell con value 0.
        for (int i = 0; i < this._boardSize; i++) {
            for (int j = 0; j < this._boardSize; j++) {
                _board[i][j] = new Cell(0);
            }
        }
    }

    /**
     * Método para modificar el valor de una baldosa en la posición pos
     * con el valor value
     *
     * @param pos   posición de la baldosa
     * @param value valor de la baldosa
     */
    public void setCell(Position pos, int value) {
        _board[pos.getX()][pos.getY()].setValue(value);
    }

    /**
     * Método para comprobar si la posición p del tablero _board está vacía.
     *
     * @param p posición del tablero
     * @return <ul>
     * <li>true: si la posición del tablero está vacía</li>
     * <li>false: si la posición del tablero no está vacía</li> </ul>
     */
    public boolean isEmpty(Position p) {
        return _board[p.getX()][p.getY()].isEmpty();
    }

    /**
     * Método accedente para consultar el tamaño del tablero
     *
     * @return tamaño del tablero
     */
    public int get_boardSize() {
        return _boardSize;
    }
    
    /**
     * Realiza la acción de desplazar y fusionar hacia la dirección RIGHT.
     *
     * @param rules reglas del juego actual
     * @return objeto MoveResults con los resultados del movimiento.
     */
    private MoveResults moveRight(GameRules rules) {
        Position pos;
        boolean moved = false; // Indica si se ha producido un desplazamiento o fusión. Inicializada a false.
        int points = 0; // Points.
        boolean fusionado = false;

        int x, y;
        for (int i = 0; i < _boardSize; i++) {
            for (int j = _boardSize - 1; j >= 0; j--) {

                // DESPLAZAMIENTO
                if (_board[i][j].isEmpty() && j > 0) { // Si la baldosa está vacía es posible realizar un movimiento.
                    pos = new Position(i, j);
                    int z = j; // Iterador utilizado para, en caso de no haber casillas con valor, salir del
                    // bucle.

                    // Actualiza 'pos' con la posición de la casilla vecina (de la izquierda).
                    do {
                        pos = pos.neighbour(Direction.LEFT, _boardSize);
                        x = pos.getX();
                        y = pos.getY();
                        z--;
                    } while (_board[x][y].isEmpty() && z > 0);
                    /*
                     * Sale del bucle cuando encuentra una casilla no vacía o cuando no hay
                     * movimientos posibles puesto que no hay casillas no vacías.
                     */

                    // Desplazamiento
                    if (!_board[x][y].isEmpty()) { // Si hay alguna casilla no vacía se desplaza hacia la derecha.
                        _board[i][j].setValue(_board[x][y].getValue());
                        setCell(pos, 0);
                        moved = true;
                    }
                }

                // Fusión
                if (fusionado)
                    fusionado = false;
                else {
                    if (j < _boardSize - 1) {
                        int fusion = _board[i][j].doMerge(_board[i][j + 1], rules);
                        if (fusion != 0) {
                            moved = true;
                            points += rules.points(fusion);
                            j++; // Se aumenta para que se quede en la posición actual puesto que esta casilla ha
                            // pasado a estar vacía.
                            fusionado = true;
                        }
                    }
                }

            }
        }
        return new MoveResults(moved, points);
    }


    /**
     * Comprueba si en la posición pos la fusión es posible hacia la dirección
     * RIGHT.
     *
     * @param pos   posición de la baldosa de la que se quiere comprobar
     *              si la fusión es posible
     * @param rules reglas del juego actual
     * @return <ul>
     * <li>true: si la fusión es posible</li>
     * <li>false: si la baldosa fusión no es posible</li> </ul>
     */
    private boolean fusionPosibleD(Position pos, GameRules rules) {
        return (pos.getY() < _boardSize - 1) && (rules.canMergeNeighbours(_board[pos.getX()][pos.getY()], _board[pos.getX()][pos.getY() + 1]));
    }

    /**
     * Método que comprueba si la fusión es posible en la posición y la dirección
     * indicadas.
     *
     * @param pos   posición de la baldosa
     * @param dir   dirección indicada
     * @param rules reglas del juego actual
     * @return <ul>
     * <li>true: si la fusión es posible</li>
     * <li>false: si la baldosa fusión no es posible</li> </ul>
     */
    public boolean fusionPosible(Position pos, Direction dir, GameRules rules) {
        boolean posible = false;
        switch (dir) {
            case UP:
                transponerD();
                posible = fusionPosibleD(pos, rules);
                transponerI();
                break;
            case RIGHT:
                posible = fusionPosibleD(pos, rules);
                break;
            case LEFT:
                reflejar();
                posible = fusionPosibleD(pos, rules);
                reflejar();
                break;
            case DOWN:
                transponerI();
                posible = fusionPosibleD(pos, rules);
                transponerD();
                break;
        }
        return posible;
    }

    /**
     * Copia las baldosas del tablero en un array bidimensional.
     *
     * @return Devuelve el array bidimensional con la copia.
     */
    private Cell[][] copyCells() {
        Cell[][] _boardT = new Cell[_boardSize][_boardSize];

        for (int x = 0; x < _boardSize; x++) {
            for (int y = 0; y < _boardSize; y++) {
                _boardT[x][y] = _board[x][y];
            }
        }
        return _boardT;
    }

    /**
     * Transpone el tablero hacia la derecha
     */
    private void transponerD() {
        Cell[][] _boardT = copyCells();
        /*
         * i=0 , j=0 ; x=0, y=3
         * i=0 , j=1 ; x=1, y=3
         * i=0 , j=2 ; x=2, y=3
         * i=0 , j=3 ; x=3, y=3
         *
         * i=1 , j=0 ; x=0, y=2
         * i=1 , j=1 ; x=1, y=2
         * i=1 , j=2 ; x=2, y=2
         * i=1 , j=3 ; x=3, y=2
         *
         * i=2 , j=0 ; x=0, y=1
         * i=2 , j=1 ; x=1, y=1
         * i=2 , j=2 ; x=2, y=1
         * i=2 , j=3 ; x=3, y=1
         *
         * i=3 , j=0 ; x=0, y=0
         * i=3 , j=1 ; x=1, y=0
         * i=3 , j=2 ; x=2, y=0
         * i=3 , j=3 ; x=3, y=0
         */
        int z = _boardSize - 1;
        for (int i = 0; i < _boardSize; i++) {
            for (int j = 0; j < _boardSize; j++) {
                _board[j][z] = _boardT[i][j];
            }
            z--;
        }

    }

    /**
     * Transpone el tablero hacia la izquierda
     */
	private void transponerI() {
		Cell[][] _boardT = copyCells();

		int z;
		for (int i = 0; i < _boardSize; i++) {
			z = _boardSize - 1;
			for (int j = 0; j < _boardSize; j++) {
				_board[z][i] = _boardT[i][j];
				z--;
			}
		}
	}

    /**
     * Refleja el tablero.
     */
	private void reflejar() {
		Cell[][] _boardT = copyCells();

		int z;
		for (int i = 0; i < _boardSize; i++) {
			z = _boardSize - 1;
			for (int j = 0; j < _boardSize; j++) {
				_board[i][z] = _boardT[i][j];
				z--;
			}
		}
	}


    /**
     * Método que ejecuta las dos primeras acciones de un movimiento (desplazar y
     * fusionar)
     *
     * @param dir   dirección en la que desplazar
     * @param rules reglas del juego actual
     * @return objeto MoveResults con los resultados del movimiento
     */
    public MoveResults executeMove(Direction dir, GameRules rules) {
        MoveResults result = null;
        switch (dir) {
            case UP:
                transponerD();
                result = moveRight(rules);
                transponerI();
                break;
            case RIGHT:
                result = moveRight(rules);
                break;
            case LEFT:
                reflejar();
                result = moveRight(rules);
                reflejar();
                break;
            case DOWN:
                transponerI();
                result = moveRight(rules);
                transponerD();
                break;
        }

        return result;
    }

    /**
     * Produce la representación compacta a partir del estado del tablero actual
     *
     * @return array bidimensional con la representación del tablero actual
     */
	public int[][] getState() {
		int[][] aState = new int[_boardSize][_boardSize];

		for (int i = 0; i < _boardSize; i++) {
			for (int j = 0; j < _boardSize; j++) {
				aState[i][j] = _board[i][j].getValue();
			}
		}

		return aState;
	}

    /**
     * Establece el estado del tablero actual a patir de la representación
     * compacta pasada como argumento.
     *
     * @param aState representación compacta del estado del tablero.
     */
	public void setState(int[][] aState) {
		for (int i = 0; i < _boardSize; i++) {
			for (int j = 0; j < _boardSize; j++) {
				_board[i][j].setValue(aState[i][j]);
			}
		}
	}

    /**
     * Método que devuelve el valor máximo del tablero.
     *
     * @return valor máximo de las baldosas del tablero
     */
	public int maxValue() {
		int max = 0;
		for (int i = 0; i < _boardSize; i++) {
			for (int j = 0; j < _boardSize; j++)
				if (_board[i][j].getValue() > max)
					max = _board[i][j].getValue();
		}
		return max;
	}

    /**
     * Método que devuelve el valor mínimo del tablero de las
     * baldosas no vacías.
     *
     * @return el valor mínimo del tablero de las baldosas no vacías.
     */
	public int minValue() {
		int min = 2048;// Valor máximo posible en el juego inverse.
		for (int i = 0; i < _boardSize; i++) {
			for (int j = 0; j < _boardSize; j++) {
				if (_board[i][j].getValue() < min && _board[i][j].getValue() != 0) {
					min = _board[i][j].getValue();
				}
			}
		}
		return min;
	}
    
    /**
     * Método que escribe en un fichero el tablero actual
     * 
     * @param bw buffer para escribir caracteres.
     * @throws IOException Excepción generada por un error en la escritura.
     */
	public void store(BufferedWriter bw) throws IOException {
		bw.newLine();
		for (int i = 0; i < _boardSize; i++) {
			for (int j = 0; j < _boardSize; j++) {
				bw.write(_board[i][j].getValue() + "\t");
			}
			bw.newLine();
		}
		bw.newLine();
	}
    
    /**
     * Método que lee de un fichero un tablero.
     * 
     * @param br buffer para leer caracteres.
     * @throws IOException Excepción generada por un error en la lectura.
     */
	public void load(BufferedReader br) throws IOException {
		String[] st;
		String s;

		s = br.readLine();
		if (!s.isEmpty())
			throw new IOException();

		s = br.readLine();
		if (s.isEmpty())
			throw new IOException();

		st = s.split("\\s+");
		int oldSize = _boardSize;
		this._boardSize = st.length;
		
		_board = new Cell[_boardSize][_boardSize];

		for (int i = 0; i < this._boardSize; i++) {
			for (int j = 0; j < this._boardSize; j++) {
				_board[i][j] = new Cell(0);
			}
		}

		int i = 1;
		for (int a = 0; a < _boardSize; a++) {
			if (s.isEmpty()) {
				this._boardSize = oldSize;
				throw new IOException();
			}
			
			for (int b = 0; b < _boardSize; b++) {
				try {
					_board[a][b].setValue(Integer.parseInt(st[b]));
				} catch (NumberFormatException nfe) {
					this._boardSize = oldSize;
					throw new IOException();
				}
				catch(ArrayIndexOutOfBoundsException aiobe) {
					this._boardSize = oldSize;
					throw new IOException();
				}
			}
			
			if (i < _boardSize) {
				s = br.readLine();
				st = s.split("\\s+");
			}
			i++;
		}
	}

    /**
     * Imprime el estado del tablero _board.
     */
    @Override
	public String toString() {
		String celda;
		int cellSize = 7;
		String space = " ";
		String vDelimiter = "|";
		String hDelimiter = "-";

		String valor;
		int value;

		String separador = space + MyStringUtils.repeat(hDelimiter, ((cellSize + 1) * _boardSize) - 1) + " \n";

		celda = separador;
		for (int i = 0; i < _boardSize; i++) {
			for (int j = 0; j < _boardSize; j++) {
				valor = _board[i][j] + "";
				value = _board[i][j].getValue();
				if (value == 0)
					celda += vDelimiter + MyStringUtils.centre(space, cellSize);
				else
					celda += vDelimiter + MyStringUtils.centre(valor, cellSize);
			}
			celda += vDelimiter + " \n" + separador;
		}

		return celda;
	}

}