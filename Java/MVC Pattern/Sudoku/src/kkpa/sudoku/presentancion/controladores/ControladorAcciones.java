package kkpa.sudoku.presentancion.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import kkpa.sudoku.presentancion.VistaSudoku;
import kkpa.sudoku.utilidades.SudokuConstantes;

public class ControladorAcciones implements ActionListener {

	private final VistaSudoku vistaPpal;

	public ControladorAcciones(VistaSudoku pVista) {
		vistaPpal = pVista;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		boolean resultado = false;
		String msg = null;
		JButton btnAccion = (JButton) e.getSource();

		// Accion Guardar
		if (btnAccion.getName().equalsIgnoreCase(SudokuConstantes.GUARDAR)) {
			resultado = getVistaPpal().guardarPartida();
			msg = resultado ? "Partida Guardada Exitosamente" : "Ha ocurrido un error al guardar la partida.";
			getVistaPpal().mostrarMensaje(msg);
			return;
		}

		// Accion Comprobar
		if (btnAccion.getName().equalsIgnoreCase(SudokuConstantes.COMPROBAR)) {
			msg = getVistaPpal().comprobarPartida();
			getVistaPpal().mostrarMensaje(msg);
			return;
		}

		// Accion Reiniciar
		if (btnAccion.getName().equalsIgnoreCase(SudokuConstantes.REINICIAR)) {
			getVistaPpal().reiniciarSudoku();
			return;
		}
	}

	private VistaSudoku getVistaPpal() {
		return vistaPpal;
	}

}
