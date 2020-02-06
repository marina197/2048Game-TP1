package tp.pr3.logic.multigames;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Random;
import tp.pr3.*;
import tp.pr3.exceptions.GameOverException;
import tp.pr3.logic.GameStateStack;

/**
 * <h1>Clase Game</h1>
 * 
 * <p>Representa el estado de una partida</p>
 *
 * @author Marina Lozano Lahuerta
 * @author Pablo López Veleiro
 */
public class Game {
    /**
     * Tablero
     */
    private Board _board;
    /**
     * Dimensión del tablero
     */
    private int _size;
    /**
     * Número de baldosas no nulas iniciales
     */
    private int _initCells;
    /**
     * Comportamiento aleatorio del juego
     */
    private Random _myRandom;
    /**
     * Semilla para el comportamiento aleatorio del juego
     */
    private long _seed;
    /**
     * Pila de estados del juego para deshacer un movimiento
     */
    private GameStateStack undoStack = new GameStateStack();
    /**
     * Pila de estados del juego para rehacer un movimiento
     */
    private GameStateStack redoStack = new GameStateStack();
    /**
     * Puntuación
     */
    private int points;
    /**
     * Reglas del juego actual
     */
    private GameRules currentRules;
    /**
     * Tipo de juego actual.
     */
    private GameType tipo;

    /**
     * Constructor de la clase Game. inicializa los atributos de la clase e
     * instancia el tablero.
     * Inicializa el tablero con las baldosas no nulas iniciales.
     *
     * @param type     	tipo de juego actual
     * @param seed      semilla para el comportamiento aleatorio del juego
     * @param dim       dimensión del tablero
     * @param initCells número de baldosas iniciales no nulas
     */
	public Game(GameType type, long seed, int dim, int initCells) {
		this._size = dim;
		this._initCells = initCells;
		this._seed = seed;
		this.points = 0;
		this.tipo = type;
		this.currentRules = tipo.getRules();
		this._board = currentRules.createBoard(_size);
		_myRandom = new Random(seed);
		initBoard();
	}

    /**
     * Método responsable de inicializar una partida
     */
    public final void reset() {
        _seed = new Random().nextInt(1000);
        _myRandom = new Random(_seed);
        Position pos;

        for (int i = 0; i < _size; i++) {
            for (int j = 0; j < _size; j++) {
                pos = new Position(i, j);
                _board.setCell(pos, 0);
            }
        }
        initBoard();
    }

    /**
     * Ejecuta un movimiento en la dirección indicada sobre el tablero, actualiza
     * los resultados del juego.
     *
     * @param dir dirección indicada por el usuario en la que realizar el
     *            movimiento.
     * @throws GameOverException Excepción lanzada para finalizar el juego.
     */
    public void move(Direction dir) throws GameOverException {
    	saveMove();
        MoveResults r;
        r = _board.executeMove(dir, currentRules);

        if (r.isMoved()) {
            points = r.getPoints() + points;
            new MoveResults(true, points);
            randomCell();
            lose();
            win();
        } else {
            undoStack.pop();
        }
    }

    /**
     * Almacena un movimiento en la pila de undo y se instancia una nueva pila para
     * redo.
     */
    public void saveMove() {
        undoStack.push(new GameState(points, _board.getState()));
        redoStack.clear();
    }

    /**
     * Comprueba si se ha perdido la partida
     *
     * @throws GameOverException Excepción lanzada para finalizar el juego.
     */
    public void lose() throws GameOverException {
    	if(currentRules.lose(_board))
    		throw new GameOverException(" \n" + " \n" + "No hay movimientos posibles." + " \n" + "GAME OVER.");
    }

    /**
     * Método que genera los valores aleatorios de las baldosa no nulas
     * iniciales.
     */
    public void initBoard() {
        currentRules.initBoard(_board, _initCells, _myRandom);
    }

    /**
     * Genera aleatoriamente el valor de una baldosa
     */
    private void randomCell() {
        currentRules.addNewCell(_board, _myRandom);
    }

    /**
     * Comprueba si la partida se ha ganado
     *
     * @throws GameOverException Excepción lanzada para finalizar el juego.
     */
	public void win() throws GameOverException {
		if (currentRules.win(_board))
			throw new GameOverException(" \n" + "Has GANADO :)");
	}

    /**
     * El estado actual del juego toma el valor del estado previo almacenado.
     */
	public void undo() {
		GameState aState = getState();
		GameState state;

		state = undoStack.pop();

		redoStack.push(aState);
		setState(state);

	}

    /**
     * El estado actual del juego toma el valor del estado siguiente almacenado.
     */
	public void redo() {
		GameState aState;
		aState = redoStack.pop();

		undoStack.push(getState());
		setState(aState);

	}

    /**
     * Devuelve el estado actual del juego invocando el método getState de Board
     *
     * @return estado actual del juego
     */
    private GameState getState() {
        return new GameState(points, _board.getState());
    }

    /**
     * Reestablece el juego al estado aState e invoca el método setState
     * de Board.
     *
     * @param aState estado del juego
     */
    private void setState(GameState aState) {
        points = aState.getScore();
        _board.setState(aState.getBoardState());
    }

    /**
	 * Método que escribe en un fichero el tablero, las celdas iniciales, la
	 * puntuación y el tipo de juego.
	 * 
	 * @param bw buffer para escribir caracteres
     * @throws IOException Excepción generada por un error en la escritura.
	 */
	public void store(BufferedWriter bw) throws IOException {
		_board.store(bw);
		bw.write(_initCells + "\t" + points + "\t" + tipo.externalise());
	}
	
	/**
	 * Método que carga de un fichero el tablero, las celdas iniciales, la
	 * puntuación y el tipo de juego.
	 * 
	 * @param br buffer para leer caracteres.
	 * @return tipo de juego leido en el archivo.
	 * @throws IOException Excepción generada por un error en la lectura.
	 */
	public GameType load(BufferedReader br) throws IOException {
		String s;
		String[] st;

		GameState state = getState();
		int oldInitCells = this._initCells;
		GameType oldType = this.tipo;
		int oldSize = _size;
		
		try {
			_board.load(br);
			_size = _board.get_boardSize();

			s = br.readLine();
			if (!s.isEmpty()) 
				throw new IOException();

			s = br.readLine();
			st = s.split("\\s+");
			if (st.length != 3) 
				throw new IOException();
			
			try {
				this._initCells = Integer.parseInt(st[0]);
				if ((_initCells > _size * _size) || _initCells < 1) 
					throw new IOException();
				
				this.points = Integer.parseInt(st[1]);
				if (points < 0) 
					throw new IOException();
				
			} catch (NumberFormatException nfe) {
				throw new IOException();
			}

			this.tipo = GameType.parse(st[2]);
			if (tipo == null) 
				throw new IOException();
			
			currentRules = tipo.getRules();
			undoStack.clear();
			redoStack.clear();
			return tipo;
			
		} catch (IOException ioe) {
			this.points = state.getScore();
			this._initCells = oldInitCells;
			this.tipo = oldType;	
			currentRules = tipo.getRules();
			_size = oldSize;
			
			_board = currentRules.createBoard(_size);
			setState(state);
			throw new IOException();
		}	
	}

	/**
	 * Método que cambia la partida con los datos proporcionados.
	 * 
	 * @param gtype tipo de juego
	 * @param initCells celdas iniciales
	 * @param size tamaño del tablero
	 * @param seed semilla para la generación aletatoria
	 */
	public void changeGame(GameType gtype, int initCells, int size, long seed){
		this._size = size;
		this._initCells = initCells;
		this._seed = seed;
		_myRandom = new Random(seed);
		this.tipo = gtype;
		this.currentRules = tipo.getRules();
		this._board = currentRules.createBoard(_size);
		
		initBoard();
		this.points = 0;
	}
	
	
    @Override
    /**
     * Imprime el tablero, los puntos y el valor de la baldosa de mayor valor
     */
	public String toString() {
		String string;
		string = _board + "" + "Best value: "
				+ currentRules.getWinValue(_board) + " Points: " + points;

		return string;
	}

}