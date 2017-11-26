/**
 * 
 */
package kkpa.chat.servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

import kkpa.protocolo.constantes.IProtocolo;
import kkpa.protocolo.constantes.IRespuestas;

/**
 * @author ccpena
 *
 */
public class AdminPeticiones implements Runnable {

	private Socket cliente;
	private DataInputStream in2;

	public AdminPeticiones(Socket pClient) {
		this.cliente = pClient;
	}


	@Override
	public void run() {
		String mensaje = "";
		String idOrgine = null;
		String idDestino = null;
		String msg = null;
		
		do {
			try {
				in2 = new DataInputStream(cliente.getInputStream());
				mensaje = in2.readUTF();

				mensaje = InterpretacionMsjSocket.getMensaje(mensaje);

				String[] rta = mensaje.split(IProtocolo.TOKEN_MSJ);
				int comando = Integer.valueOf(rta[0]).intValue();

				switch (comando) {
				case IRespuestas.OFFLINE:
					idOrgine = rta[1];
					msg = rta[2];
					for (String idUser : Subscripcion.getInstance().getMapClientes().keySet()) {
						if (!idUser.equalsIgnoreCase(idOrgine)) {
							Socket user = Subscripcion.getInstance().getMapClientes().get(idUser);
							DataOutputStream out = new DataOutputStream(user.getOutputStream());
							mensaje = IRespuestas.OK + IProtocolo.TOKEN_MSJ + IRespuestas.OFFLINE + IProtocolo.TOKEN_MSJ +  msg;
							out.writeUTF(mensaje);
						}
					}
					break;
				case IRespuestas.MSJ:
					idOrgine = rta[1];
					idDestino = rta[2];
					msg = rta[3];

					Socket usuarioDestino = Subscripcion.getInstance().getMapClientes().get(idDestino);
					if (usuarioDestino != null) {
						DataOutputStream out = new DataOutputStream(usuarioDestino.getOutputStream());
						mensaje = IRespuestas.OK + IProtocolo.TOKEN_MSJ + IRespuestas.MSJ + IProtocolo.TOKEN_MSJ +  msg;
						out.writeUTF(mensaje);
					}

					break;
				}
			} catch (SocketException ex) {
				try {
					Subscripcion.getInstance().getMapClientes().remove(cliente);
					cliente.close();
					cliente = null;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}// fin catch
			catch (IOException ex) {
				try {
					Subscripcion.getInstance().getMapClientes().remove(cliente);
					cliente.close();
					cliente = null;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} while (cliente != null && cliente.isConnected());

	}
}
