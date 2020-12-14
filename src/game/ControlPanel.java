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

    private static int xZombies = 5;
    private static int xCivilians = 5;

    public static void addxZombies() {
        ControlPanel.xZombies++;
    }

    public static void addxCivilians() {
        ControlPanel.xCivilians++;
    }

    public static void subtractxZombies() {
        ControlPanel.xZombies--;
    }

    public static void subtractxCivilians() {
        ControlPanel.xCivilians--;
    }

    public ControlPanel() {
        super();

        JLabel infectionChance = new JLabel();

        infectionChance.setForeground(Color.black);
        infectionChance.setText("Infection: " + Zombie.getInfectionChance() + "%   | ");
        add(infectionChance);

        JLabel decay = new JLabel();
        decay.setForeground(Color.black);

        decay.setText("Decay: " + Zombie.getDefaultDecayMaxTimer()/30 + " seconds   | ");
        add(decay);

        JLabel zms = new JLabel();
        zms.setForeground(Color.black);

        zms.setText("Z ms: " + Zombie.getDefaultMoveSpeed()*30 + "p/s   | "); //   |
        add(zms);

        JLabel cms = new JLabel();
        cms.setForeground(Color.black);

        cms.setText("C ms: " + Civilian.getDefaultMoveSpeed()*30 + "p/s");
        add(cms);


        JButton QAZbutton = new JButton(); // Quick Add Zombie
        QAZbutton.setText("Add Zombie");
        QAZbutton.setBackground(Color.red);
        QAZbutton.setLocation(new Point(1000,1000));

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

        JButton QACbutton = new JButton(); // Quick Add Civilian
        QACbutton.setText("Add Civilian");
        QACbutton.setBackground(Color.green);
        QACbutton.setLocation(new Point(1000,1000));
        QACbutton.setSize(100,100);
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

        ////////////////////////////////////

        JButton SXZbutton = new JButton(); // Quick Add 5 Zombie
        SXZbutton.setText("Add 5 Zombies");
        SXZbutton.setBackground(Color.red);
        SXZbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Random rand = new Random();
                Zombies = ClientGUI.getZeds();
                for (int i = 0; i < xZombies; i++){
                    Zombie zed = new Zombie(Main.minX+rand.nextInt( Main.maxX-Main.minX), Main.minY+rand.nextInt( Main.maxY-Main.minY));
                    Zombies.add(zed);
                }
                ClientGUI.setZeds(Zombies);
            }
        });
        add(SXZbutton);

        JButton SXCbutton = new JButton(); // Quick Add 5 Civilians
        SXCbutton.setText("Add 5 Civilians");
        SXCbutton.setBackground(Color.green);
        SXCbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Random rand = new Random();
                Civilians = ClientGUI.getCivs();
                for (int i = 0; i < xCivilians; i++){
                    Civilian civ = new Civilian(Main.minX+rand.nextInt( Main.maxX-Main.minX), Main.minY+rand.nextInt( Main.maxY-Main.minY));
                    Civilians.add(civ);

                }
                ClientGUI.setCivs(Civilians);
            }
        });
        add(SXCbutton);

        JButton infectionbutton = new JButton(); // Quick add 5% to infection
        infectionbutton.setText("Infection+");
        infectionbutton.setBackground(Color.cyan);
        infectionbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Zombie.increaseInfectionChance();
                infectionChance.setText("Infection: " + Zombie.getInfectionChance() + "%   | ");
            }
        });
        add(infectionbutton);

        JButton minusinfectionbutton = new JButton(); // Quick subtract 5% to infection
        minusinfectionbutton.setText("Infection-");
        minusinfectionbutton.setBackground(Color.cyan);
        minusinfectionbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Zombie.decreaseInfectionChance();
                infectionChance.setText("Infection: " + Zombie.getInfectionChance() + "%   | ");
            }
        });
        add(minusinfectionbutton);

        JButton decaybutton = new JButton(); // Quick add 1 second to max decay timer
        decaybutton.setText("Decay+");
        decaybutton.setBackground(Color.orange);
        decaybutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Zombie.increaseDecayMax();
                decay.setText("Decay: " + Zombie.getDefaultDecayMaxTimer()/30 + " seconds   | ");
            }
        });
        add(decaybutton);

        JButton minusdecaybutton = new JButton(); // Quick subtract 1 second to max decay timer
        minusdecaybutton.setText("Decay-");
        minusdecaybutton.setBackground(Color.orange);
        minusdecaybutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Zombie.getDefaultDecayMaxTimer() > 31) {
                    Zombie.decreaseDecayMax();
                    decay.setText("Decay: " + Zombie.getDefaultDecayMaxTimer() / 30 + " seconds   | ");
                }
            }
        });
        add(minusdecaybutton);

        ////////////////////

        JButton zmsbutton = new JButton(); // Quick add 30 pixels/section to movespeed of zombies
        zmsbutton.setText("Z ms+");
        zmsbutton.setBackground(Color.red);
        zmsbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Zombie.setDefaultMoveSpeed(Zombie.getDefaultMoveSpeed() + 1);
                ArrayList<Zombie> zeds = ClientGUI.getZeds();
                for(Zombie zed: zeds){
                    zed.setMoveSpeed(Zombie.getDefaultMoveSpeed());
                }
                ClientGUI.setZeds(zeds);
                zms.setText("Z ms: " + Zombie.getDefaultMoveSpeed()*30 + "p/s   | ");

            }
        });
        add(zmsbutton);

        JButton minuszmsbutton = new JButton(); // Quick subtract 30 pixels/section to movespeed of zombies
        minuszmsbutton.setText("Z ms-");
        minuszmsbutton.setBackground(Color.red);
        minuszmsbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Zombie.getDefaultMoveSpeed() > 1){
                    Zombie.setDefaultMoveSpeed(Zombie.getDefaultMoveSpeed() - 1);
                    ArrayList<Zombie> zeds = ClientGUI.getZeds();
                    for(Zombie zed: zeds){
                        zed.setMoveSpeed(Zombie.getDefaultMoveSpeed());
                    }
                    ClientGUI.setZeds(zeds);
                    zms.setText("Z ms: " + Zombie.getDefaultMoveSpeed()*30 + "p/s   | ");
                }
            }
        });
        add(minuszmsbutton);

        ////////////////////////////////////////////////asdf////

        JButton cmsbutton = new JButton(); // Quick add 30 pixels/section to movespeed of civilians
        cmsbutton.setText("C ms+");
        cmsbutton.setBackground(Color.green);
        cmsbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Civilian.setDefaultMoveSpeed(Civilian.getDefaultMoveSpeed() + 1);
                ArrayList<Civilian> civs = ClientGUI.getCivs();
                for(Civilian civ: civs){
                    civ.setMoveSpeed(Zombie.getDefaultMoveSpeed());
                }
                ClientGUI.setCivs(civs);
                cms.setText("C ms: " + Civilian.getDefaultMoveSpeed()*30 + "p/s");

            }
        });
        add(cmsbutton);

        JButton minuscmsbutton = new JButton(); // Quick subtract 30 pixels/section to movespeed of civilians
        minuscmsbutton.setText("C ms-");
        minuscmsbutton.setBackground(Color.green);
        minuscmsbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Civilian.getDefaultMoveSpeed() > 1){
                    Civilian.setDefaultMoveSpeed(Civilian.getDefaultMoveSpeed() - 1);
                    ArrayList<Civilian> civs = ClientGUI.getCivs();
                    for(Civilian civ: civs){
                        civ.setMoveSpeed(Zombie.getDefaultMoveSpeed());
                    }
                    ClientGUI.setCivs(civs);
                    cms.setText("C ms: " + Civilian.getDefaultMoveSpeed()*30 + "p/s");
                }
            }
        });
        add(minuscmsbutton);



    }
    public void paintComponent(Graphics g) { super.paintComponent(g); }
}