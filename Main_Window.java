/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package focus;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
 import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
 import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
 import javafx.stage.Stage;



public class Main_Window extends Application {
    
    static String keywords_text=null;

     

    @Override
    public void start(Stage stage) {

      TextArea counter = new TextArea();
      TextArea keywords = new TextArea();
      Button crawl = new Button("Start");
      TextArea progress_text=new TextArea();
      //progress_text.setStyle("-fx-background-color: transparent;");

      Label label = new Label("Enter Keyword");
      Label label_counter = new Label("Counter");
      progress_text.setEditable(false);
      
      ProgressIndicator progress=new ProgressIndicator();
      
      progress.setVisible(false);
      
      progress_text.setPrefColumnCount(50);    //Width
      progress_text.setPrefRowCount(50);       //Height

      keywords.setPrefColumnCount(8);    //Width
      keywords.setPrefRowCount(5);       //Height

      counter.setPrefColumnCount(1);    //Width
      counter.setPrefRowCount(1);       //Height

      HBox horizontal_layout = new HBox();
      VBox vertical_layout = new VBox();

      horizontal_layout.setStyle("-fx-background-color: transparent;");
      //vertical_layout.setStyle("-fx-background-color: transparent;");
      
      vertical_layout.setAlignment(Pos.CENTER); // To align nodes (Button,SearchBox,ProgressIndicator) in center
     
      vertical_layout.setSpacing(30);
      
      HBox.setMargin(vertical_layout, new Insets(20, 20, 20, 20));
      HBox.setMargin(progress_text, new Insets(20, 20, 20, 20));
      
      ObservableList list_v=vertical_layout.getChildren();
      list_v.addAll(label,keywords,crawl,progress,label_counter,counter);
      
      ObservableList list_h = horizontal_layout.getChildren();
      list_h.addAll(vertical_layout,progress_text);
      
      Scene scene = new Scene(horizontal_layout,1000,1000);
      
      Platform.setImplicitExit(true); // Closes Windows Instead Of Hinding It
      stage.setOnCloseRequest((ae) -> { // SO, Exit application along with all backgroud threads 
            Platform.exit();
            System.exit(0);
        });
      
      //scene.setFill(Color.TRANSPARENT);

      //scene.setFill(null);

      stage.setTitle("Main Window");
      stage.setScene(scene);
      stage.show();
      
      Start_Crawling startcrawl=new Start_Crawling();
      
        EventHandler<MouseEvent> eventHandler = (MouseEvent e) -> {
            keywords_text=keywords.getText();
            

            
            System.out.println(keywords_text);
            
            new Thread(
                    () -> {
                        try {
                            
                            progress.setVisible(true);      //display progressbar
                            startcrawl.start(progress_text,counter);
                            progress.setVisible(false);     //hide progressbar
                            
                            
                        } catch (IOException ex) {
                            Logger.getLogger(FOCUS.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ClassNotFoundException | SQLException ex) {
                            Logger.getLogger(Main_Window.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }).start();
      }; 
        
      crawl.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler); 

    }
    
    public void start(){
        
        launch();
        
    }

    
}
