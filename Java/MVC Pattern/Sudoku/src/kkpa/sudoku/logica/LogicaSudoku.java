package kkpa.sudoku.logica;

import java.util.List;

import kkpa.sudoku.persistencia.OperacionesArchivoAPI;
import kkpa.sudoku.utilidades.SudokuConstantes;

public class LogicaSudoku {

	public boolean comprobarSudoku(String[][] mtzCeldas) {

		int factorialFilas = 1;
		int factorialColumnas = 1;
		int factorialSector1 = 1;
		int factorialSector2 = 1;
		int factorialSector3 = 1;
		int filaActual = 0;
		int factorialCorrecto = 1 * 2 * 3 * 4 * 5 * 6 * 7 * 8 * 9;
		boolean esValido = true;

		for (int i = 0; i <= 8; i++) {
			if (filaActual != i) {
				factorialColumnas = 1;
				factorialFilas = 1;
			}

			if (i % 3 == 0) {
				factorialSector1 = 1;
				factorialSector2 = 1;
				factorialSector3 = 1;
			}
			int valA = -1;
			for (int j = 0; j <= 8; j++) {
				valA = mtzCeldas[i][j].length() != 0 ? Integer.parseInt(mtzCeldas[i][j]) : 0;
				factorialColumnas *= valA;
				factorialFilas *= mtzCeldas[j][i].length() != 0 ? Integer.parseInt(mtzCeldas[j][i]) : 0;

				if (j < 3) {
					factorialSector1 *= valA;
				} else if (j < 6) {
					factorialSector2 *= valA;
				} else {
					factorialSector3 *= valA;
				}
			}
			if (factorialColumnas != factorialCorrecto || factorialFilas != factorialCorrecto) {
				esValido = false;
				break;
			}

			if (i % 3 == 2) {
				if (factorialSector1 != factorialCorrecto || factorialSector2 != factorialCorrecto
						|| factorialSector3 != factorialCorrecto) {
					esValido = false;
					break;
				}
			}

		}
		return esValido;
	}

	public boolean guardarPartidaActual(String[][] mtzCeldas) {
		boolean result = false;
		Object objData = mtzCeldas;

		result = OperacionesArchivoAPI.guardarObjeto(objData, SudokuConstantes.NOM_ARCHIVO_CONF);

		return result;
	}

	public String[][] leerPartidaActual() {
		String[][] objFile = (String[][]) OperacionesArchivoAPI
				.leerObjetoSerializado(SudokuConstantes.NOM_ARCHIVO_CONF);
		return objFile;
	}

	public List<String> leerArchivo(String nombreArchivo) {
		return OperacionesArchivoAPI.leerArchivo(nombreArchivo);
	}

}
