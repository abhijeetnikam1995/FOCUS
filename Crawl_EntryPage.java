/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package focus;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.TextArea;

/**
 *
 * @author rakesh
 */
public class Crawl_EntryPage {
    
    String entryPageURL;
    Filter filter;
    TextArea progressText,pagesCounterText;
    
    /**
     *
     * @param text_area
     * @param url
     * @param f
     * @param counter
     */
    public Crawl_EntryPage(TextArea text_area,String url,Filter f,TextArea counter)
    {
        this.progressText=text_area;
        this.entryPageURL=url;
        filter=f;
        this.pagesCounterText=counter;
    }
    
    /**
     *
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public ArrayList<String> crawl_page() throws IOException, ClassNotFoundException, SQLException
    {
        
            Crawl_URL obj=new Crawl_URL(progressText,entryPageURL,filter,pagesCounterText);
            try {
                ArrayList<String> str=obj.get_url_paths(entryPageURL);
                
                
                for(int j=0;j<str.size();j++){
                    String i=str.get(j);
                    //System.out.println(i);
                    if(i.length()>1) // Get rid of only 1 char elements and first slash from paths
                    { 
                        i=i.substring(1,i.length());
                        str.set(j, i);
                    }
                    else
                    {
                        str.set(j,"");  //nullifying 1 char elements
                    }
                }
                str.removeAll(Arrays.asList("", null)); // removing 1 chars elements [SO]
                
                return str;
                
            } catch (IOException | SQLException ex) {
                Logger.getLogger(Crawl_EntryPage.class.getName()).log(Level.SEVERE, null, ex);
            }
  
            
            return null;

    
    }
}
    
  
    

