package bg.tu_varna.sit;

import javax.swing.text.BadLocationException;
import java.util.*;

/**
 * The Mutator class represent the tool for making automation deterministic.
 * @author Teodor
 * @version 1.0
 * @see Automation
 * @see Menu
 */
public class Mutator {

    /**
     * Makes nondeterministic automation deterministic.
     * @param id stores the id of the current automation.
     * @param machines stores the current machines.
     * @param console stores the console of the program.
     */
    public void mutator(int id, Machines machines, Console console) throws BadLocationException {
        Automation automation1 = machines.getAutomation(id);
        Automation automation=new Automation(automation1.getStartState());
        automation.setAlphabet(automation1.getAlphabet());
        automation.setStartState(automation1.getStartState());

        automation.setStates(automation1.getStates());
        Set<State> states=new HashSet<>();

        for (State state : automation1.getStates()) {
            for (Symbol symbol : automation1.getAlphabet()) {
                for (Function function : automation1.getFunctions()) {
                    if (function.getFirstState() == state) {
                        if (symbol == function.getSymbol()) {
                            String string = "";
                            boolean flag=false;
                            int i=1;
                            for (State state1:function.getSecondStates()) {
                                if(i>1)
                                    string+=",";
                                string+=state1.getName();
                                i++;
                                for(State state2:automation1.getFinaleStates()) {
                                    if (state1==state2){
                                        flag=true;
                                        break;
                                    }
                                }
                            }

                            boolean flag1=true;
                            for(State state1:automation.getStates()) {
                                if(Objects.equals(state1.getName(), string)){
                                    flag1=false;
                                    break;
                                }
                            }

                            State state1=new State(string);

                            if(flag){
                                if(flag1){
                                    states.add(state1);
                                    automation.addFinaleState(state1);
                                }
                            }
                            else{
                                if(flag1){
                                    states.add(state1);
                                }
                            }

                            automation.addFunction(function);
                        }
                    }
                }
            }
        }
        for(State state:states){
            automation.addState(state);
        }
        for(Function function:automation.getFunctions()) {
            for (State state : automation.getStates()) {
                if(Objects.equals(state.getName(), function.getFirstState().getName())){
                    function.setFirstState(state);
                }
                for(State state1:function.getSecondStates()){
                    if (Objects.equals(state.getName(), state1.getName())){
                        state1=state;
                    }
                }
            }
        }
        boolean flags=true;
        while (flags) {
            List<State> states1=new ArrayList<>();
            for (State state : automation.getStates()) {
                String[] string = state.getName().split(",");
                if (string.length > 1) {
                    for (Symbol symbol : automation.getAlphabet()) {
                        String string2 = "";
                        int i = 1;
                        for (String str : string) {
                            for (Function function1 : automation1.getFunctions()) {
                                if (Objects.equals(str, function1.getFirstState().getName()) && symbol == function1.getSymbol()) {
                                    for (State state1 : function1.getSecondStates()) {
                                        if (i > 1)
                                            string2 += ",";
                                        string2 += state1.getName();
                                        i++;
                                    }
                                }
                            }
                        }
                        State state2 = new State(string2);
                        Function function = new Function(state, symbol);
                        boolean flag = true;
                        for (State state1 : automation.getStates()) {
                            if (Objects.equals(state1.getName(), string2)) {
                                function.addSecondState(state1);
                                flag = false;
                                break;
                            }
                        }
                        if (flag) {
                            states1.add(state2);
                            function.addSecondState(state2);
                        }
                        boolean flag3=false;
                        for(Function function1:automation.getFunctions()){
                            for(State state1:function1.getSecondStates()){
                                for(State state3:function.getSecondStates()){
                                    if(Objects.equals(state1.getName(), state3.getName()) &&function.getFirstState()==function1.getFirstState()&&function.getSymbol()==function1.getSymbol()){
                                        flag3=true;
                                        break;
                                    }
                                }
                                if(flag3){
                                    break;
                                }
                            }
                            if(flag3){
                                break;
                            }
                        }
                        if(!flag3) {
                            automation.addFunction(function);
                        }
                    }
                }
            }
            if(states1.size()!=0) {
                for (State state : states1) {
                    automation.addState(state);
                }
            }
            else{
                flags=false;
                for(Function function2: automation.getFunctions()){
                    if(function2.getSecondStates().size()>1) {
                        String string3="";
                        int i=1;
                        for (State state3 : function2.getSecondStates()) {
                            if (i > 1)
                                string3+= ",";
                            string3+= state3.getName();
                            i++;
                        }
                        List<State> states2=new ArrayList<>();
                        states2.add(new State(string3));
                        function2.setSecondStates(states2);
                    }
                }
            }
        }
        Set<State> states1=new HashSet<>();
        for(Function function:automation.getFunctions()){
            for(State state:function.getSecondStates()){
                for(State state1:automation1.getFinaleStates()) {
                    for (State state2 : automation.getFinaleStates()) {
                        String[] string=state.getName().split(",");
                        for(String str:string) {
                            if (!Objects.equals(str, state2.getName()) && Objects.equals(str, state1.getName())&&state!=state2) {
                                states1.add(state);
                            }
                        }
                    }
                }
            }
        }
        for(State state:states1){
            for(State state1:automation.getStates()) {
                if (state == state1) {
                    automation.addFinaleState(state);
                }
            }
        }
        machines.setAutomation(id,automation);
        console.print("Automation with id:"+id+" is now deterministic");
    }
}
