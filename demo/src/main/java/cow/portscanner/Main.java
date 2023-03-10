package cow.portscanner;

/**
 * @author Alexander Lorei
 */
public class Main {
    static int portsPerThread = 100;
    static int portsTotal = 1000;
    static int threadsTotal = portsTotal/portsPerThread;
    public static void main(String[] args) throws Exception {
        System.out.println(threadsTotal);
        for(int i = 0; i < threadsTotal; i++) {
            int startingPort = i * portsPerThread;
            int endingPort = portsPerThread + i * portsPerThread;
            ConnectionScanner ps = new ConnectionScanner("142.250.217.110", startingPort, endingPort, endingPort); 

           ps.start();
        }
    }
}