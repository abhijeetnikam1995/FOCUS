/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package focus;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    int counter;    //Assign counter from  Question Window
    
    Connection con=null;
    Statement stmt=null;
    ResultSet rs=null;
    
    public Posts_Window(int counter){
        
        this.counter=counter;
        
        try {  
            //Initialize DB 
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/crawler","root","");
            stmt=con.createStatement();  
        } catch (ClassNotFoundException|SQLException ex) {
            Logger.getLogger(Question_Window.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
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
        updateUI(posts);
    }
    
    
    public void updateUI(TextArea posts){
        
        try {
            String query="select answer from records where id="+counter+";";
            rs=stmt.executeQuery(query);
            if(rs.first()){
                //System.out.println("Got record : "+rs.getString(1));
                posts.setText(rs.getString(1));
                }
        } catch (SQLException ex) {
            Logger.getLogger(Posts_Window.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }


    
}
