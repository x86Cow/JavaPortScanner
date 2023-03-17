package cow.portscanner;

import java.net.InetSocketAddress;
import java.net.Socket;

public class ConnectionScanner implements Runnable {

   private String ip;
   private int startingPort;
   private int endingPort;
   private int timeout;
   private Thread thread;
   /**
    * 
    * @param ip
    * @param timeout
    */
   public ConnectionScanner(String ip, int startingPort, int endingPort, int timeout) {
      this.ip = ip;
      this.startingPort = startingPort;
      this.endingPort = endingPort;
      this.timeout = timeout;
   }

   /**
    * 
    * @param ip
    * @param port
    * @param timeout
    * @return
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

   public void start() {
      thread = new Thread(this);
      thread.start();
   }

   public void run() {
      for(int p = startingPort; p < endingPort; p++) {
         try {
            scanner(ip, p, timeout);
         } catch (Exception e) {
            continue;
         }
      }
   }
}
