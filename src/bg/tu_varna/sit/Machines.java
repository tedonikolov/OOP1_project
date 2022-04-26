package bg.tu_varna.sit;

import javax.swing.text.BadLocationException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

    public void print(int id,Console console) throws BadLocationException {
        Automation automation=getAutomation(id);
        if(automation==null){
            console.print("Automation with ID:"+id+" didn't exist");
        }
        else{
            console.print(automation.serialize()+"");
        }
    }

    public void list(Console console) throws BadLocationException {
        console.print("Number of automations:"+getId());
        List<Integer> list=new ArrayList<>();
        for(Map.Entry<Integer,Automation> entry:automations.entrySet()){
            list.add(entry.getKey());
        }
        console.print("IDs:"+list);
    }

    public void empty(int id,Console console) throws BadLocationException {
        Automation automation=getAutomation(id);
        if(automation.getAlphabet()==null)
            console.print("The alphabet is empty");
        else{
            console.print("The alphabet is not empty. \n It contains the corresponding symbols:"+automation.getAlphabet());
        }
    }
}
