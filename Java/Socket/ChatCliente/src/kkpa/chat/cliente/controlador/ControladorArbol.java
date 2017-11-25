/**
 * 
 */
package kkpa.chat.cliente.controlador;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import kkpa.chat.cliente.presentacion.VistaUsuario;
import kkpa.chat.cliente.util.ChatConstantes;

/**
 * @author RaspuWIN7
 *
 */
public class ControladorArbol implements TreeSelectionListener {

	private final VistaUsuario vistaUsuario;
	
	public ControladorArbol (VistaUsuario pVista) {
		vistaUsuario = pVista;
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.event.TreeSelectionListener#valueChanged(javax.swing.event.TreeSelectionEvent)
	 */
	@Override
	public void valueChanged(TreeSelectionEvent e) {
		
		JTree tree = (JTree) e.getSource();
		DefaultMutableTreeNode nodo = (DefaultMutableTreeNode)
                tree.getLastSelectedPathComponent();
		if (nodo == null || nodo.isRoot()) {
			return;
		}
		String idDestinatario = nodo.getUserObject().toString();
		if (idDestinatario != null && !idDestinatario.equals(ChatConstantes.NOMBRE_GRUPO)) {
			vistaUsuario.setIdDestinatario(idDestinatario);
			vistaUsuario.getIdUsuarioDestino().setText("Usuario: " + idDestinatario);
			vistaUsuario.actualizarEstadoUsuarioDestino("En línea");
			vistaUsuario.getTxtChat().setEnabled(true);
		}
		else {
			vistaUsuario.setIdDestinatario(null);
			vistaUsuario.getIdUsuarioDestino().setText(" ");
			vistaUsuario.getTxtChat().setEnabled(false);
		}
	}

}
