import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import kkpa.chat.cliente.presentacion.VistaInscripcion;


/**
 * 
 */


/**
 * @author ccpena
 *
 */
public class LauncherClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		VistaInscripcion p = new VistaInscripcion();
		p.setSize(100, 80);
		p.setResizable(false);
		p.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				System.exit(0);
			}
		});
		p.setVisible(true);
		
		
		

	}

}
