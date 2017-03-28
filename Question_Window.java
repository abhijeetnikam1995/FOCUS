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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author rakesh
 */

public class Question_Window extends Application {
    
    public static int counter=0;
    Connection con=null;
    Statement stmt;
    ResultSet rs;
    Button prevButton,nextButton;
    public TextArea q1,q2,q3,q4,q5,q6,q7,q8;
    Button see1,see2,see3,see4,see5,see6,see7,see8;
    
    public Question_Window(){
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
        
        ArrayList<TextArea> questions=new ArrayList<>();

        nextButton = new Button("Next");
        prevButton = new Button("Previous");
        
        
         //Initially keep NEXT and PREV button disabled
        //nextButton.setDisable(true);
        prevButton.setDisable(true);
        
        
        q1=new TextArea("Question 1");
        q2=new TextArea("Question 2");
        q3=new TextArea("Question 3");
        q4=new TextArea("Question 4");
        q5=new TextArea("Question 5");
        q6=new TextArea("Question 6");
        q7=new TextArea("Question 7");
        q8=new TextArea("Question 8");

        //questions={q1,q2,q3,q4,q5,q6,q7,q8};
        

        
        //questions[0]=q1;
        questions.add(q1);
        questions.add(q2);
        questions.add(q3);
        questions.add(q4);
        questions.add(q5);
        questions.add(q6);
        questions.add(q7);
        questions.add(q8);



        
        see1=new Button("See");
        see2=new Button("See");
        see3=new Button("See");
        see4=new Button("See");
        see5=new Button("See");
        see6=new Button("See");
        see7=new Button("See");
        see8=new Button("See");
        
        Button buttons[]={see1,see2,see3,see4,see5,see6,see7,see8};

        VBox vertical_layout=new VBox();
        BorderPane horizontal_layout=new BorderPane();
        
        horizontal_layout.setLeft(prevButton);
        horizontal_layout.setRight(nextButton);

         // To align nodes (Button,SearchBox,ProgressIndicator) in center
        vertical_layout.setAlignment(Pos.CENTER);
     
        vertical_layout.setSpacing(10);

        
        for(int i=0;i<questions.size();i++){
            questions.get(i).setPrefRowCount(2);        // Set Height
        }


        ObservableList list_v = vertical_layout.getChildren();
        
        
        for(int i=0;i<buttons.length;i++){
            list_v.add(buttons[i]);         // Add button 
            list_v.add(questions.get(i));       // Add textarea

        }
        
        list_v.addAll(horizontal_layout);
            
        
        
        
        Scene scene = new Scene(vertical_layout, 1000, 1000);
        
        primaryStage.setTitle("Questions Window");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
        //System.out.println(questions.size());
        
        
        EventHandler<MouseEvent> eventHandler = (MouseEvent e) -> {
            System.out.println("Prev Button Clicked");
            
            counter=counter-8; // Decrement Counter
            if(start(questions,counter)!=1){    //Increment counter if operation fails
                counter=counter+8;
            }
            
            //If prevButton is clicked and no records can be obtained then disble the button
            if(counter<=0){
                prevButton.setDisable(true);
                counter=0;
            }
            
            System.out.println("Prev Button Clicked  : counter->"+counter);
        }; 
         prevButton.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler); 
        
         
        EventHandler<MouseEvent> eventHandler1 = (MouseEvent e) -> {
            System.out.println("Next Button Clicked");
            
            counter=counter+8; // Increment Counter
            if(start(questions,counter)!=1){    //Decrement counter if operation fails
                counter=counter-8;
            }
            //Enable prevButton when previous records can be obtained
            if(counter>=8){
                prevButton.setDisable(false);
                //counter=0;
            }
            System.out.println("Next Button Clicked  : counter->"+counter);
        }; 
        
         nextButton.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler1);          
        
        
                  
        EventHandler<MouseEvent> eventHandler2 = (MouseEvent e) -> {
                                                  Platform.runLater(() -> {
                                                      new Posts_Window(counter+1).start(new Stage());
                                                  });
        }; 
        
         see1.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler2);          
        
         
         EventHandler<MouseEvent> eventHandler3 = (MouseEvent e) -> {
                                                               Platform.runLater(() -> {
                                                      new Posts_Window(counter+2).start(new Stage());
                                                  });

        }; 
        
         see2.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler3);          
        
        EventHandler<MouseEvent> eventHandler4 = (MouseEvent e) -> {
                                                              Platform.runLater(() -> {
                                                      new Posts_Window(counter+3).start(new Stage());
                                                  });

        }; 
        
         see3.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler4); 
        EventHandler<MouseEvent> eventHandler5 = (MouseEvent e) -> {
                                                              Platform.runLater(() -> {
                                                      new Posts_Window(counter+4).start(new Stage());
                                                  });

        }; 
        
         see4.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler5);          
        
         
         EventHandler<MouseEvent> eventHandler6 = (MouseEvent e) -> {
                                                               Platform.runLater(() -> {
                                                      new Posts_Window(counter+5).start(new Stage());
                                                  });

        }; 
        
         see5.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler6);          
        
        EventHandler<MouseEvent> eventHandler7 = (MouseEvent e) -> {
                                                              Platform.runLater(() -> {
                                                      new Posts_Window(counter+6).start(new Stage());
                                                  });

        }; 
        
         see6.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler7); 
         
        EventHandler<MouseEvent> eventHandler8 = (MouseEvent e) -> {
                                                              Platform.runLater(() -> {
                                                      new Posts_Window(counter+7).start(new Stage());
                                                  });

        }; 
        
         see7.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler8);          
        
         
         EventHandler<MouseEvent> eventHandler9 = (MouseEvent e) -> {
                                                               Platform.runLater(() -> {
                                                      new Posts_Window(counter+8).start(new Stage());
                                                  });

        }; 
        
         see8.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler9);          
        

                 
        start(questions,counter);
        
    }
    
    
    public int start(ArrayList<TextArea> questions,int count){

                //System.out.println(questions.size()); 

        try {  
            int tmp;
            tmp=count+8;
            //Check if 8 records can be obtained
            rs=stmt.executeQuery("select question from records where id="+tmp+";");
            System.out.println("counter->"+counter);
            if(rs.first()){
                System.out.println("Obtained 8 records:"+questions.size());
                for(int i=0;i<questions.size();i++){
                    tmp=i+1+count;  //i+1 bcz db stores records from id 1..N but questions array starts from index 0
                    System.out.println("Selecting records i="+tmp);
                    //Add 
                    rs=stmt.executeQuery("select question from records where id="+tmp+";");
                    if(rs.first()){
                        System.out.println("Got record : "+rs.getString(1));
                        questions.get(i).setText(rs.getString(1));
                    }
                    
                }
                return 1;
            }
            else{
                    System.out.println("Failed ");
                    return 0;

            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Question_Window.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;

        
    }


    
}
