package game;

import javax.swing.*;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;

public class Main{

    public static int maxX = 445;
    public static int maxY = 445;
    public static int minX = 5;
    public static int minY = 5;

    /**
     * The main function of the program that loops 30 times per second and moves the people around the grid
     * @param civies is the list of civilians
     * @param zombs is the list of zombies
     * @param dead is the list of dead people
     * @return false if either there are no zombies or no civilians alive, and true otherwise
     */
    public static boolean tick(ArrayList<Civilian> civies, ArrayList<Zombie> zombs, ArrayList<Person> dead){

        // goes through and removes all dead civilians from arraylist
        Iterator<Civilian> iCivy = civies.iterator();
        while (iCivy.hasNext()){
            Civilian person = iCivy.next();
            if (person.isAlive() == false){
                dead.add(person);
                iCivy.remove();
            }

        }

        // goes through and removes all decayed zombies from arraylist
        Iterator<Zombie> iZombie = zombs.iterator();
        while (iZombie.hasNext()){
            Zombie zombie = iZombie.next();
            if (zombie.getDecayTimer() >= zombie.getDecayMaxTimer()){
                dead.add(zombie);
                iZombie.remove();
            }

        }

        // will catch if there is only zombies or civilians and essentially pause the simulation.
        if (civies.size() == 0 || zombs.size() == 0){
            return false;
        }

        ArrayList<Person> civilians = new ArrayList<Person>();
        civilians.addAll(civies);
        ArrayList<Person> zombies = new ArrayList<Person>();
        zombies.addAll(zombs);

        int lenZombies = zombs.size();
        int lenCivilians = civilians.size();

        // goes through all zombies and uses the chase function. Then possibly spawns another zombie based on infection chance
        for (int i = 0; i < lenZombies; i++){
            Person target = zombs.get(i).findClosest(civilians);
            zombs.get(i).decayByOne();
            if (zombs.get(i).chase(target)){
                zombs.get(i).findClosest(civilians).die();
                if (zombs.size() < 100){
                    Zombie zed = new Zombie(zombs.get(i).getxPos(), zombs.get(i).getyPos());
                    zombs.add(zed);
                }

            }

        }

        // goes through all civilians and runs from the closest zombie.
        for (int i = 0; i < lenCivilians; i++){
            Person target = civilians.get(i).findClosest(zombies);
            civies.get(i).run(target);
        }

        return true;

    }


}
