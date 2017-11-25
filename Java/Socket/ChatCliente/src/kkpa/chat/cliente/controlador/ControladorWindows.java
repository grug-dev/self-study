/**
 * 
 */
package kkpa.chat.cliente.controlador;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import kkpa.chat.cliente.presentacion.VistaUsuario;
import kkpa.protocolo.constantes.IComandos;

/**
 * @author RaspuWIN7
 *
 */
public class ControladorWindows implements WindowListener {
	
private final VistaUsuario vistaUsuario;
	
	public ControladorWindows (VistaUsuario pVista) {
		vistaUsuario = pVista;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.WindowListener#windowOpened(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.WindowListener#windowClosing(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowClosing(WindowEvent e) {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yy hh:mm:ss");
		String msj = df.format(new Date());
		vistaUsuario.enviarMensaje(IComandos.USER_OFFLINE, msj);
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.WindowListener#windowClosed(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.WindowListener#windowIconified(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.WindowListener#windowDeiconified(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.WindowListener#windowActivated(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.WindowListener#windowDeactivated(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
