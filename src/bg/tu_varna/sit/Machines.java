package bg.tu_varna.sit;

import javax.swing.text.BadLocationException;
import java.util.*;

/**
 * The Machines class represent the map of finite state machines, each automation with a unique identifier.
 * @author Teodor
 * @version 1.0
 * @see Automation
 */
public class Machines {
    /**
     * Property "id" represent the current number of automations in the map.
     */
    private int id=0;
    /**
     * Property "automations" represent the map of automations, each with a unique identifier.
     */
    private Map<Integer,Automation> automations;

    /**
     * Empty constructor for the usage of JavaBean xml tools.
     */
    public Machines() {
        automations = new LinkedHashMap<>();
    }

    /**
     * Getter for property "automations".
     *
     * @return the map of automations with their ids.
     */
    public Map<Integer, Automation> getAutomations() {
        return automations;
    }

    /**
     * Getter for property "id".
     *
     * @return the number of automations.
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for property "id".
     *
     * @param id stores the number of automations.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Setter for property "automations".
     *
     * @param automations stores the map of automations.
     */
    public void setAutomations(Map<Integer, Automation> automations) {
        this.automations = automations;
    }

    /**
     * Adds automation in the map of the automations.
     *
     * @param automation stores an automation from the map of automations.
     */
    public void addAutomation(Automation automation)
    {
        id++;
        automations.put(id,automation);
    }

    @Override
    public String toString() {
        String string = "";
        for(Map.Entry<Integer,Automation> entry:automations.entrySet()){
           string+="Automation ID:"+entry.getKey()+"\n"+entry.getValue().serialize()+"\n";
        }
        return string;
    }

    /**
     * Getter for property "automation" from the map of automations.
     *
     * @param id stores the id of the automation.
     * @return the Automation.
     */
    public Automation getAutomation(int id){
        for(Map.Entry<Integer,Automation> entry:automations.entrySet()){
            if(id==entry.getKey()) {
                return entry.getValue();
            }
        }
        return null;
    }

    /**
     * Setter for property "automation" in the map "automations".
     *
     * @param id stores the id of the replaced automation.
     * @param automation stores the automation from the map of automations.
     */
    public void setAutomation(int id, Automation automation){
        for(Map.Entry<Integer,Automation> entry:automations.entrySet()){
            if(id==entry.getKey()) {
                entry.setValue(automation);
            }
        }
    }

    /**
     * List of identifiers of all read automations.
     * @param console stores the console of the program.
     */
    public void list(Console console) throws BadLocationException {
        console.print("Number of automations:"+getId());
        List<Integer> list=new ArrayList<>();
        for(Map.Entry<Integer,Automation> entry:automations.entrySet()){
            list.add(entry.getKey());
        }
        console.print("IDs:"+list);
    }

    /**
     * Displays information about all transitions in the automation.
     * @param id stores the id of the current automation.
     * @param console stores the console of the program.
     */
    public void print(int id,Console console) throws BadLocationException {
        Automation automation=getAutomation(id);
        if(automation==null){
            console.print("Automation with ID:"+id+" didn't exist");
        } else{
            console.print(automation.serialize()+"");
        }
    }

    /**
     * Checks if the language of the automation is empty.
     * @param id stores the id of the current automation.
     * @param console stores the console of the program.
     */
    public void empty(int id,Console console) throws BadLocationException {
        Automation automation=getAutomation(id);
        if(automation==null){
            console.print("Automation with ID:"+id+" didn't exist");
        }
        else {
            if (automation.getAlphabet() == null) {
                console.print("The alphabet is empty");
            }else {
                console.print("The alphabet is not empty. \n It contains the corresponding symbols:" + automation.getAlphabet());
            }
        }
    }

    /**
     * Checks if the automaton is deterministic.
     * @param id stores the id of the current automation.
     * @param console stores the console of the program.
     */
    public void deterministic(int id,Console console) throws BadLocationException {
        Automation automation=getAutomation(id);
        if(automation==null){
            console.print("Automation with ID:"+id+" didn't exist");
        } else {
            boolean flag = false;
            for (Function function : automation.getFunctions()) {
                if (function.getSecondStates().size() > 1) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                console.print("The automation is nondeterministic");
            } else {
                console.print("The automation is deterministic");
            }
        }
    }

    /**
     * Checks if a word is in the language of the automation.
     * @param id stores the id of the current automation.
     * @param word stores the checked word.
     * @param console stores the console of the program.
     */
    public void recognize(int id,String word,Console console) throws BadLocationException {
        Automation automation=getAutomation(id);
        boolean flag=false;
        int k=0;
        if(automation==null){
            console.print("Automation with ID:"+id+" didn't exist");
        } else {
            List<State> states = null;
            String[] symbol=word.split("");
            for(Function function: automation.getFunctions()){
                if(automation.getStartState()==function.getFirstState()){
                    if(Objects.equals(symbol[0], function.getSymbol().getName())) {
                        states=function.getSecondStates();
                        flag=true;
                        k=1;
                        break;
                    }
                    else if(Objects.equals(function.getSymbol().getName(), "∅")){
                        states=function.getSecondStates();
                        flag=true;
                        break;
                    }
                }
            }
            if(flag) {
                for(int i=k;i<symbol.length;i++){
                    for (Function function: automation.getFunctions()){
                        flag=false;
                        for(State state:states){
                            if(state==function.getFirstState()){
                                if(Objects.equals(symbol[i], function.getSymbol().getName())) {
                                    states=function.getSecondStates();
                                    flag=true;
                                    break;
                                }
                            }
                        }
                        if(flag)
                            break;
                    }
                    if(!flag)
                        break;
                }
                if (flag) {
                    for (State state : states) {
                        for (State lastState : automation.getFinaleStates()) {
                            if (state != lastState) {
                                flag = false;
                            }else{
                                flag=true;
                                break;
                            }
                        }
                        if(flag)
                            break;
                    }
                }
            }
        }
        if(flag){
            console.print("the word \""+word+"\" is from the automation with id:"+id);
        }
        else{
            console.print("the word \""+word+"\" is not from the automation with id:"+id);
        }
    }

    /**
     * Check whether the language of the automation is finite.
     * @param id stores the id of the current automation.
     * @param console stores the console of the program.
     */
    public void finite(int id,Console console) throws BadLocationException {
        Automation automation = getAutomation(id);
        if (automation == null) {
            console.print("Automation with ID:" + id + " didn't exist");
        } else {
            if(automation.getFinaleStates().size()==0){
                console.print("Automation with ID:" + id + " is not finite");
            }
            else{
                console.print("Automation with ID:" + id + " is finite");
            }
        }
    }
}
