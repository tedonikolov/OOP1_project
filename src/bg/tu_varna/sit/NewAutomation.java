package bg.tu_varna.sit;

import javax.swing.text.BadLocationException;
import java.util.*;

public class NewAutomation {

    public void reg(String regex, Machines machines, Console console) throws BadLocationException {
        String[] symbols = regex.split("");
        Set<String> strings = new HashSet<>();
        for (String s : symbols) {
            if (!Objects.equals(s, "+") && !Objects.equals(s, "*") && !Objects.equals(s, "(") && !Objects.equals(s, ")") && !Objects.equals(s, ",") && !Objects.equals(s, "[") && !Objects.equals(s, "]") && !Objects.equals(s, ".")) {
                strings.add(s);
            }
        }

        int number = 1;
        State state = new State(Integer.toString(number));
        Automation automation = new Automation(state);
        for (String s : strings) {
            automation.addSymbol(new Symbol(s));
        }

        String[] split = regex.split("");
        State stateSave = state;
        State stateSave2 = state;
        State stateTemp = state;
        State stateTemp2 = state;
        boolean flag1 = true;
        boolean flag5 = true;
        boolean flag6 = true;
        for (int i = 0; i < split.length; i++) {
            if (Objects.equals(split[i], "(")) {
                for (State state1 : automation.getStates()) {
                    if (Objects.equals(state1.getName(), Integer.toString(number))) {
                        stateTemp = state1;
                        stateTemp2 = state1;
                    }
                }
            } else if (Objects.equals(split[i], ")") && Objects.equals(split[i + 1], "*")) {
                for (Function function : automation.getFunctions()) {
                    if (Objects.equals(function.getFirstState(), stateSave)) {
                        for(State state2: function.getSecondStates()) {
                            if(!Objects.equals(function.getFirstState(), state2)) {
                                List<State> states = new ArrayList<>();
                                boolean flag7 = false;
                                for (State state1 : function.getSecondStates()) {
                                    if (state1 != stateTemp) {
                                        states.add(stateTemp);
                                        flag7 = true;
                                    }
                                }
                                if (flag7) {
                                    function.setSecondStates(states);
                                }
                                flag1 = false;
                            }
                        }
                    }
                }
            } else if (Objects.equals(split[i], "*") && flag1) {
                boolean flag3 = true;
                for (Function function : automation.getFunctions()) {
                    if (Objects.equals(function.getFirstState(), stateSave)) {
                        function.addSecondState(stateSave);
                        flag3 = false;
                        break;
                    }
                }
                if (flag3) {
                    for (Symbol symbol : automation.getAlphabet()) {
                        if (Objects.equals(symbol.getName(), split[i - 1])) {
                            Function function = new Function(stateSave, symbol);
                            function.addSecondState(stateSave);
                            automation.addFunction(function);
                            break;
                        }
                    }
                }
                flag6 = false;
            } else if (Objects.equals(split[i], "+")) {
                flag5 = false;
            } else {
                for (Symbol symbol : automation.getAlphabet()) {
                    if (Objects.equals(symbol.getName(), split[i])) {
                        State state2 = new State(Integer.toString(number));
                        boolean flag = false;
                        for (State state1 : automation.getStates()) {
                            if (Objects.equals(state1.getName(), Integer.toString(number))) {
                                if (!flag1) {
                                    boolean flag2 = false;
                                    Function function1 = new Function(stateTemp, symbol);
                                    for (Function function : automation.getFunctions()) {
                                        if (Objects.equals(function.getFirstState(), stateTemp)) {
                                            if (function.getSymbol() == symbol) {
                                                function.addSecondState(state1);
                                            } else {
                                                List<State> states = new ArrayList<>();
                                                states.add(state1);
                                                function1.setSecondStates(states);
                                                flag2 = true;
                                            }
                                        }
                                    }
                                    if (flag2) {
                                        automation.addFunction(function1);
                                    }
                                    flag1 = true;
                                } else if (!flag5) {
                                    boolean flag2 = false;
                                    Function function1 = new Function(stateTemp2, symbol);
                                    if(i+1<split.length&&!Objects.equals(split[i+1], ")")) {
                                        for (Function function : automation.getFunctions()) {
                                            if (Objects.equals(function.getFirstState(), stateTemp2)) {
                                                if (function.getSymbol() == symbol) {
                                                    function.addSecondState(state1);
                                                } else {
                                                    List<State> states = new ArrayList<>();
                                                    number++;
                                                    state2.setName(Integer.toString(number));
                                                    states.add(state2);
                                                    function1.setSecondStates(states);
                                                    flag2 = true;
                                                    flag=true;
                                                }
                                            }
                                        }
                                        if (flag2) {
                                            automation.addFunction(function1);
                                        }
                                    }else if(i+1==split.length){
                                        state2.setName(Integer.toString(number));
                                        Function function = new Function(stateTemp, symbol);
                                        function.addSecondState(stateSave2);
                                        stateSave2 = stateTemp;
                                        automation.addFunction(function);
                                    }else{
                                        if(i+2==split.length){
                                            break;
                                        }
                                        if (i + 1 < split.length && Objects.equals(split[i + 2], "*")) {
                                            state2.setName(Integer.toString(number));
                                            Function function = new Function(stateTemp, symbol);
                                            function.addSecondState(stateTemp);
                                            stateSave2 = stateTemp;
                                            automation.addFunction(function);
                                        } else{
                                            state2.setName(Integer.toString(number));
                                            Function function = new Function(state1, symbol);
                                            function.addSecondState(stateSave2);
                                            stateSave = state1;
                                            automation.addFunction(function);
                                        }
                                        flag5 = true;
                                    }
                                } else if (!flag6) {
                                    boolean flag2 = false;
                                    Function function1 = new Function(stateSave, symbol);
                                    for (Function function : automation.getFunctions()) {
                                        if (Objects.equals(function.getFirstState(), stateSave)) {
                                            if (function.getSymbol() == symbol) {
                                                function.addSecondState(state1);
                                            } else {
                                                List<State> states = new ArrayList<>();
                                                states.add(state1);
                                                function1.setSecondStates(states);
                                                flag2 = true;
                                            }
                                        }
                                    }
                                    if (flag2) {
                                        automation.addFunction(function1);
                                    }
                                    flag6 = true;
                                } else {
                                    state1=stateSave2;
                                    stateSave = state1;
                                    number++;
                                    flag = true;
                                    state2.setName(Integer.toString(number));
                                    stateSave2=state2;
                                    Function function = new Function(state1, symbol);
                                    function.addSecondState(state2);
                                    if (i + 1 < split.length && !Objects.equals(split[i + 1], "*")) {
                                        automation.addFunction(function);
                                    } else if (i + 1 == split.length) {
                                        automation.addFunction(function);
                                    }
                                }
                                break;
                            }
                        }
                        if (flag) {
                            automation.addState(state2);
                            break;
                        }
                    }
                }
            }
        }
        machines.addAutomation(automation);
        console.print("The new automation id is:" + machines.getId());
    }
}

