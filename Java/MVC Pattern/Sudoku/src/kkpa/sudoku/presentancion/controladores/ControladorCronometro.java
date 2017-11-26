package kkpa.sudoku.presentancion.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import kkpa.sudoku.presentancion.VistaSudoku;
import kkpa.sudoku.utilidades.SudokuConstantes;

public class ControladorCronometro implements ActionListener{
	
	private final VistaSudoku vistaPpal;
	
	public ControladorCronometro(VistaSudoku pVistaPpal) {
		vistaPpal = pVistaPpal;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btnAccion = (JButton) e.getSource();

		// Accion Guardar
		if (btnAccion.getName().equalsIgnoreCase(SudokuConstantes.INICIAR_TIEMPO)) {
			getVistaPpal().iniciarCronometro();
			return;
		}

		// Accion Comprobar
		if (btnAccion.getName().equalsIgnoreCase(SudokuConstantes.REINICIAR_TIEMPO)) {
			getVistaPpal().reiniciarCronometro();
			return;
		}

		// Accion Reiniciar
		if (btnAccion.getName().equalsIgnoreCase(SudokuConstantes.PAUSAR_TIEMPO)) {
			getVistaPpal().pausarCronometro();
			return;
		}
		
	}

	private VistaSudoku getVistaPpal() {
		return vistaPpal;
	}
	

}
