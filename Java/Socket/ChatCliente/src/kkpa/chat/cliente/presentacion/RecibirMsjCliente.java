/**
 * 
 */
package kkpa.chat.cliente.presentacion;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import kkpa.protocolo.constantes.IRespuestas;


/**
 * @author RaspuWIN7
 *
 */
public class RecibirMsjCliente implements Runnable {

	private Socket usuarioReceptor;

	private VistaUsuario vistaUsuario;

	private String mensaje;

	private static final String TOKEN_RTA = "###";

	public RecibirMsjCliente(Socket usuario, VistaUsuario vistaUsuario) {
		this.usuarioReceptor = usuario;
		this.vistaUsuario = vistaUsuario;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		DataInputStream in2 = null;
		String msg = null;
		try {

			do {
				if (usuarioReceptor.isClosed()) {
					cerrarConexion();
				} else {
					in2 = new DataInputStream(usuarioReceptor.getInputStream());

					mensaje = in2.readUTF();

					String idUsuario = vistaUsuario.getIdUsuario();

					if (mensaje.startsWith(IRespuestas.OK)) {
						String ans[] = mensaje.split(TOKEN_RTA);

						if (ans.length > 0) {
							int tipoRta = Integer.parseInt(ans[1]);

							switch (tipoRta) {
							case IRespuestas.USERS_ONLINE:
								if (ans.length > 2) {
									for (int i = 2; i < ans.length; i++) {
										String id = ans[i];
										if (!id.equalsIgnoreCase(idUsuario)) {
											vistaUsuario.agregarUsuario(id);
										}
									}
								}
								break;
							case IRespuestas.MSJ:
								String newMsj = ans[2];
								vistaUsuario.agregarTextoAlChat(newMsj, false);
								break;
							case IRespuestas.NUM_SUBSCRITO:
								break;
							case IRespuestas.OFFLINE:
								String msj = "Ultima conexión: " + ans[2];
								vistaUsuario.actualizarEstadoUsuarioDestino(msj);
								break;
							case IRespuestas.ONLINE:
								break;
							case IRespuestas.READ:
								break;
							case IRespuestas.WRITING:
								break;
							}
						}
					}
				}
			} while (usuarioReceptor != null && usuarioReceptor.isConnected());
		} catch (IOException e) {
			cerrarConexion();
		}
	}

	private void cerrarConexion() {
		try {
			usuarioReceptor.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		usuarioReceptor = null;
	}

}
