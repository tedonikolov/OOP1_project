package bg.tu_varna.sit;

import java.util.ArrayList;
import java.util.List;

public class Automation {
    private List<State> states;
    private List<Symbol> alphabet;
    private List<Function> functions;
    private State startState;
    private List<State> finaleStates;

    public Automation() {
    }

    public Automation(List<State> states, List<Symbol> alphabet, List<Function> functions, State startState, List<State> finaleStates) {
        this.states = states;
        this.alphabet = alphabet;
        this.functions = functions;
        this.startState = startState;
        this.finaleStates = finaleStates;
    }

    public Automation(State startState){
        states = new ArrayList<>();
        alphabet = new ArrayList<>();
        functions = new ArrayList<>();
        finaleStates = new ArrayList<>();
        this.startState = startState;
        addState(startState);
    }

    public void addState(State state){
        states.add(state);
    }

    public void addSymbol(Symbol symbol){
        alphabet.add(symbol);
    }

    public void addFunction(Function function){
        functions.add(function);
    }

    public void addFinaleState(State state){
        finaleStates.add(state);
    }

    public void setStartState(State startState) {
        this.startState = startState;
    }

    public List<State> getStates() {
        return states;
    }

    public List<Symbol> getAlphabet() {
        return alphabet;
    }

    public List<Function> getFunctions() {
        return functions;
    }

    public State getStartState() {
        return startState;
    }

    public List<State> getFinaleStates() {
        return finaleStates;
    }

    public void setStates(List<State> states) {
        this.states = states;
    }

    public void setAlphabet(List<Symbol> alphabet) {
        this.alphabet = alphabet;
    }

    public void setFunctions(List<Function> functions) {
        this.functions = functions;
    }

    public void setFinaleStates(List<State> finaleStates) {
        this.finaleStates = finaleStates;
    }

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

    public StringBuilder serialize() {
        StringBuilder string= new StringBuilder("\t\t\t");
        for(Symbol symbol:alphabet){
            string.append(symbol).append("\t");
        }
        string.append("\nSTART->\t{").append(print(startState)).append("\n");

        for(State state:states) {
            boolean flag=false;
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
