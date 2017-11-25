/**
 * 
 */
package kkpa.chat.cliente.presentacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.BoxLayout;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import kkpa.chat.cliente.controlador.ControladorArbol;
import kkpa.chat.cliente.controlador.ControladorWindows;
import kkpa.chat.cliente.util.ChatConstantes;
import kkpa.protocolo.constantes.IComandos;
import kkpa.protocolo.constantes.IProtocolo;

/**
 * @author RaspuWIN7
 *
 */
public class VistaUsuario extends JFrame {

	private String idUsuario;

	private String idDestinatario;

	private Socket cliente;

	private JTree treeUsuarios;

	private JPanel panelChat;

	private JTextField txtChat;

	private JEditorPane areaTexto;

	private DefaultMutableTreeNode root;

	private JPanel infoUsuario;

	private JLabel idUsuarioDestino;

	private JLabel infoUsuarioDestino;

	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyHH:mm:ss");

	public VistaUsuario(String idCliente) {
		super("Bienvenido " + idCliente);
		this.idUsuario = idCliente;
		initComponents();
	}

	public void subscribir() {
		try {
			String mensaje = null;
			cliente = new Socket(InetAddress.getByName("127.0.0.1"), IProtocolo.SOCKET_PORT);
			ExecutorService executor = Executors.newCachedThreadPool();

			DataOutputStream out = new DataOutputStream(cliente.getOutputStream());

			mensaje = getProtocolo();
			mensaje += IComandos.SUBSCRIBIR;
			mensaje += IProtocolo.TOKEN_MSJ+"SERVER";
			mensaje += IProtocolo.TOKEN_MSJ+"Subscribiendo";
			out.writeUTF(mensaje);
			executor.execute(new RecibirMsjCliente(cliente, this));

			executor.shutdown();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void iniciar() {

	}

	public void initComponents() {

		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, getTreeUsuarios(), getPanelChat());
		splitPane.setOneTouchExpandable(true);
		splitPane.setDividerLocation(150);
		getContentPane().add(splitPane, BorderLayout.CENTER);
		pack();
		addWindowListener(new ControladorWindows(this));
	}

	public void agregarUsuario(String idUsuario) {
		DefaultMutableTreeNode user = null;
		user = new DefaultMutableTreeNode(idUsuario);
		user.setAllowsChildren(false);
		getRoot().add(user);
		SwingUtilities.updateComponentTreeUI(getTreeUsuarios());
	}

	/**
	 * Obtiene protocolo
	 * 
	 * @return the protocolo
	 */
	private String getProtocolo() {
	    StringBuffer protocolo = new StringBuffer(IProtocolo.NAME);
		Date fechaActual = new Date();
		String strFecha = dateFormat.format(fechaActual);
		protocolo.append(strFecha); 
		protocolo.append(IProtocolo.TOKEN_MSJ);
		protocolo.append(idUsuario);
		protocolo.append(IProtocolo.TOKEN_MSJ);
		return protocolo.toString();
	}

	/**
	 * Obtiene panelChat
	 * 
	 * @return the panelChat
	 */
	public JPanel getPanelChat() {
		if (panelChat == null) {
			panelChat = new JPanel();
			panelChat.setLayout(new BoxLayout(panelChat, BoxLayout.Y_AXIS));
			panelChat.add(getInfoUsuario());
			panelChat.add(getAreaTexto());
			panelChat.add(getTxtChat());
		}
		return panelChat;
	}

	/**
	 * Obtiene treeUsuarios
	 * 
	 * @return the treeUsuarios
	 */
	public JTree getTreeUsuarios() {
		if (treeUsuarios == null) {
			treeUsuarios = new JTree(getRoot());
			treeUsuarios.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
			treeUsuarios.addTreeSelectionListener(new ControladorArbol(this));
		}
		return treeUsuarios;
	}

	/**
	 * Obtiene root
	 * 
	 * @return the root
	 */
	public DefaultMutableTreeNode getRoot() {
		if (root == null) {
			root = new DefaultMutableTreeNode(ChatConstantes.NOMBRE_GRUPO);
		}
		return root;
	}

	/**
	 * Obtiene idCliente
	 * 
	 * @return the idCliente
	 */
	public String getIdUsuario() {
		return idUsuario;
	}

	/**
	 * Obtiene areaTexto
	 * 
	 * @return the areaTexto
	 */
	public JEditorPane getAreaTexto() {
		if (areaTexto == null) {
			areaTexto = new JEditorPane();
			areaTexto.setBackground(new Color(153, 204, 255));
			areaTexto.setForeground(Color.BLACK);
			areaTexto.setEditable(false);
			areaTexto.setContentType("text/html");
			areaTexto.setAutoscrolls(true);
			areaTexto.setPreferredSize(new Dimension(0, 480));
		}
		return areaTexto;
	}

	public void agregarTextoAlChat(String newMsj, boolean enviado) {
		String mensaje = "";

		mensaje += newMsj;
		// Define a keyword attribute

		SimpleAttributeSet keyWord = new SimpleAttributeSet();
		if (enviado) {
			StyleConstants.setForeground(keyWord, Color.BLACK);
			StyleConstants.setBackground(keyWord, new Color(255, 255, 255));
		} else {
			StyleConstants.setForeground(keyWord, Color.BLACK);
			StyleConstants.setBackground(keyWord, new Color(51, 255, 51));
		}

		StyleConstants.setBold(keyWord, true);

		getAreaTexto().setContentType("text/html");
		try {
			int l = getAreaTexto().getDocument().getLength();
			getAreaTexto().getDocument().insertString(l, mensaje + "\n", keyWord);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void actualizarEstadoUsuarioDestino(String mensaje) {
		getInfoUsuarioDestino().setText(mensaje);
	}
	
	public void cerrarConexion() {
		try {
			cliente.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void enviarMensaje(String comando, String textoMensaje) {
		try {
			if (comando == null) {
				return;
			}
			DataOutputStream out = new DataOutputStream(cliente.getOutputStream());
			String msj = getProtocolo();
			
			if (comando.equals(IComandos.USER_OFFLINE)) {
				msj += comando;
				msj += IProtocolo.TOKEN_MSJ;
				msj += "SERVER";
				msj += IProtocolo.TOKEN_MSJ;
				msj += textoMensaje;
				out.writeUTF(msj);
			}
			
			out.close();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	/**
	 * Obtiene txtChat
	 * 
	 * @return the txtChat
	 */
	public JTextField getTxtChat() {
		if (txtChat == null) {
			txtChat = new JTextField();
			txtChat.setForeground(Color.BLACK);
			txtChat.setEnabled(false);
			txtChat.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						DataOutputStream out = new DataOutputStream(cliente.getOutputStream());
						String msj = getProtocolo();
						msj += IComandos.ENVIAR_MENSAJE;
						msj += IProtocolo.TOKEN_MSJ;
						msj += idDestinatario;
						msj += IProtocolo.TOKEN_MSJ;
						msj += txtChat.getText();
						out.writeUTF(msj);
						agregarTextoAlChat(txtChat.getText(), true);
						txtChat.setText("");
					} catch (UnknownHostException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
		}
		return txtChat;
	}

	/**
	 * Obtiene idDestinatario
	 * 
	 * @return the idDestinatario
	 */
	public String getIdDestinatario() {
		return idDestinatario;
	}

	/**
	 * Asignar idDestinatario
	 * 
	 * @param idDestinatario
	 *            the idDestinatario to set
	 */
	public void setIdDestinatario(String idDestinatario) {
		this.idDestinatario = idDestinatario;
	}

	/**
	 * Obtiene infoUsuario
	 * 
	 * @return the infoUsuario
	 */
	public JPanel getInfoUsuario() {
		if (infoUsuario == null) {
			infoUsuario = new JPanel();
			infoUsuario.setLayout(new BoxLayout(infoUsuario, BoxLayout.Y_AXIS));
			infoUsuario.add(getIdUsuarioDestino());
			infoUsuario.add(getInfoUsuarioDestino());

		}
		return infoUsuario;
	}

	/**
	 * Obtiene idUsuarioDestino
	 * 
	 * @return the idUsuarioDestino
	 */
	public JLabel getIdUsuarioDestino() {
		if (idUsuarioDestino == null) {
			idUsuarioDestino = new JLabel();
			idUsuarioDestino.setText(" ");
		}
		return idUsuarioDestino;
	}

	/**
	 * Obtiene infoUsuarioDestino
	 * 
	 * @return the infoUsuarioDestino
	 */
	public JLabel getInfoUsuarioDestino() {
		if (infoUsuarioDestino == null) {
			infoUsuarioDestino = new JLabel();
			infoUsuarioDestino.setText(" ");
		}
		return infoUsuarioDestino;
	}

}
