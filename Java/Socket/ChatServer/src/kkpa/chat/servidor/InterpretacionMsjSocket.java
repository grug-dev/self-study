/**
 * 
 */
package kkpa.chat.servidor;

import java.util.StringTokenizer;

import kkpa.protocolo.constantes.IComandos;
import kkpa.protocolo.constantes.IProtocolo;
import kkpa.protocolo.constantes.IRespuestas;

/**
 * @author RaspuWIN7
 *
 */
public class InterpretacionMsjSocket {
    
    
    public static String getMensaje(String msgSocket) {
        String respuesta = null;
        
        if (msgSocket == null) {
            return respuesta;
        }
        
        String comando = null;
        
        System.out.println(msgSocket);
        
        StringTokenizer strToken = new StringTokenizer(msgSocket, IProtocolo.TOKEN_MSJ);
        String idOrigen = null;
        String idDestino = null;
        String msgToFriend = null;
        
        while (strToken.hasMoreTokens()) {
            switch (strToken.countTokens()) {
            case 1:
                msgToFriend = strToken.nextToken();
                break;
            case 2:
                idDestino = strToken.nextToken();
                break;
            case 3:
                comando = strToken.nextToken();    
                break;
            case 4:
                idOrigen = strToken.nextToken();
                break;
            default:
                strToken.nextToken();
                break;
            }
        }
        
        if (comando.equalsIgnoreCase(IComandos.SUBSCRIBIR)) {
            respuesta = IComandos.SUBSCRIBIR;
            respuesta += IProtocolo.TOKEN_MSJ;
            respuesta += idOrigen;
            
            return respuesta;
        }
        
        if (comando.equalsIgnoreCase(IComandos.ENVIAR_MENSAJE)) {
            respuesta = "" + IRespuestas.MSJ;
            respuesta += IProtocolo.TOKEN_MSJ;
            respuesta += idOrigen;
            respuesta += IProtocolo.TOKEN_MSJ;
            respuesta += idDestino;
            respuesta += IProtocolo.TOKEN_MSJ;
            respuesta += msgToFriend;
            return respuesta;
        }
        
        if (comando.equalsIgnoreCase(IComandos.USER_OFFLINE)) {
            respuesta = "" + IRespuestas.OFFLINE;
            respuesta += IProtocolo.TOKEN_MSJ; 
            respuesta += idOrigen;
            respuesta += IProtocolo.TOKEN_MSJ;
            
            String hora = msgToFriend;
            
            if (hora != null) {
                hora = hora.replace(IProtocolo.TOKEN_MSJ, "");
                respuesta += hora;
                return respuesta;
            }
        }
        
        return respuesta;
        
    }
    
}
