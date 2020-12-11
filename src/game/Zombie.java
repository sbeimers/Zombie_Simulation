package game;

import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.Random;

public class Zombie extends Person {
    private int decayTimer;
    private int decayMaxTimer;
    private int sleepTimer;
    private int sleepMaxTimer;

    public Circle circle;

    static int defaultDecayTimer = 0;
    static int defaultDecayMaxTimer = 500;
    static int defaultSleepTimer = 0;
    static int defaultSleepMaxTimer = 5;
    static int infectionChance = 80;
    static int defaultradius = 5;
    static int defaultX = 100;
    static int defaultY = 100;


    public String toString(){
        return String.format("Zombie at x:%d and y:%d with decay of %d/%d and sleep of %d/%d.", getxPos(), getyPos(), decayTimer, decayMaxTimer, sleepTimer, sleepMaxTimer);
    }


    public Zombie(int xPos, int yPos, int decayTimer, int decayMaxTimer, int sleepTimer, int sleepMaxTimer, int moveSpeed, int wanderTime) {
        this.setxPos(xPos);
        this.setyPos(yPos);
        this.decayTimer = decayTimer;
        this.decayMaxTimer = decayMaxTimer;
        this.sleepTimer = sleepTimer;
        this.sleepMaxTimer = sleepMaxTimer;
        this.setMoveSpeed(moveSpeed);
        this.setWanderTime(wanderTime);
        this.setAlive(true);
    }

    public Zombie(int xPos, int yPos) {
        this(xPos, yPos, defaultDecayTimer, defaultDecayMaxTimer, defaultSleepTimer, defaultSleepMaxTimer, 3, 5);
    }

    public Zombie() {
        this.setxPos((int)((Main.maxX - Main.minX) / 2));
        this.setyPos((int)((Main.maxY - Main.minY) / 2));
        this.decayTimer = defaultDecayTimer;
        this.decayMaxTimer = defaultDecayMaxTimer;
        this.sleepTimer = defaultSleepTimer;
        this.sleepMaxTimer = defaultSleepMaxTimer;
        this.setMoveSpeed(3);
        this.setWanderTime(5);
        this.setAlive(true);
    }

    public int getDecayTimer() {
        return decayTimer;
    }

    public int getDecayMaxTimer() {
        return decayMaxTimer;
    }

    public int getSleepTimer() {
        return sleepTimer;
    }

    public int getSleepMaxTimer() {
        return sleepMaxTimer;
    }

    public Zombie findClosestPerson(ArrayList<Person> zombies) {
        Zombie closest = new Zombie();
        return closest;
    }

    public boolean chase(Person closest_target){
        int ms = this.getMoveSpeed();
        //System.out.println("NOT IN MS LOOP");
        //System.out.println("ms" + ms);
        for (int i = 0; i < ms; i++){
            //System.out.println("IN MS LOOP");
            int direction = this.chooseDirection(closest_target);
            System.out.println("direction: " + direction);
            if (direction == 1 && this.getxPos() < Main.maxX){
                this.moveRight();
            }
            else if (direction == -1 && this.getxPos() > Main.minX){
                this.moveLeft();
            }
            else if (direction == 2 && this.getyPos() < Main.maxY){
                this.moveDown();
            }
            else if (direction == -2 && this.getxPos() > Main.minY){
                this.moveUp();
            }else {
                System.out.println("hi");
                if(killByZombie(closest_target)){
                    return true;
                }
                break;
            }
        }

        this.decayTimer++;

        return false;

    }

    public boolean killByZombie(Person closest_target){
        closest_target.setAlive(false);
        Random rand = new Random();
        int x = rand.nextInt(100);
        System.out.println(x);
        if (x <= infectionChance){
            return true;
            //zombies.add(zombie)
            //Zombie zombie = new Zombie(closest_target.getxPos(), closest_target.getyPos());
        }
        return false;


    }



}
