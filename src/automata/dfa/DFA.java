package automata.dfa;

import automata.Automata;

import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.*;

public class DFA extends Automata {

    public static DFA createDFAFromFile(String filename) throws Exception {
        return new DFA().createFromFile(filename);
    }

    @Override
    public boolean acceptInput(String input) {
        String currentState = getStartState();
        for (char ch : input.toCharArray()) {
            String symbol = String.valueOf(ch);
            if (!getAlphabets().contains(symbol)) {
                System.out.println(symbol + " doesn't exist in alphabet of DFA ❌");
                return false;
            }
            Map<String, String> stateTransitions = getTransitions().get(currentState);
            if (stateTransitions == null || !stateTransitions.containsKey(symbol)) {
                System.out.println(
                        "transitions for ("
                                + currentState +
                                "," + symbol +
                                ") doesn't exist ❌"
                );
                return false;
            }
            currentState = stateTransitions.get(symbol);
        }
        return getAcceptsStates().contains(currentState);
    }

    @Override
    protected DFA createFromFile(String filename) throws Exception {
        LineNumberReader reader = new LineNumberReader(new FileReader(filename));
        setValidator(new DFAValidator());
        DFAValidator validator = (DFAValidator) getValidator();

        setStates(createStates(reader, validator));
        setAlphabets(createAlphabets(reader, validator));
        setStartState(createStartState(reader, validator));
        setAcceptsStates(createAcceptsStates(reader, validator));
        setTransitions(createTransitions(reader, validator));
        return this;
    }

    private Map<String, Map<String, String>> createTransitions(
            LineNumberReader reader,
            DFAValidator validator
    ) throws IOException, IncorrectDFAException {

        String line = skipSpaces(reader);
        validator.validateTransitionsTitle(reader.getLineNumber(), line);
        Map<String, Map<String, String>> transitions = new HashMap<>();

        while ((line = reader.readLine()) != null) {

            if (line.isEmpty())
                continue;

            line = removeAllSpace(line.toLowerCase());

            String[] parts = line.substring(1, line.length() - 1).split(",");
            validator.validateTransitionsFormat(reader.getLineNumber(), parts, line);

            String from = parts[0];
            String symbol = parts[1];
            String to = parts[2];
            validator.validateTransitionsStates(getStates(), from, to, reader.getLineNumber());
            validator.validateTransitionsAlphabet(getAlphabets(), reader.getLineNumber(), symbol);

            transitions.putIfAbsent(from, new HashMap<>());
            transitions.get(from).put(symbol, to);
        }
        reader.close();
        return transitions;
    }

    private Set<String> createAcceptsStates(LineNumberReader reader, DFAValidator validator)
            throws IOException, IncorrectDFAException {

        String line = skipSpaces(reader);
        validator.validateAcceptStates(reader.getLineNumber(), line);
        return new HashSet<>(Arrays.asList(line.substring(7).split(",")));
    }

    private String createStartState(LineNumberReader reader, DFAValidator validator)
            throws IOException, IncorrectDFAException {

        String line = skipSpaces(reader);
        validator.validateStartState(reader.getLineNumber(), line);
        return line.substring(6);
    }

    private Set<String> createAlphabets(LineNumberReader reader, DFAValidator validator)
            throws IOException, IncorrectDFAException {

        String line = skipSpaces(reader);
        validator.validateAlphabet(reader.getLineNumber(), line);
        return new HashSet<>(Arrays.asList(line.substring(9).split(",")));
    }

    private Set<String> createStates(LineNumberReader reader, DFAValidator validator)
            throws IOException, IncorrectDFAException {

        String line = skipSpaces(reader);
        validator.validateStates(reader.getLineNumber(), line);
        return new HashSet<>(Arrays.asList(line.substring(7).split(",")));
    }
}