package game;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

@SuppressWarnings("serial")
public class ControlPanel extends JPanel {

    private ArrayList<Civilian> Civilians;
    private ArrayList<Zombie> Zombies;

    public ControlPanel() {
        super();


        JButton QAZbutton = new JButton(); // Quick Add Zombie
        QAZbutton.setText("Add Zombie");
        QAZbutton.setBackground(Color.red);


        QAZbutton.setSize(100,100);
        QAZbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Random rand = new Random();
                Zombies = ClientGUI.getZeds();
                Zombie zed = new Zombie(Main.minX+rand.nextInt( Main.maxX-Main.minX), Main.minY+rand.nextInt( Main.maxY-Main.minY));
                Zombies.add(zed);
                ClientGUI.setZeds(Zombies);
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
                Random rand = new Random();
                Civilians = ClientGUI.getCivs();
                Civilian civ = new Civilian(Main.minX+rand.nextInt( Main.maxX-Main.minX), Main.minY+rand.nextInt( Main.maxY-Main.minY));
                Civilians.add(civ);
                ClientGUI.setCivs(Civilians);
            }
        });
        add(QACbutton);


    }
    public void paintComponent(Graphics g) { super.paintComponent(g); }
}