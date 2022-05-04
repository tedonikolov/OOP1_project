package bg.tu_varna.sit;

import javax.swing.text.BadLocationException;
import java.beans.XMLEncoder;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

public class SaveAutomation {

    public void save(int id, String fileName ,Machines machines, Console console) throws BadLocationException, IOException {
        Automation automation=machines.getAutomation(id);
        if(automation==null)
            console.print("Automation with ID:"+id+" didn't exist");
        else {
            String[] type=fileName.split("\\.");
            if(Objects.equals(type[1], "txt")) {
                saveTxt(fileName, automation);
                console.print("Successfully saved " + fileName);
            }
            else if(Objects.equals(type[1], "xml")) {
                saveXml(fileName, automation);
                console.print("Successfully saved " + fileName);
            }
            else {
                console.print("The program didn't support ." + type[1]);
                console.print("File must be .txt or .xml");
            }
        }
    }

    public void saveTxt(String fileName, Automation automation) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName);
        fileWriter.write(String.valueOf(automation.serialize()));
        fileWriter.close();
    }

    public void saveXml(String fileName, Automation automation) throws IOException {
        FileOutputStream file= new FileOutputStream(fileName);
        XMLEncoder encoder = new XMLEncoder(file);
        encoder.writeObject(automation);
        encoder.close();
        file.close();
    }
}
