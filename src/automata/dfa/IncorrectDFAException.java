package automata.dfa;

import automata.InCorrectAutomataException;

class IncorrectDFAException extends InCorrectAutomataException {
    public IncorrectDFAException(String message) {
        super(message);
    }
}