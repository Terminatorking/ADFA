import automata.dfa.DFA;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        final Logger logger = Logger.getLogger(Main.class.getName());
        try {
            DFA dfa = DFA.createDFAFromFile(
                    System.getProperty("user.dir") + "\\src\\dfa.txt"
            );
            Scanner scanner = new Scanner(System.in);
            print("Enter input:");
            String input = scanner.nextLine();
            if (dfa.acceptInput(dfa.removeAllSpace(input))) {
                print("DFA accepts input: ✅");
            } else {
                print("DFA rejects input: ❌");
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    private static void print(Object o) {
        System.out.println(o.toString());
    }
}