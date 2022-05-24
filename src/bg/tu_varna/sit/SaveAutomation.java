package bg.tu_varna.sit;

import javax.swing.text.BadLocationException;
import java.beans.XMLEncoder;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

/**
 * The SaveAutomation class represent the saving of corresponding automation in a file.
 * @author Teodor
 * @version 1.0
 * @see Console
 * @see Menu
 * @see Commands
 */
public class SaveAutomation {

    /**
     * Saves the automation in a file.
     * @param id stores the number of automations.
     * @param fileName stores the file name.
     * @param machines stores the current machines.
     * @param console stores the console of the program.
     */
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

    /**
     * Saves the automation in txt format.
     * @param fileName stores the file name.
     * @param automation stores the current automation
     */
    public void saveTxt(String fileName, Automation automation) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName);
        fileWriter.write(String.valueOf(automation.serialize()));
        fileWriter.close();
    }

    /**
     * Saves the automation in xml format.
     * @param fileName stores the file name.
     * @param automation stores the current automation
     */
    public void saveXml(String fileName, Automation automation) throws IOException {
        FileOutputStream file= new FileOutputStream(fileName);
        XMLEncoder encoder = new XMLEncoder(file);
        encoder.writeObject(automation);
        encoder.close();
        file.close();
    }
}
