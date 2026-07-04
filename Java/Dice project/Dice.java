// School project to intergrate JavaFX elements to logic of dice rolls, output images within a window

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.geometry.Insets;  //Used with setPadding
import javafx.geometry.Pos;   //Allows center alignment 
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import java.util.Random;



//Dice roll 

public class Dice extends Application
{
   private int pos = 1;
   
   public static void main(String[] args)
   {
      // Launch the application.
      launch(args);
   }
   
   @Override
   public void start(Stage primaryStage)
   {
      
      
      // Create an Image components.
      Image image0 = new Image("die0.png");
      Image image1 = new Image("die1.png");
      Image image2 = new Image("die2.png");
      Image image3 = new Image("die3.png");
      Image image4 = new Image("die4.png");
      Image image5 = new Image("die5.png");
      Image image6 = new Image("die6.png");

      // Create an ImageView control.
      ImageView imageView1 = new ImageView(image0);
      ImageView imageView2 = new ImageView(image0);
      
      Button btnRoll = new Button ("Roll Dice");
      
      // Add the listener to the button.  Implemented as a Anonymous Inner class
      btnRoll.setOnAction(new EventHandler<ActionEvent>()
      {
          @Override
          public void handle(ActionEvent event) 
          {
          int ran1, ran2;
          Random ran = new Random();
          ran1 = ran.nextInt(6)+1;
          ran2 = ran.nextInt(6)+1;
             
            switch (ran1) {
               case 1:   imageView1.setImage(image1); break;
               case 2:   imageView1.setImage(image2); break;
               case 3:   imageView1.setImage(image3); break;
               case 4:   imageView1.setImage(image4); break;
               case 5:   imageView1.setImage(image5); break;
               case 6:   imageView1.setImage(image6); break;
               }
               
            switch (ran2) {
               case 1: imageView2.setImage(image1); break;
               case 2: imageView2.setImage(image2); break;
               case 3: imageView2.setImage(image3); break;
               case 4: imageView2.setImage(image4); break;
               case 5: imageView2.setImage(image5); break;
               case 6: imageView2.setImage(image6); break;
               }     
          }    
      });
      
            
      // Put the ImageView in an HBox.
      
      HBox hbox = new HBox(10, imageView1, imageView2);
      hbox.setPadding(new Insets(10));
      hbox.setAlignment(Pos.CENTER);
      hbox.setStyle("-fx-background-color:#000000;");   //set background color using HTML color format
      
      // putting hbox into vbox
      
      VBox vbox = new VBox(15, hbox, btnRoll);
      vbox.setPadding(new Insets(10));
      vbox.setAlignment(Pos.CENTER);
      
      // Create a Scene 
      Scene scene = new Scene(vbox);
      
      // Add the Scene to the Stage.
      primaryStage.setScene(scene);
      
      // Set the stage title.
      primaryStage.setTitle("Dice Simulator");
      
      // Show the window.
      primaryStage.show();
   }
}