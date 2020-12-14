package game;

import java.util.ArrayList;
import java.util.Random;

public abstract class Person {

    private int xPos;
    private int yPos;
    private int moveSpeed;
    private int wanderTime;
    private boolean alive;


    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public void setMoveSpeed(int moveSpeed) {
        this.moveSpeed = moveSpeed;
    }

    public void setWanderTime(int wanderTime) {
        this.wanderTime = wanderTime;
    }

    public void die(){
        this.alive = false;
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public int getMoveSpeed() {
        return moveSpeed;
    }

    public int getWanderTime() { return wanderTime; }

    public boolean isAlive() { return alive; }

    public void setAlive(boolean alive) { this.alive = alive; }

    /**
     * Finds the distance between two Person objects
     * @param person the person being compared to
     * @return the difference in distance between the two objects.
     */
    public int findDistance(Person person){
        int myX = this.getxPos();
        int myY = this.getyPos();
        int x = person.getxPos();
        int y = person.getyPos();

        int xdiff = Math.abs(myX - x);
        int ydiff = Math.abs(myY - y);

        return xdiff + ydiff;
    }

    /**
     * Finds the closest Person from an list of People
     * @param list the list to compare
     * @return
     */
    public Person findClosest(ArrayList<Person> list){
        int shortest = this.findDistance(list.get(0));

        Person final_person = list.get(0);

        for (Person person: list){

            int dist = this.findDistance(person);

            if (dist < shortest){
                shortest = dist;
                final_person = person;
            }


        }
        return final_person;

    }


    public void moveUp() {
        this.yPos -= 1;
    }
    public void moveDown() {
        this.yPos += 1;
    }
    public void moveLeft() {
        this.xPos -= 1;
    }
    public void moveRight() {
        this.xPos += 1;
    }

    /**
     * Finds the direction for the person to go assuming the person is going towards the closest target arguement
     * @param closest_target is the closest person to the person calling the funciton
     * @return the direction the person should go to get closer to closest_target in the form of an int.
     */
    public int chooseDirection(Person closest_target){

        boolean directions[] = {true, true, true, true};

        Random rand = new Random();

        boolean up = true;    //0 1  return value: -2
        boolean right = true; //1 2  return value:  1
        boolean down = true;  //2 3  return value:  2
        boolean left = true;  //3 4  return value: -1

        int xdiff = this.getxPos() - closest_target.getxPos();
        int ydiff = this.getyPos() - closest_target.getyPos();

        if (xdiff == 0 && ydiff == 0){
            return 3;
        }

        if (xdiff == 0 && ydiff > 0 && this.getyPos() > Main.minY){
            return -2;
        }
        if (xdiff == 0 && ydiff > 0 && this.getyPos() >= Main.minY){
            int x = rand.nextInt(100);
            if (x % 2 == 0){
                return 1;
            }else{
                return -1;
            }
        }

        if (xdiff == 0 && ydiff < 0 && this.getyPos() < Main.maxY){
            return 2;
        }
        if (xdiff == 0 && ydiff < 0 && this.getyPos() >= Main.maxY){
            int x = rand.nextInt(100);
            if (x % 2 == 0){
                return 1;
            }else{
                return -1;
            }
        }

        if (ydiff == 0 && xdiff > 0 && this.getxPos() > Main.minX){
            return -1;
        }
        if (ydiff == 0 && xdiff > 0 && this.getxPos() >= Main.minX){
            int x = rand.nextInt(100);
            if (x % 2 == 0){
                return 2;
            }else{
                return -2;
            }
        }

        if (ydiff == 0 && xdiff < 0 && this.getxPos() > Main.minX){
            return 1;
        }
        if (ydiff == 0 && xdiff < 0 && this.getxPos() >= Main.minX){

            int x = rand.nextInt(100);
            if (x % 2 == 0){
                return 2;
            }else{
                return -2;
            }
        }

        if (xdiff > 0){
            //right = false;
            directions[1] = false;
        }else {
            //left = false;
            directions[3] = false;
        }
        if (ydiff > 0){
            //down = false;
            directions[2] = false;
        }else{
            //up = false;
            directions[0] = false;
        }

        if (Math.abs(xdiff) > Math.abs(ydiff)){
            //up = false;
            directions[0] = false;
            //down = false;
            directions[2] = false;
        }else{
            //right = false;
            directions[1] = false;
            //left = false;
            directions[3] = false;
        }

        if (directions[0]){
            return -2;
        }
        else if (directions[1]){
            return 1;
        }
        else if (directions[2]){
            return 2;
        }
        else if (directions[3]){
            return -1;
        }

        return 3;

    }

    /***
     * Chooses second direction (only used by civilians pinned against walls). Assumes civilian is next to wall, which causes this function to run.
     * @param closest_target is closest target to person calling function
     * @return direction of closest target in the form of an int.
     */
    public int chooseSecondDirectionRun(Person closest_target){

        boolean directions[] = {true, true, true, true};

        boolean up = true;    //0 1  -2
        boolean right = true; //1 2   1
        boolean down = true;  //2 3   2
        boolean left = true;  //3 4  -1

        int xdiff = this.getxPos() - closest_target.getxPos();
        int ydiff = this.getyPos() - closest_target.getyPos();

        if (xdiff >= 0){
            //left = false;
            directions[3] = false;
        }else {
            //right= false;
            directions[1] = false;
        }
        if (ydiff >= 0){
            //up = false;
            directions[0] = false;
        }else{
            //down = false;
            directions[2] = false;
        }

        if (Math.abs(xdiff) < Math.abs(ydiff)){
            //up = false;
            directions[0] = false;
            //down = false;
            directions[2] = false;
        }else{
            //right = false;
            directions[1] = false;
            //left = false;
            directions[3] = false;
        }

        if (directions[0]){
            return -2;
        }
        else if (directions[1]){
            return 1;
        }
        else if (directions[2]){
            return 2;
        }
        else if (directions[3]){
            return -1;
        }

        return 3;

    }




}
