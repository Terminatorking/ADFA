package automata;

import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Map;
import java.util.Set;

public abstract class Automata {

    private AutomataValidator validator;
    private Set<String> states;
    private Set<String> alphabets;
    private Map<String, Map<String, String>> transitions;
    private String startState;
    private Set<String> acceptsStates;

    public abstract boolean acceptInput(String input) ;
    protected abstract Automata createFromFile(String filename) throws Exception ;

    public void setStates(Set<String> states) {
        this.states = states;
    }

    public Set<String> getStates() {
        return this.states;
    }

    public void setAlphabets(Set<String> alphabets) {
        this.alphabets = alphabets;
    }

    public Set<String> getAlphabets() {
        return this.alphabets;
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

    public String removeAllSpace(String input) {
        return input.replaceAll("\\s", "");
    }

    public String skipSpaces(LineNumberReader reader) throws IOException {
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