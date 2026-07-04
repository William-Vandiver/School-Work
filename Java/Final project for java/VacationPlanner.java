// This is code to my final project for my Java course
// It was to make and interface with series of clickable buttons, checkboxes, radio buttons, and then to style it with JavaFX CSS file
// The total output to a box base on selected inputs, as a outlined full bill
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

public class VacationPlanner extends Application{

   private RadioButton place1;
   private RadioButton place2;
   private RadioButton place3;
   private RadioButton hotelType1;
   private RadioButton hotelType2;
   private RadioButton hotelType3;
   private CheckBox addOns1;
   private CheckBox addOns2; 
   private CheckBox addOns3;
   private CheckBox addOns4;
   private Label lblTotal;  

   public static void main(String[] args){
      //launch the application
      launch(args);
   }
    
    @Override
    public void start(Stage primaryStage){
    
      //add header
      Label programName = new Label("Plan Your Vacation");
      programName.getStyleClass().add("label-heading");
      VBox vboxHeader= new VBox (10, programName);
      vboxHeader.setPadding(new Insets(20));
      vboxHeader.setAlignment(Pos.CENTER);
        
      //add radiobuttons hotel type   
      Label  lblHotel = new Label("Hotel Type:");
      lblHotel.getStyleClass().add("label-minor");
      hotelType1 = new RadioButton("Standard ($500.00)");
      hotelType2 = new RadioButton("Luxury ($1000.00)"); 
      hotelType3 = new RadioButton("Boutique ($1580.00)");
      ToggleGroup gpRegistration = new ToggleGroup();
      hotelType1.setToggleGroup(gpRegistration);
      hotelType2.setToggleGroup(gpRegistration);
      hotelType3.setToggleGroup(gpRegistration); 
      hotelType1.setSelected(true);
      VBox vboxTypes = new VBox(10, lblHotel, hotelType1, hotelType2, hotelType3);
      vboxTypes.setPadding(new Insets(10));
      
      //add radiobuttons destination
      Label lblPlace = new Label("Destination");
      lblPlace.getStyleClass().add("label-minor");
       place1 = new RadioButton("Hawaii ($1200.00)");
       place2 = new RadioButton("Paris ($1400.00)"); 
       place3 = new RadioButton("Tokyo ($1800.00)");
      ToggleGroup gpRegistration2 = new ToggleGroup();
      place1.setToggleGroup(gpRegistration2);
      place2.setToggleGroup(gpRegistration2);
      place3.setToggleGroup(gpRegistration2); 
      place1.setSelected(true);
      VBox vboxPlaces = new VBox(10, lblPlace, place1, place2, place3);
      vboxPlaces.setPadding(new Insets(10));
        
      //add checkbox add ons
      Label lblAddOns = new Label("Add-ons");
      lblAddOns.getStyleClass().add("label-minor");
      addOns1 = new CheckBox("Guided Tours ($150.00)");
      addOns2 = new CheckBox("Airport Transfer ($50.00)");
      addOns3 = new CheckBox("Breakfast Included ($80.00)");
      addOns4 = new CheckBox("Travel Insurance ($100.00)");
      VBox vboxAddOns = new VBox(10, lblAddOns, addOns1, addOns2, addOns3, addOns4);
      vboxAddOns.setPadding(new Insets(10));
      
      //calculate button, clear button and text area, with button handlers  
      Button calcButton = new Button("Calculate Cost");
      Button clearButton = new Button("Clear");
      calcButton.getStyleClass().add("label-minor");
      clearButton.getStyleClass().add("label-minor");
      lblTotal = new Label("");
      lblTotal.getStyleClass().add("label-display");
      TextArea textArea = new TextArea();
      textArea.setPrefColumnCount(40);
      textArea.setPrefRowCount(40);
      textArea.setEditable(false);
      textArea.getStyleClass().add("label-display");
      calcButton.setOnAction(new EventHandler<ActionEvent>(){ // calcbutton event
         @Override
         public void handle(ActionEvent event){
            double total;
            String place, hotel, addOn1, addOn2, addOn3, addOn4, allAddOns;
            total = 0;
            place = "";
            hotel = "";
            addOn1 = "";
            addOn2 = "";
            addOn3 = "";
            addOn4 = "";
            
            // distination ifs
            if (place1.isSelected())
               {
               total = total + 1200.00;
               place = "Hawaii ($1200.00)";
               }
            if (place2.isSelected())
               {
               total = total + 1400.00;
               place = "Paris ($1400.00)";
               }
            if (place3.isSelected())
               {
               total = total + 1800.00;
               place = "Tokyo ($1800.00)";
               }
                  
            // hotel type ifs
            if (hotelType1.isSelected())
               {
               total = total + 500.00;
               hotel = "Standard ($500.00)";
               }
            if (hotelType2.isSelected())
               {
               total = total + 1000.00;
               hotel = "Luxury ($1000.00)";
               }
            if (hotelType3.isSelected())
               {
               total = total + 1580.00;
               hotel = "Boutique ($1580.00)";
               }
                      
            // add ons ifs
            if (addOns1.isSelected() || addOns2.isSelected() || addOns3.isSelected() || addOns4.isSelected()){
               if (addOns1.isSelected())
                  {
                  total = total + 150.00;
                  addOn1 = "Guided Tours ($150.00)\n";
                  allAddOns = addOn1;
                  }
               else
                  {
                  allAddOns = "";
                  }
               if (addOns2.isSelected())
                  {
                  total = total + 50.00;
                  addOn2 = "Airport Transfer ($50.00)\n";
                  allAddOns = addOn1 + addOn2;
                  }
               else
                  {
                  allAddOns = addOn1;
                  }
               if (addOns3.isSelected())
                  {
                  total = total + 80.00;
                  addOn3 = "Breakfast Included ($80.00)\n";
                  allAddOns = addOn1 + addOn2 + addOn3;
                  }
               else
                  {
                  allAddOns = addOn1 + addOn2;
                  }
               if (addOns4.isSelected())
                  {
                  total = total + 100.00;
                  addOn4 = "Travel Insurance ($100.00)\n";
                  allAddOns = addOn1 + addOn2 + addOn3 + addOn4;
                  }
               else
                  {
                  allAddOns = addOn1 + addOn2 + addOn3;
                  }
               }   
            else
               {
               allAddOns = "No add-ons selected\n";   
               }
            
                
            // textArea output            
            textArea.setText(String.format("Vaction Billing Summary\n" +
            "----------------------------------------------------------\n" + 
            "Destination: " + place + "\n" +
            "Hotel: " + hotel + "\n" +
            "Add-Ons: " + "\n" +
            allAddOns +
            "----------------------------------------------------------\n" +
            "Total Cost: $" + total));
            textArea.getStyleClass().add("label-display");
            
            // lblTotal output
            lblTotal.setText(String.format("Total Cost: $%,.2f", total));
         }
      });
      clearButton.setOnAction(new EventHandler<ActionEvent>(){  // clearbutton event
         @Override
         public void handle(ActionEvent event){
            
            // distination reset
            if (place2.isSelected())
               place1.setSelected(true);
            else
               place1.setSelected(true);
               
            // hotel reset
            if (hotelType2.isSelected())
               hotelType1.setSelected(true);
            else
               hotelType1.setSelected(true);
               
            // add ons reset
            if (addOns1.isSelected() || addOns2.isSelected() || addOns3.isSelected() || addOns4.isSelected())
               {
               addOns1.setSelected(false);
               addOns2.setSelected(false);
               addOns3.setSelected(false);
               addOns4.setSelected(false);
               }
            
            // textArea output reset            
            textArea.setText(String.format(""));
            textArea.getStyleClass().add("label-display");
            
            //lblTotal reset
            lblTotal.setText(String.format(""));
         }
      });
      
      VBox vboxFooter = new VBox(10, calcButton, clearButton, lblTotal, textArea);
      vboxFooter.setAlignment(Pos.CENTER);
            
      //put it all together
      BorderPane borderPane = new BorderPane();
      borderPane.setPadding(new Insets(10,10,10,30));
      borderPane.setTop(vboxHeader);
      borderPane.setLeft(vboxPlaces);
      borderPane.setCenter(vboxTypes);
      borderPane.setRight(vboxAddOns);
      borderPane.setBottom(vboxFooter);
      
      //create a scene and display it
      Scene scene = new Scene(borderPane, 600, 600);
      scene.getStylesheets().add("vacation.css");
      primaryStage.setTitle("Vacation Planner");
      primaryStage.setScene(scene);
      primaryStage.show();                     
    }    
}