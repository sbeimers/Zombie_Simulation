package game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.swing.*;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Main extends Application {

    public static int maxX = 450;
    public static int maxY = 450;
    public static int minX = 0 + 5;
    public static int minY = 0 + 5;

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        // 25:22
        /*Scene scene = new Scene(new Pane(), 1500, 900);
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(scene);
        primaryStage.show();
        */
        Pane mainPane = new Pane();

        //Create a new button
        //Requires import javafx.scene.control.Button;
        Button b = new Button("Press Me");



        //Change the text inside the button
        //Once we add the ability to interact and process events
        //this will become more useful
        b.setText("PANIC!");

        //Change text alignment in the button
        //Requires import javafx.geometry.Pos;
        b.setAlignment(Pos.CENTER);

        //Enable or disable the button
        b.setDisable(false);
        //b.setDisable(true);

        //Create a button with and style it
        Button colorButton = new Button("Test");
        //Change the style of the image button
        //This is a CSS-type string
        colorButton.setStyle("-fx-font: 22 arial; -fx-base: rgb(170,0,0); -fx-text-fill: rgb(255,255,255);");

        //Set the location and size of both buttons
        b.relocate(50,10);
        b.setPrefSize(200, 100);
        colorButton.relocate(50,150);
        colorButton.setPrefSize(200,100);

        Label title = new Label("TITLE");
        title.relocate(500,500);
        //Add the buttons to the main pane
        mainPane.getChildren().add(b);
        mainPane.getChildren().add(title);
        mainPane.getChildren().add(colorButton);

        //Create a new scene using the mainPain as the root
        //Set the scene of the main window
        Scene scene = new Scene(mainPane, 1500, 1000);
        primaryStage.setTitle("Basic Button Demo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {



        //launch(args);

        /*
        Zombie zombie1 = new Zombie();
        Zombie zombie2 = new Zombie();

        zombie1.setxPos(100);
        zombie1.setyPos(100);
        zombie2.setxPos(105);
        zombie2.setyPos(90);

        int x = zombie1.chooseDirection(zombie2);
        System.out.println("x = " + x);

        ArrayList<Person> zombies = new ArrayList<Person>();
*/
        /*Zombie zombie01 = new Zombie();
        zombies.add(zombie01);
        Zombie zombie02 = new Zombie();
        zombies.add(zombie02);
        Zombie zombie03 = new Zombie();
        zombies.add(zombie03);
*/
        /*
        zombies.add(zombie1);
        zombies.add(zombie2);

        System.out.println("zombie1: " + zombie1 + " " + zombie1.getxPos() + " " + zombie1.getyPos());
        System.out.println("zombie2: " + zombie2 + " " + zombie2.getxPos() + " " + zombie2.getyPos());


        Zombie zombie01 = new Zombie();


        Person target2 = zombie01.findClosestPerson(zombies);
        System.out.println("target2: " + target2 + " " + target2.getxPos() + " " + target2.getyPos());

        System.out.println(zombie1.findClosest(zombies));
        System.out.println(zombie2.findClosest(zombies));
        //Person target = zombie1.findClosest(zombies);
        //System.out.println("target: " + target + target.getyPos() + target.getyPos());

        Civilian civy = new Civilian();
        Civilian civy2 = new Civilian(80, 100);

        System.out.println(civy);
        System.out.println(civy2);
        System.out.println("--------------");

        for (int j = 0; j < 12; j++){
            zombie1.chase(civy2);
            System.out.println(zombie1);
            System.out.println(civy2);
        }

         */

        ArrayList<Civilian> civs = new ArrayList<Civilian>();
        ArrayList<Zombie> zeds = new ArrayList<Zombie>();
        ArrayList<Person> dead = new ArrayList<Person>();

        Civilian c1 = new Civilian(0,100);
        //Civilian c2 = new Civilian(80, 100);
        //Civilian c3 = new Civilian(130, 100);

        civs.add(c1);
        //civs.add(c2);
        //civs.add(c3);


        Zombie zed1 = new Zombie(20,100);
        //Zombie zed2 = new Zombie(200,200);
        //Zombie zed3 = new Zombie(32,30);

        zeds.add(zed1);
        //zeds.add(zed2);
        //zeds.add(zed3);

        System.out.println("civies: " + civs);
        System.out.println("zombs: " + zeds);

        /*for (int i = 0; i < 1; i++){
            if (!tick(civs, zeds)){
                break;
            }
        }*/
        int count = 0;
        while(true){
            count++;
            if (!tick(civs, zeds, dead)){
                break;
            }
        }
        System.out.println(count);
        System.out.println("done");


    }

    public static boolean tick(ArrayList<Civilian> civies, ArrayList<Zombie> zombs, ArrayList<Person> dead){
        System.out.println("civies: " + civies);
        System.out.println("zombs: " + zombs);

        Iterator<Civilian> iCivy = civies.iterator();
        while (iCivy.hasNext()){
            Civilian person = iCivy.next();
            if (person.isAlive() == false){
                dead.add(person);
                iCivy.remove();
            }

        }
        Iterator<Zombie> iZombie = zombs.iterator();
        while (iZombie.hasNext()){
            Zombie zombie = iZombie.next();
            if (zombie.getDecayTimer() >= zombie.getDecayMaxTimer()){
                dead.add((Person)zombie);
                iZombie.remove();
            }

        }

        if (civies.size() == 0 || zombs.size() == 0){
            return false;
        }


        ArrayList<Person> civilians = new ArrayList<Person>();
        civilians.addAll(civies);
        ArrayList<Person> zombies = new ArrayList<Person>();
        zombies.addAll(zombs);

        int lenZombies = zombs.size();
        int lenCivilians = civilians.size();

        System.out.println(civilians);



        for (int i = 0; i < lenZombies; i++){
            Person target = zombs.get(i).findClosest(civilians);
            zombs.get(i).decayByOne();
            if (zombs.get(i).chase(target)){
                zombs.get(i).findClosest(civilians).die();
                Zombie zed = new Zombie(zombs.get(i).getxPos(), zombs.get(i).getyPos());
                zombs.add(zed);
            }
            System.out.println("done chase");
            System.out.println("new stats:");
            System.out.println(zombs.get(i));
        }
        System.out.println();
        System.out.println();

        for (int i = 0; i < lenZombies; i++){
            System.out.println(zombs.get(i));
        }


        for (int i = 0; i < lenCivilians; i++){
            Person target = civilians.get(i).findClosest(zombies);
            civies.get(i).run(target);
            System.out.println("done run");
            System.out.println("new stats:");
            System.out.println(civies.get(i));
        }



        return true;

    }


}














