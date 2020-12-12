package game;
import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class SimulationPanel extends JPanel {

    private ArrayList<Civilian> Civilians;
    private ArrayList<Zombie> Zombies;
    private ArrayList<Person> dead;

    public void setCivilians(ArrayList<Civilian> civilians) {
        Civilians = civilians;
    }

    public void setZombies(ArrayList<Zombie> zombies) {
        Zombies = zombies;
    }

    public void setDead(ArrayList<Person> dead) {
        this.dead = dead;
    }

    public SimulationPanel() {
        super();


    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //g.setColor(Color.red);
        //g.drawRect(100,100,10,10);

        for(Zombie zombie: Zombies){
            g.setColor(Color.red);
            g.drawRect(zombie.getxPos()-5,zombie.getyPos()-5,10,10);
        }
        for(Civilian civ: Civilians){
            g.setColor(Color.green);
            g.drawRect(civ.getxPos()-5,civ.getyPos()-5,10,10);
        }
        for(Person d: dead ){
            g.setColor(Color.orange);
            g.drawRect(d.getxPos()-5,d.getyPos()-5,10,10);
        }


    }

}