package kkpa.sudoku.presentancion.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import kkpa.sudoku.presentancion.VistaSudoku;

public class ControladorTablero implements ActionListener {
	
	private final VistaSudoku vistaPpal;
	
	public ControladorTablero(VistaSudoku pVistaPpal) {
		this.vistaPpal = pVistaPpal;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btnClicked = (JButton) e.getSource();
		getVistaPpal().asignarCeldaSeleccionada(btnClicked);
	}

	private VistaSudoku getVistaPpal() {
		return vistaPpal;
	}
	
	
	
}
