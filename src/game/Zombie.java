package game;

import java.util.ArrayList;
import java.util.Random;

public class Zombie extends Person {
    int decayTimer;
    int decayMaxTimer;
    int sleepTimer;
    int sleepMaxTimer;

    static int defaultDecayTimer = 0;
    static int defaultDecayMaxTimer = 180;
    static int defaultSleepTimer = 0;
    static int defaultSleepMaxTimer = 20;
    static int infectionChance = 75;
    static int defaultMoveSpeed = 3;
    static int defaultWanderTime = 5;

    public static void setDefaultMoveSpeed(int defaultMoveSpeed) {
        Zombie.defaultMoveSpeed = defaultMoveSpeed;
    }

    public static int getDefaultMoveSpeed() {
        return defaultMoveSpeed;
    }

    public static int getInfectionChance() {
        return infectionChance;
    }

    public static void increaseInfectionChance(){
        if (infectionChance <= 95){
            infectionChance += 5;
        }
    }

    public static void decreaseInfectionChance(){
        if (infectionChance >= 5){
            infectionChance -= 5;
        }

    }

    public static void decreaseDecayMax(){
        if (defaultDecayMaxTimer >= 31){
            defaultDecayMaxTimer -= 30;
        }

    }

    public static void increaseDecayMax(){
        defaultDecayMaxTimer += 30;
    }


    public static int getDefaultDecayMaxTimer() {
        return defaultDecayMaxTimer;
    }

    public void setDecayMaxTimer(int decayMaxTimer) {
        this.decayMaxTimer = decayMaxTimer;
    }

    public static void setInfectionChance(int infectionChance) {
        Zombie.infectionChance = infectionChance;
    }

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
        this(xPos, yPos, defaultDecayTimer, defaultDecayMaxTimer, defaultSleepTimer, defaultSleepMaxTimer, defaultMoveSpeed, defaultWanderTime);
    }

    public Zombie() {
        this.setxPos((int)((Main.maxX - Main.minX) / 2));
        this.setyPos((int)((Main.maxY - Main.minY) / 2));
        this.decayTimer = defaultDecayTimer;
        this.decayMaxTimer = defaultDecayMaxTimer;
        this.sleepTimer = defaultSleepTimer;
        this.sleepMaxTimer = defaultSleepMaxTimer;
        this.setMoveSpeed(defaultMoveSpeed);
        this.setWanderTime(defaultWanderTime);
        this.setAlive(true);
    }

    public int getDecayTimer() {
        return decayTimer;
    }

    public void decayByOne() {
        this.decayTimer++;
    }

    public void sleepTimerAddOne() {
        this.sleepTimer++;
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

    /**
     * Moves A zombie towards the closest target a distance based on their movespeed
     * @param closest_target is the target that the zombie chases
     * @return true if it stopped prematurely and killed a civilian
     */
    public boolean chase(Person closest_target){
        if (this.getSleepTimer() < this.getSleepMaxTimer()){
            this.sleepTimerAddOne();
            return false;
        }
        int ms = this.getMoveSpeed();

        for (int i = 0; i < ms; i++){

            int direction = this.chooseDirection(closest_target);
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
                if (killByZombie(closest_target)){
                    return true;
                }

            }
        }

        return false;

    }

    /**
     * Kills the target and calculates if it should create a new zombie on the corpse
     * @param closest_target is the target that is being killed
     * @return true if the infection chance passes, and returns false if it doesn't create a new zombie
     */
    public boolean killByZombie(Person closest_target){

        if (closest_target.isAlive() == false){
            return false;
        }

        closest_target.setAlive(false);
        Random rand = new Random();
        int x = rand.nextInt(100);
        //System.out.println(x);
        if (x <= infectionChance){
            return true;
        }
        return false;


    }



}
