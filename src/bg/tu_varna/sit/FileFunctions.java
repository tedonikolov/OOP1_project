package bg.tu_varna.sit;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.Objects;
import java.util.Scanner;


public class FileFunctions {
    private String fileName;
    private Machines machines;

    public void menu() throws IOException {
        Scanner scanner = new Scanner(System.in);
        String command;
        boolean flag=true;
        do {
            command = scanner.next();
            switch (command) {
                case "open":
                    String name = scanner.nextLine();
                    if(Objects.equals(name, "") || Objects.equals(name, " ")) {
                        System.out.println("Must enter file name or path.");
                        name = scanner.nextLine();
                    }
                    else
                        name = name.substring(1);
                    if(fileName==null) {
                        fileName=name;
                        open();
                    }
                    else
                        System.out.println("You have already opened file!");
                    break;
                case "close":
                    if(fileName!=null)
                        close();
                    else
                        System.out.println("You first must open a file!");
                    break;
                case "save":
                    if(fileName!=null)
                        save();
                    else
                        System.out.println("You first must open a file!");
                    break;
                case "saveas":
                    name = scanner.nextLine();
                    if(Objects.equals(name, "") || Objects.equals(name, " ")) {
                        System.out.println("Must enter file name or path.");
                        name = scanner.nextLine();
                    }
                    else
                    name = name.substring(1);
                    if(fileName!=null) {
                        saveAs(name);
                    }
                    else
                        System.out.println("You first must open a file!");
                    break;
                case "help":
                    help();
                    break;
                case "exit":
                    flag=false;
                    exit();
                    break;
                default:
                    System.out.println("There is not such a command! \nPlease type: help");
            }
        }while(flag);
    }

    public void open() throws IOException {
        File file = new File(fileName);
        if(file.exists()) {
            FileInputStream fileOpen = new FileInputStream(fileName);
            if(fileOpen.available() != 0){
                XMLDecoder decoder = new XMLDecoder(fileOpen);
                machines = (Machines) decoder.readObject();
                decoder.close();
                fileOpen.close();
            }
            System.out.println("Successfully opened " + fileName);
        }
        else {
            boolean newFile = file.createNewFile();
            System.out.println("Successfully created "+fileName);
        }
    }

    public void close(){
        System.out.println("Successfully closed "+fileName);
        fileName=null;
        machines=null;
    }

    public void save() throws IOException {
        FileOutputStream file= new FileOutputStream(fileName);
        XMLEncoder encoder = new XMLEncoder(file);
        encoder.writeObject(machines);
        encoder.close();
        file.close();
        System.out.println("Successfully saved "+fileName);
    }

    public void saveAs(String name) throws IOException {
        FileOutputStream file= new FileOutputStream(name);
        XMLEncoder encoder = new XMLEncoder(file);
        encoder.writeObject(machines);
        encoder.close();
        file.close();
        System.out.println("Successfully saved "+name);
    }

    public void help()
    {
        System.out.println("The following commands are supported:");
        System.out.println("open <file> \topens <file>");
        System.out.println("close \t\t\tcloses currently opened file");
        System.out.println("save \t\t\tsaves the currently open file");
        System.out.println("saveas <file> \tsaves the currently open file in <file>");
        System.out.println("help \t\t\tprints this information");
        System.out.println("exit \t\t\texists the program \n");
    }

    public void exit()
    {
        System.out.println("Exiting the program...");
        System.exit(0);
    }
}
