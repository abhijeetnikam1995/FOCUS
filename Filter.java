/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package focus;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author rakesh
 */
public class Filter {
    String ip_regex,tp_regex;
    Pattern r;

    /**
     *
     * @param ip_regex
     * @param tp_regex
     */
    public Filter(String ip_regex,String tp_regex) //get and set regex
    {
        this.ip_regex=ip_regex;
        this.tp_regex=tp_regex;
         

    }
    
    /**
     *
     * @param url
     * @return
     */
    public boolean isIndexURL(String url){
                r = Pattern.compile(ip_regex);
                Matcher m = r.matcher(url);
                if(m.matches())
                {
                      return true;
                }
                return false;

    }
    
    /**
     *
     * @param url
     * @return
     */
    public boolean isThreadURL(String url){
                r = Pattern.compile(tp_regex);
                Matcher m = r.matcher(url);
                if(m.matches())
                {
                      return true;
                }
                return false;

    }
    
    /**
     *
     * @param urls
     * @return
     */
    public ArrayList<String> filterIndexURL(ArrayList<String> urls){          

        //remove url's that doesn't match the regex

        r = Pattern.compile(ip_regex);

            ArrayList<String> temp_url=new ArrayList<>();

        for(String url:urls){
        
                // Now create matcher object.
                Matcher m = r.matcher(url);
                if(m.matches())
                {
                    temp_url.add(url);
                }
      
            }
        
        return deDuplicate(temp_url);
        
            
    }
    
    /**
     *
     * @param urls
     * @return
     */
    public ArrayList<String> filterThreadURL(ArrayList<String> urls){          

        //remove url's that doesn't match the regex
            r = Pattern.compile(tp_regex);

            ArrayList<String> temp_url=new ArrayList<>();

        for(String url:urls){
        
                // Now create matcher object.
                Matcher m = r.matcher(url);
                if(m.matches())
                {
                    temp_url.add(url);
                }
      
            }
        
        return deDuplicate(temp_url);
        
            
    }
    
    /**
     *
     * @param urls
     * @return
     */
    public ArrayList<String> deDuplicate(ArrayList<String> urls){
        // Remove duplicate URL's
            Set<String> hs = new HashSet<>(); // SO
            hs.addAll(urls);
            urls.clear();
            urls.addAll(hs);
            return urls;
    }
    
}
