package bg.tu_varna.sit;

import javax.swing.text.BadLocationException;
import java.util.*;

public class Operations {

    public void union(int id1,int id2,Machines machines,Console console) throws BadLocationException {
        Automation automation1=machines.getAutomation(id1);
        State startState=new State(automation1.getStartState().getName());
        Automation automation11=new Automation(startState);
        automation11.setAlphabet(automation1.getAlphabet());
        Automation automation2=machines.getAutomation(id2);
        Automation automation22=new Automation(new State(automation2.getStartState().getName()));
        automation22.setAlphabet(automation2.getAlphabet());
        State startStates=new State("1");
        Automation automation=new Automation(startStates);

        Symbol symbol=new Symbol("∅");
        automation.addSymbol(symbol);
        Set<Symbol> symbols=new HashSet<>();
        for(Symbol symbol1:automation.getAlphabet()){
            for(Symbol symbol2:automation11.getAlphabet()){
                if(!Objects.equals(symbol1.getName(), symbol2.getName())){
                    symbols.add(symbol2);
                }
            }
            for(Symbol symbol2:automation22.getAlphabet()){
                if(!Objects.equals(symbol1.getName(), symbol2.getName())){
                    symbols.add(symbol2);
                }
            }
        }

        for(Symbol symbol1:symbols){
            automation.addSymbol(symbol1);
        }

        automation11.setAlphabet(automation.getAlphabet());

        for(Function function:automation1.getFunctions()){
            State state=new State(function.getFirstState().getName());
            boolean flag=true;
            for(State state1:automation11.getStates()){
                if (Objects.equals(state.getName(), state1.getName())) {
                    state=state1;
                    flag = false;
                    break;
                }
            }
            if(flag){
                automation11.addState(state);
            }
            Function function1=new Function(state,function.getSymbol());
            for(State state1:function.getSecondStates()){
                State state2=new State(state1.getName());
                flag=true;
                for(State state3:automation11.getStates()){
                    if(Objects.equals(state2.getName(), state3.getName())){
                        state2=state3;
                        flag=false;
                        break;
                    }
                }
                if(flag){
                    automation11.addState(state2);
                }
                function1.addSecondState(state2);
            }
            automation11.addFunction(function1);
        }
        for(State state:automation1.getFinaleStates()){
            for(State state1:automation11.getStates()){
                if(Objects.equals(state.getName(), state1.getName())){
                    automation11.addFinaleState(state1);
                }
            }
        }

        for(Function function:automation2.getFunctions()){
            State state=new State(function.getFirstState().getName());
            boolean flag=true;
            for(State state1:automation22.getStates()){
                if (Objects.equals(state.getName(), state1.getName())) {
                    state=state1;
                    flag = false;
                    break;
                }
            }
            if(flag){
                automation22.addState(state);
            }
            Function function1=new Function(state,function.getSymbol());
            for(State state1:function.getSecondStates()){
                State state2=new State(state1.getName());
                flag=true;
                for(State state3:automation22.getStates()){
                    if(Objects.equals(state2.getName(), state3.getName())){
                        state2=state3;
                        flag=false;
                        break;
                    }
                }
                if(flag){
                    automation22.addState(state2);
                }
                function1.addSecondState(state2);
            }
            automation22.addFunction(function1);
        }
        for(State state:automation2.getFinaleStates()) {
            for (State state1 : automation22.getStates()) {
                if (Objects.equals(state.getName(), state1.getName())) {
                    automation22.addFinaleState(state1);
                }
            }
        }

        automation22.setAlphabet(automation.getAlphabet());
        for(Function function:automation22.getFunctions()){
            for(Symbol symbol1: automation22.getAlphabet()){
                if(Objects.equals(symbol1.getName(), function.getSymbol().getName())){
                    function.setSymbol(symbol1);
                }
            }
        }

        Function function=new Function(startStates,symbol);
        int number=2;
        int br=1;
        for(State state:automation11.getStates()){
            state.setName(String.valueOf(number));
            if(br==1){
                function.addSecondState(state);
            }
            automation.addState(state);
            number++;
            br++;
        }

        br=1;
        for(State state:automation22.getStates()){
            state.setName(String.valueOf(number));
            if(br==1){
                function.addSecondState(state);
            }
            automation.addState(state);
            number++;
            br++;
        }

        automation.addFunction(function);

        for (Function function1:automation11.getFunctions()) {
            automation.addFunction(function1);
        }
        for (Function function1:automation22.getFunctions()) {
            automation.addFunction(function1);
        }

        for(State state:automation11.getFinaleStates()){
            automation.addFinaleState(state);
        }
        for(State state:automation22.getFinaleStates()){
          automation.addFinaleState(state);
        }

        machines.addAutomation(automation);

        console.print("The new automation id is:"+machines.getId());
    }

    public void concat(int id1,int id2,Machines machines,Console console) throws BadLocationException {
        Automation automation1=machines.getAutomation(id1);
        State startState=new State(automation1.getStartState().getName());
        Automation automation11=new Automation(startState);
        automation11.setAlphabet(automation1.getAlphabet());
        Automation automation2=machines.getAutomation(id2);
        Automation automation22=new Automation(new State(automation2.getStartState().getName()));
        automation22.setAlphabet(automation2.getAlphabet());
        Automation automation=new Automation(automation1.getStartState());

        Set<Symbol> symbols=new HashSet<>();
        symbols.addAll(automation11.getAlphabet());
        symbols.addAll(automation22.getAlphabet());

        for(Symbol symbol1:symbols){
            automation.addSymbol(symbol1);
        }

        automation11.setAlphabet(automation.getAlphabet());

        for(Function function:automation1.getFunctions()){
            State state=new State(function.getFirstState().getName());
            boolean flag=true;
            for(State state1:automation11.getStates()){
                if (Objects.equals(state.getName(), state1.getName())) {
                    state=state1;
                    flag = false;
                    break;
                }
            }
            if(flag){
                automation11.addState(state);
            }
            Function function1=new Function(state,function.getSymbol());
            for(State state1:function.getSecondStates()){
                State state2=new State(state1.getName());
                flag=true;
                for(State state3:automation11.getStates()){
                    if(Objects.equals(state2.getName(), state3.getName())){
                        state2=state3;
                        flag=false;
                        break;
                    }
                }
                if(flag){
                    automation11.addState(state2);
                }
                function1.addSecondState(state2);
            }
            automation11.addFunction(function1);
        }
        for(State state:automation1.getFinaleStates()){
            for(State state1:automation11.getStates()){
                if(Objects.equals(state.getName(), state1.getName())){
                    automation11.addFinaleState(state1);
                }
            }
        }

        for(Function function:automation2.getFunctions()){
            State state=new State(function.getFirstState().getName());
            boolean flag=true;
            for(State state1:automation22.getStates()){
                if (Objects.equals(state.getName(), state1.getName())) {
                    state=state1;
                    flag = false;
                    break;
                }
            }
            if(flag){
                automation22.addState(state);
            }
            Function function1=new Function(state,function.getSymbol());
            for(State state1:function.getSecondStates()){
                State state2=new State(state1.getName());
                flag=true;
                for(State state3:automation22.getStates()){
                    if(Objects.equals(state2.getName(), state3.getName())){
                        state2=state3;
                        flag=false;
                        break;
                    }
                }
                if(flag){
                    automation22.addState(state2);
                }
                function1.addSecondState(state2);
            }
            automation22.addFunction(function1);
        }
        for(State state:automation2.getFinaleStates()) {
            for (State state1 : automation22.getStates()) {
                if (Objects.equals(state.getName(), state1.getName())) {
                    automation22.addFinaleState(state1);
                }
            }
        }

        automation22.setAlphabet(automation.getAlphabet());
        for(Function function:automation22.getFunctions()){
            for(Symbol symbol1: automation22.getAlphabet()){
                if(Objects.equals(symbol1.getName(), function.getSymbol().getName())){
                    function.setSymbol(symbol1);
                }
            }
        }

        automation.setFunctions(automation11.getFunctions());

        int number=0;
        for(State state:automation11.getStates()){
            if(number<Integer.parseInt(state.getName())) {
                number = Integer.parseInt(state.getName());
            }
        }



        for(State state:automation11.getFinaleStates()){
            for(Function function:automation22.getFunctions()){
                if(function.getFirstState()==automation22.getStartState()){
                    number++;
                    function.getFirstState().setName(Integer.toString(number));
                    automation.addFunction(function);
                    Function function1=new Function(state,function.getSymbol());
                    for(State state1:function.getSecondStates()){
                        state1.setName(Integer.toString(number));
                        for(Function function2:automation11.getFunctions()){
                            if (function2.getFirstState() == function1.getFirstState() && function2.getSymbol() == function1.getSymbol()) {
                                function2.addSecondState(state1);
                            }
                        }
                    }
                }
                else{
                    function.getFirstState().setName(Integer.toString(number));
                    number++;
                    automation.addFunction(function);
                }
            }
        }

        automation.setFinaleStates(automation22.getFinaleStates());

        Set<State> states=new HashSet<>();
        for(Function function:automation.getFunctions()){
            if(Objects.equals(function.getFirstState().getName(), automation.getStartState().getName())){
                function.setFirstState(automation.getStartState());
            }

            states.addAll(function.getSecondStates());
            states.add(function.getFirstState());
        }
        for(State state:states){
            if(state!=automation.getStartState()) {
                automation.addState(state);
            }
        }
        machines.addAutomation(automation);
        console.print("The new automation id is:"+machines.getId());
    }
}
