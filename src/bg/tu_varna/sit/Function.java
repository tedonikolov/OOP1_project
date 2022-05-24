package bg.tu_varna.sit;

import java.util.ArrayList;
import java.util.List;

/**
 * The Function class represent a single function(rule) by which state passes from one to another.
 * @author Teodor
 * @version 1.0
 * @see State
 * @see Symbol
 */
public class Function {
    /**
     * Property "firstState" represent from which state the rule starts.
     */
    private State firstState;
    /**
     * Property "symbol" represent the symbol from the alphabet of the automation by which the rule is activated,
     */
    private Symbol symbol;
    /**
     * Property "secondStates" represent the list of next states which are reached by the rule.
     */
    private List<State> secondStates;

    /**
     * Empty constructor for the usage of JavaBean xml tools.
     */
    public Function() {
    }

    /**
     * Explicit value constructor for Function.
     * @param firstState stores the first state of the function.
     * @param symbol stores the symbol of the function.
     */
    public Function(State firstState, Symbol symbol) {
        this.firstState = firstState;
        this.symbol = symbol;
        secondStates=new ArrayList<>();
    }

    /**
     * Getter for property "firstState".
     *
     * @return the first state of the function.
     */
    public State getFirstState() {
        return firstState;
    }

    /**
     * Setter for property "firstState".
     *
     * @param firstState stores the first state of the function.
     */
    public void setFirstState(State firstState) {
        this.firstState = firstState;
    }

    /**
     * Getter for property "symbol".
     *
     * @return the symbol of the function.
     */
    public Symbol getSymbol() {
        return symbol;
    }

    /**
     * Setter for property "symbol".
     *
     * @param symbol stores the symbol of the function.
     */
    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    /**
     * Getter for property "secondStates".
     *
     * @return the second states of the function.
     */
    public List<State> getSecondStates() {
        return secondStates;
    }

    /**
     * Setter for property "secondStates".
     *
     * @param secondStates stores the list of second states.
     */
    public void setSecondStates(List<State> secondStates) {
        this.secondStates = secondStates;
    }

    /**
     * Adds state in the list of second states.
     *
     * @param state stores a state from the list of seconds states.
     */
    public void addSecondState(State state){
        secondStates.add(state);
    }
}
