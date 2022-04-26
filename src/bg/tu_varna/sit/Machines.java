package bg.tu_varna.sit;

import javax.swing.text.BadLocationException;
import java.util.*;

public class Machines {
    private int id=0;
    private Map<Integer,Automation> automations;

    public Machines() {
        automations = new LinkedHashMap<>();
    }

    public Map<Integer, Automation> getAutomations() {
        return automations;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAutomations(Map<Integer, Automation> automations) {
        this.automations = automations;
    }

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

    public Automation getAutomation(int id){
        for(Map.Entry<Integer,Automation> entry:automations.entrySet()){
            if(id==entry.getKey()) {
                return entry.getValue();
            }
        }
        return null;
    }

    public void list(Console console) throws BadLocationException {
        console.print("Number of automations:"+getId());
        List<Integer> list=new ArrayList<>();
        for(Map.Entry<Integer,Automation> entry:automations.entrySet()){
            list.add(entry.getKey());
        }
        console.print("IDs:"+list);
    }

    public void print(int id,Console console) throws BadLocationException {
        Automation automation=getAutomation(id);
        if(automation==null){
            console.print("Automation with ID:"+id+" didn't exist");
        } else{
            console.print(automation.serialize()+"");
        }
    }

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

    public void recognize(int id,String word,Console console) throws BadLocationException {
        Automation automation=getAutomation(id);
        boolean flag=false;
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
                        break;
                    }
                }
            }
            if(flag) {
                for(int i=1;i<symbol.length;i++){
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
}
