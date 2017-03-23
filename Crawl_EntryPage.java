/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package focus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rakesh
 */
public class Crawl_EntryPage {
    
    String url;
    
    public Crawl_EntryPage(String url)
    {
        this.url=url;
    }
    
    public ArrayList<String> crawl_page() throws IOException
    {
        Entry_URLDiscovery obj=new Entry_URLDiscovery(url);
        //System.out.println("db:"+obj.redirectedUrl);
        //url=obj.redirectedUrl;  // set redirected URL 
        try {
            ArrayList<String> str=obj.get_url_paths(url);
            //ListIterator li =str.listIterator();
            System.out.println("DB:"+url);
            System.out.println("DB:"+str.toString());

            for(int j=0;j<str.size();j++){
                String i=str.get(j);
                //li.next();
                System.out.println(i);
                //System.exit(1);
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
            
        } catch (IOException ex) {
            Logger.getLogger(Crawl_EntryPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
  
    
}
