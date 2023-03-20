package cow.portscanner;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;

public class ConnectionScanner implements Runnable {

   private String ip;
   private int startingPort;
   private int endingPort;
   private int timeout;
   private Thread thread;
   private ArrayList<String> portData;
   private boolean done;
   /**
    * 
    * @param ip
    * @param timeout
    */
   public ConnectionScanner(String ip, int startingPort, int endingPort, int timeout) {
      portData = new ArrayList<String>();
      done = false;
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
            if(scanner(ip, p, timeout)){
               //port was found
               portData.add("[" + String.valueOf(p) + "]");
            }
         } catch (Exception e) {
            continue;
         }
      }
      done = true;
   }

   /**
    * Returns the port data as a string, using the {@code ArrayList.toString()} method
    * @return the data as a string
    */
   public String getPortData(){
      if(portData.isEmpty())
         return "";
      return portData.toString();
   }

   /**
    * returns a list of all the ports the were found
    * @return
    */
   public ArrayList<String> getPortDataList(){
      return portData;
   }

   public boolean isDoneScanning(){
      return done;
   }
}
