package automata;

import java.util.Set;

public interface AutomataValidator {
    void validateStates(int lineNumber, String line)
            throws InCorrectAutomataException;

    void validateAlphabet(int lineNumber, String line)
            throws InCorrectAutomataException;

    void validateStartState(int lineNumber, String line)
            throws InCorrectAutomataException;

    void validateAcceptStates(int lineNumber, String line)
            throws InCorrectAutomataException;

    void validateTransitionsTitle(int lineNumber, String line)
            throws InCorrectAutomataException;

    void validateTransitionsFormat(int lineNumber, String[] parts, String line)
            throws InCorrectAutomataException;

    void validateTransitionsStates(Set<String> states, String from, String to, int lineNumber)
            throws InCorrectAutomataException;

    void validateTransitionsAlphabet(Set<String> alphabet, int lineNumber, String symbol)
            throws InCorrectAutomataException;
}