/*
 * Copyright (C) 2017 rakesh
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package focus;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import org.apache.commons.lang3.math.NumberUtils;
/**
 *
 * @author rakesh
 */

public class Config{
    
 
    static int flippingDepth=0;
    static String entryURL="";
    static String threadURL="";
    static String indexURL="";

    /**
     *
     */
    public Config(){
        
      JLabel l_url=new JLabel("Enter URL", JLabel.CENTER);        
      JLabel l_depth=new JLabel("Enter Depth", JLabel.CENTER);        
      JTextField t_url=new JTextField(50);
      JTextField t_depth=new JTextField(5);
      JButton go=new JButton("Continue");
      
      JPanel controlPanel = new JPanel();
      controlPanel.setLayout(new FlowLayout());
            
      controlPanel.add(l_url);
      controlPanel.add(t_url);

      controlPanel.add(l_depth);
      controlPanel.add(t_depth);
      
      controlPanel.setSize(300, 300);
      
      
      controlPanel.add(go);
      
           
      JFrame mainFrame = new JFrame("Configuration Window");
      mainFrame.setSize(800,170);
      controlPanel.setLayout(new BoxLayout (controlPanel, BoxLayout.Y_AXIS));
      mainFrame.add(controlPanel);
      
      //SO, for aligning JFrame window in center
      Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
      mainFrame.setLocation(dim.width/2-mainFrame.getSize().width/2, dim.height/2-mainFrame.getSize().height/2);
      
        
      mainFrame.setVisible(true);  
      mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      
      go.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) { 
             int flag=0;
             if(t_url.getText().length()>4 && t_url.getText().contains("://")){
                 flag=flag+1;
                 entryURL=t_url.getText();
             }
             else
                JOptionPane.showMessageDialog(mainFrame, "Invalid URL!");
               
             if(t_depth.getText().length()>0 && NumberUtils.isDigits(t_depth.getText())){
                 flag=flag+1;
             }
             else
                JOptionPane.showMessageDialog(mainFrame, "Invalid Depth!");
             
            if(flag==2){
                Check_Support cs=new Check_Support();
                if(cs.isSupported(entryURL)!=1)
                {
                    System.out.println("Forum package is not supported.");
                    JOptionPane.showMessageDialog(mainFrame, "Forum Package Is Not Supported !");
                }
                else{
                    threadURL=cs.getThreadURL();
                    indexURL=cs.getIndexURL();
                    mainFrame.setVisible(false);
                    new Main_Window().start(); 
                }
            }
         }
      }); 
    
      
      


    }
   
}