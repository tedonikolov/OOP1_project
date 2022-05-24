package bg.tu_varna.sit;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

/**
 * The Console class represent a command prompt window with simple interface.
 * @author Teodor
 * @version 1.0
 */
public class Console {
    /**
     * Property "frame" represent the console frame.
     */
    private JFrame frame;
    /**
     * Property "console" represent the console view area.
     */
    private JTextPane console;
    /**
     * Property "input" represent the input area.
     */
    private JTextField input;
    /**
     * Property "scrollPane" represent the scrollbar.
     */
    private JScrollPane scrollPane;
    /**
     * Property "document" represent the output in the view area.
     */
    private StyledDocument document;
    /**
     * Property "commands" represent the menu of the program.
     */
    private Menu menu = new Menu(this);

    /**
     * Empty constructor for creating the console.
     */
    public Console() {
        frame=new JFrame("Console");
        console = new JTextPane();
        console.setFont(new Font("Serif", Font.PLAIN, 21));
        console.setSelectionColor(Color.white);
        console.setForeground(Color.green);
        console.setOpaque(false);
        console.setEditable(false);
        scrollPane=new JScrollPane(console);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);

        document=console.getStyledDocument();

        input=new JTextField();
        input.setFont(new Font("Serif",Font.ITALIC,18));
        input.setSelectionColor(Color.white);
        input.setForeground(Color.green);
        input.setSelectedTextColor(Color.black);
        input.setCaretColor(Color.green);
        input.setOpaque(false);
        input.setBorder(null);

        frame.getContentPane().setBackground(new Color(50,50,50));
        frame.add(input,BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setSize(700,550);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        input.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    document.remove(0, document.getLength());
                    String text=input.getText();
                    menu.setText(text);
                    menu.menu();
                    input.selectAll();
                } catch (IOException | BadLocationException ex) {
                    ex.printStackTrace();
                }
            }
        });

        input.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }
            @Override
            public void keyPressed(KeyEvent e) {
            }
            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }

    /**
     * Print the text out on the console.
     * @param text stores the printed text.
     */
    public void print(String text) throws BadLocationException {
        Style style=console.addStyle("Style",null);
        StyleConstants.setForeground(style,Color.green);
        document.insertString(document.getLength(),text+"\n",style);
    }
}