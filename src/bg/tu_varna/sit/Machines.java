package bg.tu_varna.sit;

import java.util.LinkedHashMap;
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

    public void addAutomation(Automation automation)
    {
        id++;
        automations.put(id,automation);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAutomations(Map<Integer, Automation> automations) {
        this.automations = automations;
    }

    public void print() {
        for(Map.Entry<Integer,Automation> entry:automations.entrySet()){
            System.out.println("ID:"+entry.getKey()+"\n"+entry.getValue().serialize());
        }
    }

    public int getId() {
        return id;
    }
}
