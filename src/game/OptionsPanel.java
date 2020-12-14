package game;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

@SuppressWarnings("serial")
public class OptionsPanel extends JPanel {

    private ArrayList<Civilian> Civilians;
    private ArrayList<Zombie> Zombies;
    private ArrayList<Person> dead;


    public OptionsPanel() {
        super();


        JButton CSbutton = new JButton(); // Reset all Button
        CSbutton.setText("Clear Simulation");
        CSbutton.setBackground(Color.red);
        CSbutton.setPreferredSize(new Dimension(130,60));
        CSbutton.setBounds(10,300,130,60);
        CSbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ArrayList<Zombie> emptyZeds = new ArrayList<Zombie>();
                ClientGUI.setZeds(emptyZeds);
                ArrayList<Civilian> emptyCivs = new ArrayList<Civilian>();
                ClientGUI.setCivs(emptyCivs);
                ArrayList<Person> emptyDead = new ArrayList<Person>();
                ClientGUI.setDead(emptyDead);

            }
        });
        add(CSbutton);

    }
    public void paintComponent(Graphics g) { super.paintComponent(g); }
}