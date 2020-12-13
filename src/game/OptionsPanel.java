package game;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

@SuppressWarnings("serial")
public class OptionsPanel extends JPanel {
    public OptionsPanel() {
        super();

        JButton QAZbutton = new JButton(); // Quick Add Zombie
        QAZbutton.setText("Add Zombie");
        QAZbutton.setBackground(Color.red);
        QAZbutton.setSize(100,100);
        QAZbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        add(QAZbutton);

        ///////////

        JButton QACbutton = new JButton(); // Quick Add Zombie
        QACbutton.setText("Add Civilian");
        QACbutton.setBackground(Color.green);
        QACbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        add(QACbutton);
    }
    public void paintComponent(Graphics g) { super.paintComponent(g); }
}