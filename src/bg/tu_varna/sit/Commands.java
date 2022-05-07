package bg.tu_varna.sit;

import javax.swing.text.BadLocationException;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;


public class Commands {
    private String fileName;
    private Machines machines;
    private String text;
    private Console console;

    public Commands(Console console) {
        this.console = console;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void menu() throws BadLocationException, IOException {

        String[] command=text.split(" ");
        switch (command[0]) {
            case "open":
                if (fileName == null) {
                    if(command.length>2) {
                        for (int i = 2; i < command.length; i++)
                            command[1] = command[1] + " " + command[i];
                    }
                    fileName = command[1];
                    open();
                } else
                    console.print("You have already opened file!");
                break;
            case "close":
                if (fileName != null)
                    close();
                else
                    console.print("You first must open a file!");
                break;
            case "save":
                if (fileName != null)
                    if(command.length==1)
                        save();
                    else {
                        if(command.length>3) {
                            for (int i = 3; i < command.length; i++)
                                command[2] = command[2] + " " + command[i];
                        }
                        SaveAutomation saveAutomation=new SaveAutomation();
                        saveAutomation.save(Integer.parseInt(command[1]),command[2],machines,console);
                    }
                else
                    console.print("You first must open a file!");
                break;
            case "saveas":
                if (fileName != null) {
                    if(command.length>2) {
                        for (int i = 2; i < command.length; i++)
                            command[1] = command[1] + " " + command[i];
                    }
                    saveAs(command[1]);
                } else
                    console.print("You first must open a file!");
                break;
            case "help":
                help();
                break;
            case "exit":
                exit();
                break;
            case "menu":
                if (fileName != null) {
                    subMenu();
                } else
                    console.print("You first must open a file!");
                break;
            case "list":
                if (fileName != null) {
                    machines.list(console);
                } else
                    console.print("You first must open a file!");
                break;
            case "print":
                if (fileName != null) {
                    machines.print(Integer.parseInt(command[1]),console);
                } else
                    console.print("You first must open a file!");
                break;
            case "empty":
                if (fileName != null) {
                    machines.empty(Integer.parseInt(command[1]),console);
                } else
                    console.print("You first must open a file!");
                break;
            case "deterministic":
                if (fileName != null) {
                    machines.deterministic(Integer.parseInt(command[1]),console);
                } else
                    console.print("You first must open a file!");
                break;
            case "recognize":
                if (fileName != null) {
                    machines.recognize(Integer.parseInt(command[1]),command[2], console);
                } else
                    console.print("You first must open a file!");
                break;
            case "union":
                if (fileName != null) {
                    Operation operation = new Operation();
                    operation.union(Integer.parseInt(command[1]),Integer.parseInt(command[2]),machines,console);
                } else
                    console.print("You first must open a file!");
                break;
            default:
                console.print("There is not such a command! \nPlease type: help");
        }
    }

    public void open() throws IOException, BadLocationException {
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
    }

    public void close() throws BadLocationException {
        console.print("Successfully closed "+fileName);
        fileName=null;
        machines=null;
    }

    public void save() throws IOException, BadLocationException {
        FileOutputStream file= new FileOutputStream(fileName);
        XMLEncoder encoder = new XMLEncoder(file);
        encoder.writeObject(machines);
        encoder.close();
        file.close();
        console.print("Successfully saved "+fileName);
    }

    public void saveAs(String name) throws IOException, BadLocationException {
        FileOutputStream file= new FileOutputStream(name);
        XMLEncoder encoder = new XMLEncoder(file);
        encoder.writeObject(machines);
        encoder.close();
        file.close();
        console.print("Successfully saved "+name);
    }

    public void help() throws BadLocationException {
        console.print("The following commands are supported:");
        console.print("menu \t\tmenu of the program");
        console.print("open <file> \topens <file>");
        console.print("close \t\tcloses currently opened file");
        console.print("save \t\tsaves the currently open file");
        console.print("saveas <file> \tsaves the currently open file in <file>");
        console.print("help \t\tprints this information");
        console.print("exit \t\texists the program");
    }

    public void exit() throws BadLocationException {
        console.print("Exiting the program...");
        System.exit(0);
    }

    public void subMenu() throws BadLocationException {
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
        console.print("closed <id> \t\tcheck if the automation is closed-loop");
    }
}
