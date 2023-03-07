import java.net.InetSocketAddress;
import java.net.Socket;

public class ConnectionScanner {
   /**
    * Scans a given IP address and if specific port allows for connection, return true
    * @param ip
    * @param port
    * @param timeout
    * @return bool
    * @throws Exception
    */
   public static boolean scanner(String ip, int port, int timeout) throws Exception {
        try {
           Socket socket = new Socket();
           socket.connect(new InetSocketAddress(ip, port), timeout);
           socket.close();
           System.out.println("[" + String.valueOf(port) + "]");
           return true;
        } catch (Exception ex) {
            return false;
        }
   }
    
}
