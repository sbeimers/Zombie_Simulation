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

        for(Person d: dead ){
            g.setColor(Color.orange);
            g.drawRect(Math.round(((float)d.getxPos())*(float)(getBounds().width)/450)-5,Math.round(((float)d.getyPos())*(float)(getBounds().height)/450)-5, 10,10);
        }

        for(Zombie zombie: Zombies){
            if (zombie.sleepTimer < zombie.sleepMaxTimer){
                g.setColor(Color.black);

            }else{
                g.setColor(Color.red);
            }
            g.drawRect(Math.round(((float)zombie.getxPos())*(float)(getBounds().width)/450)-5,Math.round(((float)zombie.getyPos())*(float)(getBounds().height)/450)-5, 10,10);
        }
        for(Civilian civ: Civilians){
            g.setColor(Color.green);
            g.drawRect(Math.round(((float)civ.getxPos())*(float)(getBounds().width)/450)-5,Math.round(((float)civ.getyPos())*(float)(getBounds().height)/450)-5, 10,10);
        }



    }

}