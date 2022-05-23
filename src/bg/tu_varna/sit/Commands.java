package bg.tu_varna.sit;

import javax.swing.text.BadLocationException;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;


public class Commands {
    private Machines machines;

    public Machines open(Console console, String fileName) throws IOException, BadLocationException {
        File file = new File(fileName);
        if(file.exists()) {
            FileInputStream fileOpen = new FileInputStream(fileName);
            if(fileOpen.available() != 0){
                XMLDecoder decoder = new XMLDecoder(fileOpen);
                machines = (Machines) decoder.readObject();
                decoder.close();
                fileOpen.close();
            }
            console.print("Successfully opened " + fileName);
        }
        else {
            boolean newFile = file.createNewFile();
            console.print("Successfully created "+fileName);
        }
        return machines;
    }

    public void close(Console console, String fileName) throws BadLocationException {
        console.print("Successfully closed "+fileName);
    }

    public void save(Console console, String fileName) throws IOException, BadLocationException {
        FileOutputStream file= new FileOutputStream(fileName);
        XMLEncoder encoder = new XMLEncoder(file);
        encoder.writeObject(machines);
        encoder.close();
        file.close();
        console.print("Successfully saved "+fileName);
    }

    public void saveAs(String name,Console console) throws IOException, BadLocationException {
        FileOutputStream file= new FileOutputStream(name);
        XMLEncoder encoder = new XMLEncoder(file);
        encoder.writeObject(machines);
        encoder.close();
        file.close();
        console.print("Successfully saved "+name);
    }

    public void help(Console console) throws BadLocationException {
        console.print("The following commands are supported:");
        console.print("menu \t\tmenu of the program");
        console.print("open <file> \topens <file>");
        console.print("close \t\tcloses currently opened file");
        console.print("save \t\tsaves the currently open file");
        console.print("saveas <file> \tsaves the currently open file in <file>");
        console.print("help \t\tprints this information");
        console.print("exit \t\texists the program");
    }

    public void exit(Console console) throws BadLocationException {
        console.print("Exiting the program...");
        System.exit(0);
    }

    public void subMenu(Console console) throws BadLocationException {
        console.print("list \t\t\tIDs of the automations");
        console.print("print <id> \t\tprint the automation");
        console.print("save <id> <filename> \tsaves automation in file");
        console.print("empty <id> \t\tcheck if the alphabet is empty");
        console.print("deterministic <id> \tcheck if the automation is determined");
        console.print("recognize <id1> <word> \tcheck if the word is from the automation");
        console.print("union <id1> <id2> \tunion of two automations");
        console.print("concat <id1> <id2> \tconcatenation of two automations");
        console.print("un <id> \t\t\tpositive envelope of automation");
        console.print("reg <regex> \t\tKlini's theorem");
        console.print("mutator <id> \t\tmake automation deterministic");
        console.print("finite <id> \t\tcheck if the automation is closed-loop");
    }
}
