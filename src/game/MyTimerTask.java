package game;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


public class MyTimerTask extends TimerTask {

    private ArrayList<Civilian> civs;
    private ArrayList<Zombie> zombs;
    private int check = 1;

    private Group root;
    private Scene scene;
    private Stage primaryStage;



    @Override
    public void run() {
        System.out.println("Timer task started at:"+new Date());
        //completeTask();
        //System.out.println("Timer task finished at:"+new Date());
        draw(civs, zombs);
        if (check == 1){
            if(Main.tick(civs, zombs) == false){
                check = 0;
            }
        }
    }

    MyTimerTask(ArrayList<Civilian> civs, ArrayList<Zombie> zombs, Stage primaryStage){
        this.civs = civs;
        this.zombs = zombs;
        this.root = new Group();
        this.scene = new Scene(root, Main.maxX, Main.maxY);
        this.primaryStage = primaryStage;
        primaryStage.setScene(scene);
    }

    public void draw(ArrayList<Civilian> civs, ArrayList<Zombie> zombs){
        System.out.println("DRAW CALLED" + new Date());

        primaryStage.setScene(scene);
        primaryStage.show();

        for(Zombie zed: zombs){
            if (zed.circle == null){
                zed.circle = new Circle();
                root.getChildren().add(zed.circle);
            }
            zed.circle.setRadius(5);
            zed.circle.setCenterX(zed.getxPos());
            zed.circle.setCenterY(zed.getyPos());
        }

    }

    /*private void completeTask() {
        try {
            //assuming it takes 20 secs to complete the task
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }*/

    public static void main(String args[]){
        //TimerTask timerTask = new MyTimerTask();
        //running timer task as daemon thread
        Timer timer = new Timer(true);

        //timer.scheduleAtFixedRate(timerTask, 0, 1*1000);

        System.out.println("TimerTask started");
        //cancel after sometime

        try {
            Thread.sleep(120000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        timer.cancel();
        System.out.println("TimerTask cancelled");

        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}