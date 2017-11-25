/**
 * 
 */
package kkpa.chat.cliente.presentacion;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;

/**
 * @author ccpena
 *
 */
public class VistaInscripcion extends JDialog {

	private JButton btnAcceder;
	
	
	private JTextField txtID;
	
	
	private JDialog vistaInscripcion;
	
	
	
	public VistaInscripcion() {
		super();
		initComponents();
	}
	
	private void initComponents() {
		setTitle("Ingrese su ID");
		add(getTxtID(),BorderLayout.CENTER);
		add(getBtnAcceder(),BorderLayout.SOUTH);
		vistaInscripcion = this;
	}

	/**
	 * Obtiene  btnAcceder
	 * @return the btnAcceder
	 */
	public JButton getBtnAcceder() {
		if (btnAcceder == null) {
			btnAcceder = new JButton();
			btnAcceder.setText("Ingresar");
			btnAcceder.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					vistaInscripcion.dispose();
					VistaUsuario p = new VistaUsuario(getTxtID().getText());
					p.subscribir();
					p.setSize(500, 500);
					p.setResizable(false);
					p.addWindowListener(new WindowAdapter() {
						public void windowClosing(WindowEvent evt) {
							System.exit(0);
						}
					});
					p.setVisible(true);
					
				}
			});
		}
		return btnAcceder;
	}

	

	/**
	 * Obtiene  txtID
	 * @return the txtID
	 */
	public JTextField getTxtID() {
		if (txtID == null) {
			txtID = new JTextField();
		}
		return txtID;
	}

	
	
	
	
	
}
