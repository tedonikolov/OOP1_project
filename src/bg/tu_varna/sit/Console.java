package bg.tu_varna.sit;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class Console {
    public JFrame frame;
    public JTextPane console;
    public JTextField input;
    public JScrollPane scrollPane;
    public StyledDocument document;

    public Console() {
        frame=new JFrame("Console");
        console = new JTextPane();
        console.setFont(new Font("Courier New",Font.ITALIC,15));
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
        input.setFont(new Font("Courier New",Font.ITALIC,15));
        input.setSelectionColor(Color.white);
        input.setForeground(Color.green);
        input.setSelectedTextColor(Color.black);
        input.setCaretColor(Color.green);
        input.setOpaque(false);
        input.setBorder(null);

        frame.getContentPane().setBackground(new Color(50,50,50));
        frame.add(input,BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setSize(600,350);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        input.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileFunctions file = new FileFunctions();
                try {
                    document.remove(0, document.getLength());
                    String text=input.getText();
                    file.menu(text, Console.this);
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

    public void print(String text) throws BadLocationException {
        Style style=console.addStyle("Style",null);
        StyleConstants.setForeground(style,Color.green);
        document.insertString(document.getLength(),text+"\n",style);
    }
}