

/* External Libs : Libraries : Jsoup , Apache commons.lang

*/


package focus;


import java.awt.*;
import java.awt.event.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;


/**
 *
 * @author rakesh
 */

public class FOCUS {
   
    public static JFrame mainFrame;
    //public static JLabel headerLabel;
    //public static JLabel statusLabel;
    public static JPanel controlPanel;  
    public static JTextField url,thread_url,index_url;

//pass jprogress and jtext object to startcrawling class so that it can upate contents when crawling is finished

    public static void main(String[] args) throws IOException {
        Start_Crawling main=new Start_Crawling();
        //main.start();   //Start the process of crawling
        mainFrame = new JFrame("Forum Crawler");
        mainFrame.setSize(700,700);
       // mainFrame.setLayout(new FloatLayout(0,1));
        final JTextArea text_area = new JTextArea(40,50);
        final JScrollPane text_scroll = new JScrollPane(text_area);
        JButton okButton = new JButton("Crawl");

        //text_field=new JTextField();
        //text_field.s

        mainFrame.addWindowListener(new WindowAdapter() {
           public void windowClosing(WindowEvent windowEvent){
              System.exit(0);
           }        
        });    
        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
                controlPanel.add(text_scroll);

        controlPanel.add(okButton);

        //mainFrame.add(headerLabel);
        mainFrame.add(controlPanel);
        //mainFrame.add(statusLabel);
        mainFrame.setVisible(true);  
        
        okButton.setActionCommand("Crawl");

        okButton.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent evt) {
                 System.out.println("Crawl Button clicked.");
                 okButton.setEnabled(false);
                 new Thread(
                        new Runnable() {

                            public void run() {
                                try {
                                    
                                    main.start(text_area);
                                } catch (IOException ex) {
                                    Logger.getLogger(FOCUS.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                      }).start();
          
          }
        });

              mainFrame.setVisible(true);  


    }
    
}
