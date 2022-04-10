package bg.tu_varna.sit;

public class Main {

    public static void main(String[] args) {

	    State state1=new State("1");
        Automation automation=new Automation(state1);
        State state2=new State("2");
        automation.addState(state2);
        State state3=new State("3");
        automation.addState(state3);
        State state4=new State("4");
        automation.addState(state4);
        State state5=new State("5");
        automation.addState(state5);
        Symbol symbol1=new Symbol("a");
        automation.addSymbol(symbol1);
        Symbol symbol2=new Symbol("b");
        automation.addSymbol(symbol2);
        Function function1=new Function(state1,symbol1);
        function1.addSecondState(state2);
        automation.addFunction(function1);
        Function function2=new Function(state1,symbol2);
        function2.addSecondState(state5);
        automation.addFunction(function2);
        Function function3=new Function(state2,symbol1);
        function3.addSecondState(state3);
        automation.addFunction(function3);
        Function function4=new Function(state2,symbol2);
        function4.addSecondState(state2);
        automation.addFunction(function4);
        Function function5=new Function(state5,symbol1);
        function5.addSecondState(state3);
        function5.addSecondState(state4);
        automation.addFunction(function5);
        Function function6=new Function(state3,symbol1);
        function6.addSecondState(state4);
        automation.addFunction(function6);
        Function function7=new Function(state4,symbol2);
        function7.addSecondState(state3);
        automation.addFunction(function7);
        automation.addFinaleState(state3);
        System.out.println(automation.serialize());
    }
}
