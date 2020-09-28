

import java.awt.Color;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.Highlighter.HighlightPainter;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
public class MyAssignment extends JFrame {
    private JLabel QuickLists;
    private JPanel rootPanel;
    private JTextArea thisIsWhereTheTextArea;

    private JButton clearAllButton;
    private JTextArea txtarea2;
    private JTextField typeHereTextField;
    private int i=1;
    int counter;
    public MyAssignment() {

        String text = Main.textBoxInfo;


        add(rootPanel);
        setTitle("Assignment1");
        setSize(1080,720);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);






        clearAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // thisIsWhereTheTextArea.setText("");
               // String textBox=  thisIsWhereTheTextArea.getText();
                Main.textBoxInfo =thisIsWhereTheTextArea.getText();


                int temp = 0;
                for (Token element1 : Main.tokens) {
                    if (temp != element1.rowNum) {
                        txtarea2.append("\n");

                    }
                    if ((!element1.getLexeme().equals("-")) && (!element1.getLexeme().equals("+"))) {
                        txtarea2.append(element1.getLexeme() + " ");
                    } else {
                        txtarea2.append(element1.getLexeme() + element1.getLexeme());
                    }
                    temp = element1.rowNum;
                }

                //txtarea2.setText(textGUI);
            }
        });



    }


}

