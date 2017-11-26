import kkpa.chat.servidor.Subscripcion;

/**
 * 
 */


/**
 * 
 * @author ccpena
 *
 */
public class Launcher {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Subscripcion serverChat = Subscripcion.getInstance();
		
		serverChat.lanzar();
		

	}

}
