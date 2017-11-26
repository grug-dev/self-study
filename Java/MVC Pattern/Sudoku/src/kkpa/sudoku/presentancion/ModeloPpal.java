package kkpa.sudoku.presentancion;

import java.util.List;

import javax.swing.JButton;

import kkpa.sudoku.logica.LogicaSudoku;
import kkpa.sudoku.utilidades.SudokuConstantes;

public class ModeloPpal {

	/** Objectos */
	private VistaSudoku vistaPpal;
	private LogicaSudoku accionesSudoku;

	/**
	 * Método él cuál carga una ventana del juego sudoku, para su respectivo
	 * juego.
	 */
	public void iniciar() {
		definirDataPartida();
		getVistaPpal().inicializarJuego();
		getVistaPpal().setVisible(true);
	}

	private void definirDataPartida() {
		JButton[][] partidaGuardada = convertirA(getLogicaSudoku().leerPartidaActual());
		if (partidaGuardada != null) {
			getVistaPpal().setMtzCeldas(partidaGuardada);
			getVistaPpal().asignarPuntoPartida();
		} else {
			getVistaPpal().asignarPuntoPartida();
		}
	}

	public boolean comprobar(JButton[][] dataInfo) {
		boolean result = false;

		result = getLogicaSudoku().comprobarSudoku(convertirA(dataInfo));

		return result;
	}

	/**
	 * Método para convertir una matriz de JButton a una matriz de String,
	 * basados en el contenido de la propiedad getText() del JButton
	 * 
	 * @param dataInfo
	 *            JButton[][] Matriz que contiene la información a transferir
	 * @return String[][] Objecto Resultante de la transferencia del contenido.
	 */
	private String[][] convertirA(JButton[][] dataInfo) {

		if (dataInfo == null) {
			return null;
		}

		String[][] data = new String[SudokuConstantes.SIZE_SUDOKU][SudokuConstantes.SIZE_SUDOKU];
		for (int i = 0; i < SudokuConstantes.SIZE_SUDOKU; i++) {
			for (int j = 0; j < SudokuConstantes.SIZE_SUDOKU; j++) {
				data[i][j] = dataInfo[i][j].getText();
			}
		}

		return data;
	}

	/**
	 * Método para convertir una matriz de String a una matriz de objetos
	 * JButton, asignando el contenido en la propiedad text del objeto JButton.
	 * 
	 * @param dataInfo
	 *            String[][] Matriz que contiene la información a transferir
	 * @return JButton[][] Objecto Resultante de la transferencia del contenido.
	 */
	private JButton[][] convertirA(String[][] dataInfo) {

		if (dataInfo == null) {
			return null;
		}

		JButton[][] data = new JButton[SudokuConstantes.SIZE_SUDOKU][SudokuConstantes.SIZE_SUDOKU];
		for (int i = 0; i < SudokuConstantes.SIZE_SUDOKU; i++) {
			for (int j = 0; j < SudokuConstantes.SIZE_SUDOKU; j++) {
				JButton btn = new JButton();
				btn.setText(dataInfo[i][j]);
				data[i][j] = btn;
			}
		}

		return data;
	}

	/**
	 * Método para obtener la instancia del objeto LogicaSudoku
	 * 
	 * @return LogicaSudoku Objeto
	 */
	private LogicaSudoku getLogicaSudoku() {
		if (accionesSudoku == null) {
			accionesSudoku = new LogicaSudoku();
		}
		return accionesSudoku;
	}

	/**
	 * Metodo para obtener la instancia del objeto actual de vista del sudoku
	 * 
	 * @return VistaSudoku Objeto
	 */
	private VistaSudoku getVistaPpal() {
		if (vistaPpal == null) {
			vistaPpal = new VistaSudoku(this);
		}
		return vistaPpal;
	}

	/**
	 * Método para guardar la partida actual de sudoku
	 */
	public boolean guardar(JButton[][] mtzCeldas) {
		return getLogicaSudoku().guardarPartidaActual(convertirA(mtzCeldas));
	}

	/**
	 * Método para leer un archivo
	 * @param nombreArchivo
	 * @return  List<String> 
	 */
	public List<String> leerArchivo(String nombreArchivo) {
		return getLogicaSudoku().leerArchivo(nombreArchivo);
	}

}
