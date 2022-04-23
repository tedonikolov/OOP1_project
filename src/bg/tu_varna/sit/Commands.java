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
        if(command.length>2){
            for (int i=2;i<command.length;i++)
                command[1]=command[1]+" "+command[i];
        }
        switch (command[0]) {
            case "open":
                if (fileName == null) {
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
                    save();
                else
                    console.print("You first must open a file!");
                break;
            case "saveas":
                if (fileName != null) {
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
        console.print("open <file> \topens <file>");
        console.print("close \t\tcloses currently opened file");
        console.print("save \t\tsaves the currently open file");
        console.print("saveas <file> \tsaves the currently open file in <file>");
        console.print("help \t\tprints this information");
        console.print("exit \t\texists the program \n");
    }

    public void exit() throws BadLocationException {
        console.print("Exiting the program...");
        System.exit(0);
    }
}
