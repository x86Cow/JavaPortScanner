
/**
 * @author Alexander Lorei
 */
public class Main {
    public static void main(String[] args) throws Exception {
        for(int p = 0; p < 1000; p++) {
            ConnectionScanner.scanner("162.159.135.42", p, 20);
        }
    }
}