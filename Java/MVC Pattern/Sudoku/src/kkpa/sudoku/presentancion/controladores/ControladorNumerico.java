package kkpa.sudoku.presentancion.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import kkpa.sudoku.presentancion.VistaSudoku;
import kkpa.sudoku.utilidades.SudokuConstantes;

public class ControladorNumerico implements ActionListener {

	private final VistaSudoku vistaPpal;
	
	
	
	public ControladorNumerico(VistaSudoku pVistaPpal) {
		vistaPpal = pVistaPpal;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btnNum = (JButton) e.getSource();

		if (btnNum.getName().equalsIgnoreCase(SudokuConstantes.RESET)) {
			getVistaPpal().resetarCelda();
			return;
		}

		getVistaPpal().asignarNumeroACeldaSeleccionada(btnNum.getText());

	}

	private VistaSudoku getVistaPpal() {
		return vistaPpal;
	}

}
