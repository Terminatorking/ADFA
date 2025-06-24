import automata.dfa.DFA;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        final Logger logger = Logger.getLogger(Main.class.getName());
        try {
            DFA dfa = new DFA().createFromFile(
                    System.getProperty("user.dir") + "\\src\\input.txt"
            );
            Scanner scanner = new Scanner(System.in);
            dfa.print("Enter input:");
            String input = scanner.nextLine();
            if (dfa.acceptInput(dfa.removeAllSpace(input))) {
                dfa.print("DFA accepts input: ✅");
            } else {
                dfa.print("DFA rejects input: ❌");
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "an error occurred :", e);
        }
    }
}