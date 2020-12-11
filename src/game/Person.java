package game;

import java.util.ArrayList;
import java.util.Random;

public class Person {

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




    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public int getMoveSpeed() {
        return moveSpeed;
    }

    public int getWanderTime() {
        return wanderTime;
    }



    public boolean isAlive() { return alive; }

    public void setAlive(boolean alive) { this.alive = alive; }

    public int findDistance(Person person){
        int myX = this.getxPos();
        int myY = this.getyPos();
        int x = person.getxPos();
        int y = person.getyPos();

        int xdiff = Math.abs(myX - x);
        int ydiff = Math.abs(myY - y);

        return xdiff + ydiff;
    }


    public Person findClosest(ArrayList<Person> list){
        int shortest = this.findDistance(list.get(0));

        Person final_person = list.get(0);

        for (Person person: list){
            //findDistance(person);
            int myX = this.getxPos();
            int myY = this.getyPos();
            int x = person.getxPos();
            int y = person.getyPos();

            int xdiff = Math.abs(myX - x);
            int ydiff = Math.abs(myY - y);

            int dist = xdiff + ydiff;

            if (dist < shortest){
                shortest = dist;
                final_person = person;
            }

            //System.out.println("list: " + list);
            //System.out.println("person: " + person);
            //final_person = person;


        }
        System.out.println("closests Target for " + this + " is " + final_person);
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

    public int chooseDirection(Person closest_target){

        boolean directions[] = {true, true, true, true};

        Random rand = new Random();

        boolean up = true;    //0 1  -2
        boolean right = true; //1 2   1
        boolean down = true;  //2 3   2
        boolean left = true;  //3 4  -1

        //int xdiff = zombie.getxPos() - closest_target.getxPos();
        //int ydiff = zombie.getyPos() - closest_target.getyPos();
        int xdiff = this.getxPos() - closest_target.getxPos();
        int ydiff = this.getyPos() - closest_target.getyPos();

        System.out.println("xdiff = " + xdiff);
        System.out.println("ydiff = " + ydiff);

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

        //System.out.println(right);
        //System.out.println(left);
        //System.out.println(up);
        //System.out.println(down);
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

        /*for (int i = 0; i < 4; i++){
            //System.out.println(i);
            System.out.println(directions[i]);
            if (directions[i]){
                System.out.println(i);
                return i;
            }*/

        return 3;

    }

    /***
     * Chooses second direction (only used by civilians pinned against walls)
     * @param closest_target is closest target
     * @return direction in the form of an int.
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
