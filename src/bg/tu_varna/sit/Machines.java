package bg.tu_varna.sit;

import java.util.LinkedHashMap;
import java.util.Map;

public class Machines {
    private int id=1;
    private Map<Integer,Automation> automations;

    public Machines() {
        automations = new LinkedHashMap<>();
    }

    public void addAutomation(Automation automation)
    {
        automations.put(id,automation);
        id++;
    }

    public void print() {
        for(Map.Entry<Integer,Automation> entry:automations.entrySet()){
            System.out.println("ID:"+entry.getKey()+"\n"+entry.getValue().serialize());
        }
    }
}
