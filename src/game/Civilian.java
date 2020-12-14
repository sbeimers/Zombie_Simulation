package game;

public class Civilian extends Person {

    public String toString(){
        return String.format("Civilian at x:%d and y:%d.", getxPos(), getyPos());
    }

    public Civilian(int x, int y, boolean alive, int moveSpeed, int wanderTime) {
        setxPos(x);
        setyPos(y);
        setAlive(alive);
        setMoveSpeed(moveSpeed);
        setWanderTime(wanderTime);
    }

    public Civilian(int x, int y) {
        this(x, y, defaultalive, defaultmoveSpeed, defaultwanderTime);
    }

    static int defaultxPos = 10;
    static int defaultyPos = 10;
    static int defaultmoveSpeed = 3;
    static int defaultwanderTime = 10;
    static boolean defaultalive = true;

    public static void setDefaultMoveSpeed(int defaultMoveSpeed) {
        Civilian.defaultmoveSpeed = defaultMoveSpeed;
    }

    public static int getDefaultMoveSpeed() {
        return defaultmoveSpeed;
    }

    public Civilian() {
        setxPos(defaultxPos);
        setyPos(defaultyPos);
        setAlive(defaultalive);
        setMoveSpeed(defaultmoveSpeed);
        setWanderTime(defaultwanderTime);
    }


    public void run(Person closest_target){
        int ms = this.getMoveSpeed();
        //System.out.println("NOT IN MS LOOP");
        //System.out.println("ms" + ms);
        for (int i = 0; i < ms; i++){
            //System.out.println("IN MS LOOP");
            int direction = -1 * (this.chooseDirection(closest_target));
            if (direction == -3){
                break;
            }

            //System.out.println("direction: " + direction);
            if (direction == 1 && this.getxPos() < game.Main.maxX){
                this.moveRight();
            }
            else if (direction == -1 && this.getxPos() > game.Main.minX){
                this.moveLeft();
            }
            else if (direction == 2 && this.getyPos() < game.Main.maxY){
                this.moveDown();
            }
            else if (direction == -2 && this.getyPos() > game.Main.minY){
                this.moveUp();
            }else {

                int second_direction = this.chooseSecondDirectionRun(closest_target);

                if (second_direction == 1 && this.getxPos() < game.Main.maxX){
                    this.moveRight();
                }
                else if (second_direction == -1 && this.getxPos() > game.Main.minX){
                    this.moveLeft();
                }
                else if (second_direction == 2 && this.getyPos() < game.Main.maxY){
                    this.moveDown();
                }
                else if (second_direction == -2 && this.getyPos() > game.Main.minY) {
                    this.moveUp();
                }

            }
        }


    }


}
