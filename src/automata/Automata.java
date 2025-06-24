package automata;

import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Map;
import java.util.Set;

public abstract class Automata {
    private AutomataValidator validator;
    private Set<String> states;
    private Set<String> alphabet;
    private Map<String, Map<String, String>> transitions;
    private String startState;
    private Set<String> acceptsStates;

    public void setStates(Set<String> states) {
        this.states = states;
    }

    @SuppressWarnings("All")
    public Set<String> getStates() {
        return this.states;
    }

    public void setAlphabet(Set<String> alphabet) {
        this.alphabet = alphabet;
    }

    public Set<String> getAlphabet() {
        return this.alphabet;
    }

    public Map<String, Map<String, String>> getTransitions() {
        return transitions;
    }

    public void setTransitions(Map<String, Map<String, String>> transitions) {
        this.transitions = transitions;
    }

    public String getStartState() {
        return startState;
    }

    public void setStartState(String startState) {
        this.startState = startState;
    }

    public Set<String> getAcceptsStates() {
        return acceptsStates;
    }

    public void setAcceptsStates(Set<String> acceptsStates) {
        this.acceptsStates = acceptsStates;
    }

    public abstract boolean acceptInput(String input);

    public abstract Automata createFromFile(String filename) throws Exception;


    public String removeAllSpace(String input) {
        return input.replaceAll("\\s", "");
    }

    public void print(Object o) {
        System.out.println(o.toString());
    }

    public String skipSpace(LineNumberReader reader) throws IOException {
        String line;
        do {
            line = reader.readLine();
        } while (line.isEmpty());
        return removeAllSpace(line);
    }

    public AutomataValidator getValidator() {
        return validator;
    }

    public void setValidator(AutomataValidator validator) {
        this.validator = validator;
    }
}
