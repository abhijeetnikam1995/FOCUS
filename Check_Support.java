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

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/*
 *
 * @author rakesh
 */

public class Check_Support {
    
    String url; // If url does not require any script then it should end with a slash (/) (http://lol.com/ or http://lol.com/forum.php)
    
            Multimap<String, String> signature_cookie = ArrayListMultimap.create();
            Multimap<String, String> signature_body = ArrayListMultimap.create();
            HashMap<String, String> index_url = new HashMap<String, String>();
            HashMap<String, String> thread_url = new HashMap<String, String>(); 
    

            
    static String forum_package=""; // For storing detected forum package type
    
    public Check_Support(){
    
        signature_cookie.put("MYBB","mybb[lastactive]");
        signature_cookie.put("DISCUZ","_lastact=");
        signature_cookie.put("DISCUZ","_lastvisit=");
        
        signature_body.put("DISCUZ","<meta name=\"generator\" content=\"Discuz");
        signature_body.put("DISCUZ","Powered by Discuz!</title>");
        
        signature_body.put("MYBB", "Powered By <a href=\"http://www.mybboard.net");
        signature_body.put("MYBB", "Powered By <a href=\"https://www.mybboard.net");

        signature_body.put("MYBB-1","Powered By <a href=\"http://mybb.com");
        signature_body.put("MYBB-1","Powered By <a href=\"https://mybb.com");

        signature_body.put("FLUXBB","<p id=\"poweredby\">Powered by <a href=\"http://fluxbb.org");
        signature_body.put("FLUXBB","<p id=\"poweredby\">Powered by <a href=\"https://fluxbb.org");
        
        signature_body.put("PHPBB","Powered by <a href=\"https://www.phpbb.com");
        signature_body.put("PHPBB","Powered by <a href=\"http://www.phpbb.com");
        signature_body.put("PHPBB","var style_cookie = 'phpBBstyle';"); //https://www.raspberrypi.org/forums/
        signature_body.put("PHPBB", "<body id=\"phpbb\""); //http://www.airliners.net/forum/
        //signature_body.put("DISCUZ-1", "discuz_uid = '");
        signature_body.put("PUNBB", "Powered by <a href=\"http://www.punbb.org");
        signature_body.put("PUNBB", "Powered by <a href=\"http://punbb.informer.com");
        signature_body.put("PUNBB", "<p id=\"copyright\">Powered by <strong><a href=\"http://punbb.informer.com");
        signature_body.put("PUNBB", "Powered by <a href=\"https://www.punbb.org");
        signature_body.put("PUNBB", "Powered by <a href=\"https://punbb.informer.com");
        signature_body.put("PUNBB", "<p id=\"copyright\">Powered by <strong><a href=\"https://punbb.informer.com");
        
        index_url.put("DISCUZ","?mod=forumdisplay&fid=1");
        //index_url.put("DISCUZ-1","forum-1-1.html");
        index_url.put("MYBB","forum-1.html");
        index_url.put("FLUXBB","viewforum.php?id=1");
        index_url.put("PHPBB","viewforum.php?f=1");
        index_url.put("PUNBB","viewforum.php?id=1");
        index_url.put("MYBB-1","forumdisplay.php?fid=1");
        
        thread_url.put("DISCUZ","?mod=viewthread&tid=1&extra=page%3D1");
        //thread_url.put("DISCUZ-1","thread-1-1-1.html");
        thread_url.put("MYBB","thread-1.html");
        thread_url.put("FLUXBB","viewforum.php?id=1");
        thread_url.put("PHPBB","viewtopic.php?f=1&t=1");
        thread_url.put("PUNBB","viewtopic.php?id=1");
        thread_url.put("MYBB-1","showthread.php?tid=1");


    }
    
    //Identify thread and index URL
    
    public int isSupported(String url) 
    {
        this.url=url;
        

        
        try {
            
            Connection.Response response = Jsoup.connect(url).method(Connection.Method.GET).followRedirects(false).userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36").timeout(100000).execute();
            
            Document doc = response.parse();

            String cookies="Cookies Not Set.";
            if(response.header("Set-Cookie")!=null)
            {
                cookies=response.header("Set-Cookie");
            }
            System.out.println(cookies);
            
            
            //Check signature in cookies
            for(String sign:signature_cookie.keySet()){
                
                for(String value:signature_cookie.get(sign))
                {
                    if(cookies.contains(value))
                    {   
                        System.out.println(sign);
                        forum_package=sign;
                        return 1;
                    }
                }
                

            }
                        
            
            //Check signature in the body
            for(String sign:signature_body.keySet()){
                System.out.println(sign);
                for(String value:signature_body.get(sign))
                {   System.out.println(value);
                    if(doc.html().contains(value))
                    {   
                        System.out.println(sign);
                        forum_package=sign;
                        return 1;
                    }
                }
                

            }
            
            System.out.println("Couldn't find software package");

            return 0; // because forum is not supported
            
        } catch (IOException ex) {
            Logger.getLogger(Check_Support.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0; // because forum is not supported or unable to detect forum
    }
    
    public String getThreadURL()
    {
        return url+thread_url.get(forum_package);
    }
    
        public String getIndexURL()
    {
        return url+index_url.get(forum_package);

    }
    
    
}