/**
 * 
 */
package kkpa.chat.servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import kkpa.protocolo.constantes.IComandos;
import kkpa.protocolo.constantes.IProtocolo;
import kkpa.protocolo.constantes.IRespuestas;

/**
 * @author RaspuWIN7
 *
 */
public class Servidor {

	
	private static ServerSocket servidor;
	
	private boolean buscarClientes;
	
	private Map<String, Socket> mapClientes;
	
	private static Servidor servidorChat;
	
	
	private Servidor() {
		buscarClientes = true;
		mapClientes = new HashMap<String, Socket>();
	}
	
	
	public void lanzar() {
		 ExecutorService executor = Executors.newCachedThreadPool();
		 
		try {
			servidor = new ServerSocket(IProtocolo.SOCKET_PORT, IProtocolo.SOCKET_BACKLOG);
			 
			while (buscarClientes) {
				Socket usuario = servidor.accept();
				String mensaje = null;
				
				
				DataInputStream in2 = new DataInputStream(usuario.getInputStream());
				mensaje = in2.readUTF();
				
				mensaje = ProtocoloServer.getMensaje(mensaje);
				
				if (mensaje.startsWith(IComandos.SUBSCRIBIR)) {
					String rta[] = mensaje.split(IProtocolo.TOKEN_MSJ);
					String idUsuario = rta[1];
					mapClientes.put(idUsuario, usuario);
					executor.execute(new RecibirMsjServidor(usuario)); 	
					
					String msg = IRespuestas.OK + IProtocolo.TOKEN_MSJ + IRespuestas.USERS_ONLINE;
					for (String idUser : mapClientes.keySet()) {
						msg += IProtocolo.TOKEN_MSJ;
						msg += idUser;
					}
					for (String idUser : mapClientes.keySet()) {
						Socket user = mapClientes.get(idUser);
						if (!user.isClosed()) {
							DataOutputStream out = new DataOutputStream(user.getOutputStream());
							out.writeUTF(msg);
						}
					}
					
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		executor.shutdown();
	}
	
	
	public static Servidor getInstance() {
		if (servidorChat == null) {
			servidorChat = new Servidor();
		}
		return servidorChat;
	}


	/**
	 * Obtiene  mapClientes
	 * @return the mapClientes
	 */
	public Map<String, Socket> getMapClientes() {
		return mapClientes;
	}


	/**
	 * Asignar  mapClientes
	 * @param mapClientes the mapClientes to set
	 */
	public void setMapClientes(Map<String, Socket> mapClientes) {
		this.mapClientes = mapClientes;
	}
	
	
	
}
