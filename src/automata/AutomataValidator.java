package automata;

import java.util.Set;

public interface AutomataValidator {
    void validateStates(int lineNumber, String line) throws Exception;

    void validateAlphabet(int lineNumber, String line) throws Exception;

    void validateStartState(int lineNumber, String line) throws Exception;

    void validateAcceptStates(int lineNumber, String line) throws Exception;

    void validateTransitions(int lineNumber, String line) throws Exception;

    void validateTransitions(int lineNumber, String[] parts, String line) throws Exception;

    void validateTransitions(Set<String> states, String from, String to, int lineNumber) throws Exception;

    void validateTransitions(Set<String> alphabet, int lineNumber, String symbol) throws Exception;
}
