import java.util.StringTokenizer;

/**
 * 
 */

/**
 * @author ccpena
 *
 */
public class TEST {
    
    /**
     * @param args
     */
    public static void main(String[] args) {
       StringTokenizer strToken = new StringTokenizer("PCWA25/11/1717:47:16###3014844558###ENM###3008888805###Hola Amigo!","###");
       while (strToken.hasMoreElements()) {
           System.out.printf("%d - %s \n", strToken.countTokens() , strToken.nextToken());
       }
        
    }
    
}
