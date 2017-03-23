/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package question_window;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author rakesh
 */
public class Question_Window extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Button nextButton = new Button("Next");
        Button prevButton = new Button("Previous");
        TextArea q1=new TextArea("Question 1");
        TextArea q2=new TextArea("Question 2");
        TextArea q3=new TextArea("Question 3");
        TextArea q4=new TextArea("Question 4");
        TextArea q5=new TextArea("Question 5");
        TextArea q6=new TextArea("Question 6");
        TextArea q7=new TextArea("Question 7");
        TextArea q8=new TextArea("Question 8");
        TextArea q9=new TextArea("Question 9");

        Button see1=new Button("See");
        Button see2=new Button("See");
        Button see3=new Button("See");
        Button see4=new Button("See");
        Button see5=new Button("See");
        Button see6=new Button("See");
        Button see7=new Button("See");
        Button see8=new Button("See");
        

        VBox vertical_layout=new VBox();
        BorderPane horizontal_layout=new BorderPane();
        
        horizontal_layout.setLeft(prevButton);
        horizontal_layout.setRight(nextButton);

        
        vertical_layout.setAlignment(Pos.CENTER); // To align nodes (Button,SearchBox,ProgressIndicator) in center
     
        vertical_layout.setSpacing(10);

        
        q1.setPrefRowCount(2);       //Height
        q2.setPrefRowCount(2);       //Height
        q3.setPrefRowCount(2);       //Height
        q4.setPrefRowCount(2);       //Height
        q5.setPrefRowCount(2);       //Height
        q6.setPrefRowCount(2);       //Height
        q7.setPrefRowCount(2);       //Height
        q8.setPrefRowCount(2);       //Height
        q9.setPrefRowCount(2);       //Height

        
 
      
        ObservableList list_v = vertical_layout.getChildren();
        list_v.addAll(see1,q1,see2,q2,see3,q3,see4,q4,see5,q5,see6,q6,see7,q7,see8,q8,horizontal_layout);
            
        
        
        
        Scene scene = new Scene(vertical_layout, 1000, 1000);
        
        primaryStage.setTitle("Questions Window");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
