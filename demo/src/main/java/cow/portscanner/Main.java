package cow.portscanner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexander Lorei
 */
public class Main {
    static ArrayList<ConnectionScanner> scanners;
    static int portsPerThread = 1;
    static int portsTotal = 1000;
    static int threadsTotal = portsTotal/portsPerThread;
    public static void main(String[] args) throws Exception {
        scanners = new ArrayList<ConnectionScanner>();

        System.out.println(threadsTotal);
        for(int i = 0; i < threadsTotal; i++) {
            int startingPort = i * portsPerThread;
            int endingPort = portsPerThread + i * portsPerThread;
            ConnectionScanner ps = new ConnectionScanner("scanme.nmap.org", startingPort, endingPort, 200); 
            scanners.add(ps);
           ps.start();
        }

        //wait for all scanners to finish
        while(!portsAreAllDone(scanners));
        
        //past here the scanners are all done
        String data = "";
        
        //something can be done to make it format better, but that doesn't matter too much
        for(ConnectionScanner s : scanners)
            data += s.getPortData();

        System.out.println(data);
    }

    /**
     * checks if all the scanners in a given list
     * @param sc - the list of scanners to check
     * @return {@code true} if the ports are done scanning, {@code false} otherwise
     */
    private static boolean portsAreAllDone(List<ConnectionScanner> sc){
        for(ConnectionScanner s : sc)
            if(!s.isDoneScanning())
                return false;
        return true;
    }
}