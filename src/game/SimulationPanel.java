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

        for(Person d: dead ){
            g.setColor(Color.orange);
            // ((d.getxPos()-5)*2)*getBounds().width*((d.getxPos()-5)*2),((d.getyPos()-5)*2)*getBounds().width*(d.getyPos()-5)*2,
            // g.drawRect((d.getxPos()-5)*2,(d.getyPos()-5)*2,10,10);
            g.drawRect(((d.getxPos()-5)*((getBounds().width)/450)),(d.getyPos()-5)*(((getBounds().height)/450)),10,10);
            // ((d.getxPos()-5)*2)*getBounds().width*((d.getxPos()-5)*2),((d.getyPos()-5)*2)*getBounds().width*(d.getyPos()-5)*2,
        }

        for(Zombie zombie: Zombies){
            if (zombie.sleepTimer < zombie.sleepMaxTimer){
                g.setColor(Color.black);

                //g.drawRect((((zombie.getxPos()-5))*(getBounds().width*((zombie.getxPos()-5)*2))),((zombie.getyPos()-5))*getBounds().height*(zombie.getyPos()-5),10,10);
                //g.drawRect((((zombie.getxPos()-5))*(450/(getBounds(SimulationPanel).width))),((zombie.getyPos()-5))*(450/(getBounds().height)),10,10);
                g.drawRect((((zombie.getxPos()-5))*((getBounds().width)/450)),((zombie.getyPos()-5))*(((getBounds().height)/450)),10,10);
            }else{
                g.setColor(Color.red);
                g.drawRect((((zombie.getxPos()-5))*((getBounds().width)/450)),((zombie.getyPos()-5))*(((getBounds().height)/450)),10,10);
            }

        }
        for(Civilian civ: Civilians){
            g.setColor(Color.green);
            g.drawRect((((civ.getxPos()-5))*((getBounds().width)/450)),((civ.getyPos()-5))*(((getBounds().height)/450)),10,10);
        }



    }

}