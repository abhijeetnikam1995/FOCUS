/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

 import javafx.application.Application;import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
 import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
 import javafx.stage.Stage;


/**
 *
 * @author rakesh
 */
public class JavaFXApplication1 extends Application {
    
    @Override
    public void start(Stage stage) {
        
      TextArea keywords = new TextArea();
      Button crawl = new Button("Search");
      TextArea progress_text=new TextArea();
      Label label = new Label("Enter Keywords");

      
      ProgressIndicator progress=new ProgressIndicator();
      //Instantiating the HBox class
      
      progress_text.setPrefColumnCount(50);    //Width
      progress_text.setPrefRowCount(50);       //Height

      keywords.setPrefColumnCount(8);    //Width
      keywords.setPrefRowCount(5);       //Height

      
      HBox horizontal_layout = new HBox();
      VBox vertical_layout = new VBox();

      vertical_layout.setAlignment(Pos.CENTER); // To align nodes (Button,SearchBox,ProgressIndicator) in center
     
      vertical_layout.setSpacing(30);
      
      horizontal_layout.setMargin(vertical_layout, new Insets(20, 20, 20, 20));
      horizontal_layout.setMargin(progress_text, new Insets(20, 20, 20, 20));
      
      ObservableList list_v=vertical_layout.getChildren();
      list_v.addAll(label,keywords,crawl,progress);
      
      ObservableList list_h = horizontal_layout.getChildren();
      list_h.addAll(vertical_layout,progress_text);
      
      Scene scene = new Scene(horizontal_layout,1000,1000);
      
      
      
      //Setting title to the Stage
      stage.setTitle("Main Window");
      //Adding scene to the satge
      stage.setScene(scene);
      //Displaying the contents of the stage
      stage.show();


    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch();
    }
    
}
