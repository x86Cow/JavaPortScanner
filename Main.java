
/**
 * @author Alexander Lorei
 */
public class Main {
    public static void main(String[] args) throws Exception {
        for(int p = 0; p < 1000; p++) {
            ConnectionScanner.scanner("142.250.217.110", p, 20);
        }
    }
}