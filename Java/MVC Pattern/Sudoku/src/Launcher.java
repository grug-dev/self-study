import kkpa.sudoku.presentancion.ModeloPpal;


public class Launcher {
	
	private static ModeloPpal aplicacion;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		aplicacion = new ModeloPpal();
		aplicacion.iniciar();
	}

}
