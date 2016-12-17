/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* External Libs : Libraries : Jsoup , Apache commons.lang

*/


package focus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rakesh
 */
public class FOCUS {
   
    /**
     * @param args the command line 
     */
    public static void main(String[] args) {
        try {
            String url,entry_url;
            System.out.print("Enter the URL : ");
            BufferedReader input_taker=new BufferedReader(new InputStreamReader(System.in));
            url=input_taker.readLine();
            
            EntryURLDiscovery entry_url_obj=new EntryURLDiscovery(url);
            entry_url=entry_url_obj.get_entry_url();
            System.out.println("Found Entry URL : "+entry_url);
            
        } catch (IOException ex) {
            Logger.getLogger(FOCUS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
