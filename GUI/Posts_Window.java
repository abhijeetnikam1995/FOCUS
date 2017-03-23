/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package posts_window;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author rakesh
 */

public class Posts_Window extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        TextArea posts= new TextArea();
        
        VBox vertical_layout=new VBox();
        ObservableList list_v = vertical_layout.getChildren();
        list_v.add(posts);
        
        
        posts.setPrefRowCount(700); 
        posts.setPrefColumnCount(700); 
        
        Scene scene = new Scene(vertical_layout, 1000, 1000);
        
        primaryStage.setTitle("Posts Window");
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
