package game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ClientGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private int pX, pY;
    private JPanel contentPane;


    private static ArrayList<Civilian> civs;
    private static ArrayList<Zombie> zeds;
    private static ArrayList<Person> dead;

    public static ArrayList<Civilian> getCivs() {
        return civs;
    }

    public static ArrayList<Zombie> getZeds() {
        return zeds;
    }

    public ArrayList<Person> getDead() {
        return dead;
    }

    public static void setCivs(ArrayList<Civilian> inputcivs) {
        civs = inputcivs;
    }

    public static void setZeds(ArrayList<Zombie> inputzeds) {
        zeds = inputzeds;
    }

    public static void setDead(ArrayList<Person> inputdead) {
        dead = inputdead;
    }

    public void addZed(Zombie inputzed) {
        zeds.add(inputzed);
    }


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ClientGUI frame = new ClientGUI();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ClientGUI() {
        Color Grey1 = new Color (50, 50, 50);
        Color Grey2 = new Color (65, 65, 65);
        Color Grey3 = new Color (80, 80, 80);
        Color Grey4 = new Color (105, 105, 105);

        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int sc_width = gd.getDisplayMode().getWidth();
        int sc_height = gd.getDisplayMode().getHeight();
        int window_width = 1550;
        int window_height = 940;

        contentPane = new JPanel(new BorderLayout());
        contentPane.setBorder(null);
        setTitle("Zombie Simulator 2020");
        contentPane.setBackground(Grey4);
        setContentPane(contentPane);
        setResizable(true);
        setBounds((sc_width / 2) - (window_width / 2), (sc_height / 2) - (window_height / 2), window_width, window_height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        OptionsPanel optionsPanel = new OptionsPanel();
        optionsPanel.setBackground(Grey1);
        contentPane.add(optionsPanel, BorderLayout.LINE_START);

        SimulationPanel simPanel = new SimulationPanel();
        simPanel.setBackground(Grey2);
        contentPane.add(simPanel, BorderLayout.CENTER);

        ControlPanel controlPanel = new ControlPanel();
        controlPanel.setBackground(Grey3);
        contentPane.add(controlPanel, BorderLayout.SOUTH);


        ////////////////////////////////////////////////////////////////////////////

        civs = new ArrayList<Civilian>();
        zeds = new ArrayList<Zombie>();
        dead = new ArrayList<Person>();

        Random rand = new Random();

        for (int i = 0; i < 20; i++){
            int x = rand.nextInt(450);
            int y = rand.nextInt(450);
            Civilian civv = new Civilian(x,y);
            civs.add(civv);
        }
        for (int i = 0; i < 20; i++){
            int x = rand.nextInt(450);
            int y = rand.nextInt(450);
            Zombie zz = new Zombie(x,y);
            zeds.add(zz);
        }


        setSimPanel(simPanel, civs, zeds, dead);


        javax.swing.Timer t = new javax.swing.Timer(30, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (!Main.tick(civs, zeds, dead)){
                }
                setSimPanel(simPanel, civs, zeds, dead);

                simPanel.repaint();
            }
        });
        t.start();

    }

    public void setSimPanel(SimulationPanel simPanel, ArrayList<Civilian> civs, ArrayList<Zombie> zeds, ArrayList<Person> dead){
        simPanel.setCivilians(civs);
        simPanel.setZombies(zeds);
        simPanel.setDead(dead);
    }
}