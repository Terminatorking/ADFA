package automata.dfa;

import automata.AutomataValidator;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class DFAValidator implements AutomataValidator {
    @Override
    public void validateStates(int lineNumber, String line) throws Exception {
        if (!line.startsWith("states:"))
            throw new IncorrectDFAException("line " + lineNumber + " must start with states: ❌");

        if (isAutomataPatternNotCorrect("^q\\d+(,q\\d+)*$", line.substring(7)))
            throw new IncorrectDFAException("states of DFA  must be this form: q1,q2,q3,... in line : " + lineNumber + " ❌");

    }

    @Override
    public void validateAlphabet(int lineNumber, String line) throws Exception {
        if (!line.startsWith("alphabet:"))
            throw new IncorrectDFAException("line " + lineNumber + " must start with alphabet: ❌");

        if (isAutomataPatternNotCorrect("^[a-z0-9](,[a-z0-9])*$", line.substring(9)))
            throw new IncorrectDFAException("alphabet of DFA must have only one character and must exist , between them in line : " + lineNumber + " ❌");

    }

    @Override
    public void validateStartState(int lineNumber, String line) throws Exception {
        if (!line.startsWith("start:"))
            throw new IncorrectDFAException("line " + lineNumber + " must start with start: ❌");

        if (isAutomataPatternNotCorrect("q[0-9]+", line.substring(6)))
            throw new IncorrectDFAException("start state of DFA must be this form: qi (Example:q0,q1,...) in line :" + lineNumber + " ❌");

    }

    @Override
    public void validateAcceptStates(int lineNumber, String line) throws Exception {
        if (!line.startsWith("accept:"))
            throw new IncorrectDFAException("line " + lineNumber + " must start with accept: ❌");

        if (isAutomataPatternNotCorrect("^q\\d+(,q\\d+)*$", line.substring(7)))
            throw new IncorrectDFAException("accept states of DFA accept states must be this form: q1,q2,q3,... in line:" + lineNumber + " ❌");

    }

    @Override
    public void validateTransitions(int lineNumber, String line) throws Exception {
        if (!line.startsWith("transitions:"))
            throw new IncorrectDFAException("line " + lineNumber + " must start with transitions: ❌");

        if (!line.substring(12).isEmpty())
            throw new IncorrectDFAException("Nothing comes after transitions: in line : " + lineNumber + " ❌");

    }

    @Override
    public void validateTransitions(int lineNumber, String[] parts, String line) throws Exception {
        if ((!line.startsWith("(") || !line.endsWith(")")) || parts.length != 3)
            throw new IncorrectDFAException("incorrect transition format in line: " + lineNumber + " transition must be this format : (qi,string,qj) ❌");

    }

    @Override
    public void validateTransitions(Set<String> states, String from, String to, int lineNumber) throws Exception {
        if (!states.contains(from) || !states.contains(to))
            throw new IncorrectDFAException("state " + from + " or state " + to + " doesn't exist in transition function in line : " + lineNumber + " ❌");

    }

    @Override
    public void validateTransitions(Set<String> alphabet, int lineNumber,String symbol) throws Exception {
        if (!alphabet.contains(symbol))
            throw new IncorrectDFAException("symbol '" + symbol + "' in line " + lineNumber + " doesn't exist in alphabet of DFA ❌");

    }

    private boolean isAutomataPatternNotCorrect(String regex, String line) {
        final Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        final Matcher matcher = pattern.matcher(line);
        return !matcher.matches();
    }
}
