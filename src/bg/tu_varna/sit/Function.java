package bg.tu_varna.sit;

import java.util.ArrayList;
import java.util.List;

public class Function {
    private State firstState;
    private Symbol symbol;
    private List<State> secondStates;

    public Function() {
    }

    public Function(State firstState, Symbol symbol) {
        this.firstState = firstState;
        this.symbol = symbol;
        secondStates=new ArrayList<>();
    }

    public State getFirstState() {
        return firstState;
    }

    public void setFirstState(State firstState) {
        this.firstState = firstState;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public List<State> getSecondStates() {
        return secondStates;
    }

    public void setSecondStates(List<State> secondStates) {
        this.secondStates = secondStates;
    }
    public void addSecondState(State state){
        secondStates.add(state);
    }
}
