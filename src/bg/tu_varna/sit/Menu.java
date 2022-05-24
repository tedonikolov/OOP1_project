package bg.tu_varna.sit;

import javax.swing.text.BadLocationException;
import java.io.IOException;

/**
 * The Menu class represent the menu of the program.
 * @author Teodor
 * @version 1.0
 * @see Console
 * @see Commands
 * @see Machines
 */
public class Menu {
    /**
     * Property "fileName" represent the name of the opened file in the program.
     */
    private String fileName;
    /**
     * Property "commands" represent the commands provided by the program.
     */
    private final Commands commands;
    /**
     * Property "machines" represent the map of the automations currently existing in the program.
     */
    private Machines machines;
    /**
     * Property "text" represent the current input from the console.
     */
    private String text;
    /**
     * Property "console" represent the console of the program.
     */
    private final Console console;

    /**
     * Explicit value constructor for Menu. Accepts only the mandatory console.
     *
     * @param console stores the console of the program.
     */
    public Menu(Console console) {
        this.console = console;
        commands=new Commands();
    }

    /**
     * Get the input from the console.
     * @param text stores the input from the console.
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Execute the corresponding function according to the input text.
     */
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
                    machines=commands.open(console,fileName);
                } else
                    console.print("You have already opened file!");
                break;
            case "close":
                if (fileName != null) {
                    commands.close(console, fileName);
                    fileName=null;
                    machines=null;
                }
                else
                    console.print("You first must open a file!");
                break;
            case "save":
                if (fileName != null)
                    if(command.length==1)
                        commands.save(console,fileName,machines);
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
                    commands.save(console,command[1],machines);
                } else
                    console.print("You first must open a file!");
                break;
            case "help":
                commands.help(console);
                break;
            case "exit":
                commands.exit(console);
                break;
            case "menu":
                if (fileName != null) {
                    commands.subMenu(console);
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
                    Operations operation = new Operations();
                    operation.union(Integer.parseInt(command[1]),Integer.parseInt(command[2]),machines,console);
                } else
                    console.print("You first must open a file!");
                break;
            case "concat":
                if (fileName != null) {
                    Operations operation = new Operations();
                    operation.concat(Integer.parseInt(command[1]),Integer.parseInt(command[2]),machines,console);
                } else
                    console.print("You first must open a file!");
                break;
            case "mutator":
                if (fileName != null) {
                    Mutator mutator = new Mutator();
                    mutator.mutator(Integer.parseInt(command[1]),machines,console);
                } else
                    console.print("You first must open a file!");
                break;
            case "reg":
                if (fileName != null) {
                    NewAutomation newAutomation=new NewAutomation();
                    newAutomation.reg(command[1],machines,console);
                } else
                    console.print("You first must open a file!");
                break;
            case "finite":
                if (fileName != null) {
                    machines.finite(Integer.parseInt(command[1]),console);
                } else
                    console.print("You first must open a file!");
                break;
            default:
                console.print("There is not such a command! \nPlease type: help");
        }
    }
}
