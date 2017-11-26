import kkpa.chat.servidor.Servidor;

/**
 * 
 */


/**
 * 
 * @author ccpena
 *
 */
public class LauncherServidor {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Servidor serverChat = Servidor.getInstance();
		
		serverChat.lanzar();
		

	}

}
