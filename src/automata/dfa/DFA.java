package automata.dfa;

import automata.Automata;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.*;

public class DFA extends Automata {
    @Override
    public boolean acceptInput(String input) {
        String currentState = getStartState();
        for (char ch : input.toCharArray()) {
            String symbol = String.valueOf(ch);
            if (!getAlphabet().contains(symbol)) {
                print(symbol + " doesn't exist in alphabet of DFA ❌");
                return false;
            }
            Map<String, String> stateTransitions = getTransitions().get(currentState);
            if (stateTransitions == null || !stateTransitions.containsKey(symbol)) {
                print("transitions for (" + currentState + "," + symbol + ") doesn't exist ❌");
                return false;
            }
            currentState = stateTransitions.get(symbol);
        }
        return getAcceptsStates().contains(currentState);
    }
    @Override
    public DFA createFromFile(String filename) throws Exception {
        LineNumberReader reader = new LineNumberReader(new FileReader(filename));
        String line = skipSpace(reader);
        setValidator(new DFAValidator());
        getValidator().validateStates(reader.getLineNumber(), line);

        Set<String> states = new HashSet<>(Arrays.asList(line.substring(7).split(",")));

        line = skipSpace(reader);
        getValidator().validateAlphabet(reader.getLineNumber(), line);
        Set<String> alphabet = new HashSet<>(Arrays.asList(line.substring(9).split(",")));

        line = skipSpace(reader);
        getValidator().validateStartState(reader.getLineNumber(), line);
        String startState = line.substring(6);

        line = skipSpace(reader);
        getValidator().validateAcceptStates(reader.getLineNumber(), line);
        Set<String> acceptStates = new HashSet<>(Arrays.asList(line.substring(7).split(",")));

        line = skipSpace(reader);
        getValidator().validateTransitions(reader.getLineNumber(), line);
        Map<String, Map<String, String>> transitions = new HashMap<>();

        while ((line = reader.readLine()) != null) {

            if (line.isEmpty())
                continue;

            line = removeAllSpace(line.toLowerCase());

            String[] parts = line.substring(1, line.length() - 1).split(",");
            getValidator().validateTransitions(reader.getLineNumber(), parts, line);

            String from = parts[0];
            String symbol = parts[1];
            String to = parts[2];
            getValidator().validateTransitions(states, from, to, reader.getLineNumber());
            getValidator().validateTransitions(alphabet, reader.getLineNumber(), symbol);

            transitions.putIfAbsent(from, new HashMap<>());
            transitions.get(from).put(symbol, to);
        }
        reader.close();
        DFA dfa = new DFA();
        dfa.setStates(states);
        dfa.setAlphabet(alphabet);
        dfa.setTransitions(transitions);
        dfa.setStartState(startState);
        dfa.setAcceptsStates(acceptStates);
        return dfa;
    }
}