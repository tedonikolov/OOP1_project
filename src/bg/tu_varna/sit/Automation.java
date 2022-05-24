package bg.tu_varna.sit;

import java.util.ArrayList;
import java.util.List;

/**
 *The Automation class represent finite state machine.
 */
public class Automation {
    /**
     * Property "states" represent a finite non-empty set of states.
     */
    private List<State> states;
    /**
     * Property "alphabet" represent a finite non-empty set of symbols.
     */
    private List<Symbol> alphabet;
    /**
     * Property "functions" represent the state-transition functions
     */
    private List<Function> functions;
    /**
     * Property "firstState" represent an initial state, an element of states.
     */
    private State startState;
    /**
     * Property "finaleStates" represent the set of final states, a (possibly empty) subset of states.
     */
    private List<State> finaleStates;

    /**
     * Empty constructor for the usage of JavaBean xml tools.
     */
    public Automation() {
    }

    /**
     * Explicit value constructor for Automation. Accepts only the mandatory first state of the automation.
     *
     * @param startState represent the start state of the automation.
     */
    public Automation(State startState){
        states = new ArrayList<>();
        alphabet = new ArrayList<>();
        functions = new ArrayList<>();
        finaleStates = new ArrayList<>();
        this.startState = startState;
        addState(startState);
    }

    /**
     * Adds state in the list of states.
     *
     * @param state stores a state from the list of states.
     */
    public void addState(State state){
        states.add(state);
    }

    /**
     * Adds symbol in the list of symbols.
     *
     * @param symbol stores a symbol from the list of symbols.
     */
    public void addSymbol(Symbol symbol){
        alphabet.add(symbol);
    }

    /**
     * Adds function in the list of functions.
     *
     * @param function stores a function from the list of functions.
     */
    public void addFunction(Function function){
        functions.add(function);
    }

    /**
     * Adds state in the list of finale states.
     *
     * @param state stores a state from the list of finale states.
     */
    public void addFinaleState(State state){
        finaleStates.add(state);
    }

    /**
     * Setter for property "startState".
     *
     * @param startState stores the first state in the automation.
     */
    public void setStartState(State startState) {
        this.startState = startState;
    }

    /**
     * Getter for property "states".
     *
     * @return the list of states of the automation.
     */
    public List<State> getStates() {
        return states;
    }

    /**
     * Getter for property "alphabet".
     *
     * @return the alphabet of the automation
     */
    public List<Symbol> getAlphabet() {
        return alphabet;
    }

    /**
     * Getter for property "functions".
     *
     * @return the functions of the automation.
     */
    public List<Function> getFunctions() {
        return functions;
    }

    /**
     * Getter for property "startState".
     *
     * @return the start state of the automation.
     */
    public State getStartState() {
        return startState;
    }

    /**
     * Getter for property "finaleStates".
     *
     * @return the finale states of the automation.
     */
    public List<State> getFinaleStates() {
        return finaleStates;
    }

    /**
     * Setter for property "states".
     *
     * @param states stores the list of states in the automation.
     */
    public void setStates(List<State> states) {
        this.states = states;
    }

    /**
     * Setter for property "alphabet".
     *
     * @param alphabet stores the list of symbols used in the automation.
     */
    public void setAlphabet(List<Symbol> alphabet) {
        this.alphabet = alphabet;
    }

    /**
     * Setter for property "functions".
     *
     * @param functions stores the list of functions in the automations.
     */
    public void setFunctions(List<Function> functions) {
        this.functions = functions;
    }

    /**
     * Setter for property "finaleStates".
     *
     * @param finaleStates stores the list of finale states of the automation.
     */
    public void setFinaleStates(List<State> finaleStates) {
        this.finaleStates = finaleStates;
    }

    /**
     * Prints the current state functions in a row.
     * @param state stores the current state of the automation.
     * @return StringBuilder of the state functions.
     */
    public StringBuilder print(State state) {
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(state).append("}\t");
        for (Symbol symbol : alphabet) {
            boolean flag = false;
            for (Function function : functions) {
                if (function.getFirstState() == state) {
                    if (symbol == function.getSymbol()) {
                        int i=1;
                        stringBuilder.append("{");
                        for (State state1:function.getSecondStates()) {
                            if(i>1)
                                stringBuilder.append(",");
                            stringBuilder.append(state1);
                            i++;
                        }
                        stringBuilder.append("}\t");
                        flag = true;
                    }
                }
            }
            if (!flag) {
                stringBuilder.append("-").append("\t");
            }
        }

        return stringBuilder;
    }

    /**
     * Serialize the automation in tabular view.
     * @return StringBuilder of the table.
     */
    public StringBuilder serialize() {
        StringBuilder string= new StringBuilder("\t\t\t");
        for(Symbol symbol:alphabet){
            string.append(symbol).append("\t");
        }
        string.append("\nSTART->\t{").append(print(startState)).append("\n");

        for(State state:states) {
            boolean flag=state != startState;
            for (State finaleState : finaleStates) {
                flag= state != startState && state != finaleState;
                if(!flag){
                    break;
                }
            }
            if(flag){
                string.append("\t\t{").append(print(state)).append("\n");
            }
        }

        for (State state:finaleStates){
            string.append("  END->\t{").append(print(state)).append("\n");
        }

        return string;
    }

    @Override
    public String toString() {
        return "Automation{" +
                "states=" + states +
                ", alphabet=" + alphabet +
                ", functions=" + functions +
                ", startState=" + startState +
                ", finaleStates=" + finaleStates +
                '}';
    }
}
