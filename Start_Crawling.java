/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package focus;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author rakesh
 */
public class Start_Crawling {
    
    TextArea text_area;
   
    public void start(TextArea jtf) throws IOException, ClassNotFoundException, SQLException{
        
        text_area=jtf; 
        String entry_url,host;
        String tp_regex,ip_regex,turl,iurl;
        ArrayList<String> entry_page_urls,index_urls,thread_urls;
        

        
        //host="https://community.mybb.com";   //HOST
        //turl="community.mybb.com/thread-209344.html";   //ThreadURL without schema
        //iurl="community.mybb.com/forum-176.html";      //IndexURL without shema
        
        /*
        host="http://forum.ucweb.com";
        turl="forum.ucweb.com/forum.php?mod=viewthread&tid=1137449&extra=page%3D1";
        iurl="forum.ucweb.com/forum.php?mod=forumdisplay&fid=104";
       */
        
        
        host="https://fluxbb.org/forums";
        turl="fluxbb.org/forums/viewtopic.php?id=8856";
        iurl="fluxbb.org/forums/viewforum.php?id=1";
        
        //Note : Do not add slash in the host URL
        //Note : regex only matches the path not the complete URL

        System.out.println("\n Host > "+host);
        
        Platform.runLater(() ->text_area.appendText("\nHost > "+host));


        //url="forum.ucweb.com/forum.php?mod=viewthread&tid=1103035&extra=page%3D1";
        //url="forum.statcounter.com/vb/showthread.php?t=35854";//www.discogs.com/forum/thread/403125";
        
        System.out.println("\n Thread URL > "+turl);
        
        final String turl_final=turl;
        Platform.runLater(() ->text_area.appendText("\n\nThread URL > "+turl_final));
        
        turl=turl.substring(turl.indexOf("/")+1, turl.length());
        String[] str={turl}; //convert url string to string array
        //generate regex
        Regex_Generate rj=new Regex_Generate(str);
        tp_regex=rj.generateRegex(str); // get the regex
        Platform.runLater(() ->text_area.appendText("\nGenerated Thread Page Regex > "+tp_regex));
        
        System.out.println("4. Thread Regex > "+tp_regex);

        
        //*---------------
        
        //Generate Regex For Index Pages
        //url="forum.ucweb.com/forum.php?mod=forumdisplay&fid=274";
        //url="forum.statcounter.com/vb/forumdisplay.php?f=47";//www.discogs.com/forum/topic/17";
        System.out.println("5. Index URL > "+iurl);

        final String iurl_final=iurl;  
        Platform.runLater(() ->text_area.appendText("\n\nIndex URL > "+iurl_final));
         
        //Note : regex only matches the path not the complete URL
        iurl=iurl.substring(iurl.indexOf("/")+1, iurl.length());
        String str1[]={iurl}; //convert url string to string array
        //generate regex
        rj=new Regex_Generate(str1);
        ip_regex=rj.generateRegex(str1); // get the regex
        Platform.runLater(() ->text_area.appendText("\nGenerated Index Page Regex > "+ip_regex));
        
        System.out.println("6. Index Regex > "+ip_regex);

                
        //Initialize filter class
        Filter filter_obj=new Filter(ip_regex,tp_regex); //pass regex to filter class
        
        
        
        Entry_URLDiscovery entry_url_obj=new Entry_URLDiscovery(text_area,host,filter_obj);
        entry_url=entry_url_obj.get_entry_url();
        //entry_url=entry_url_obj.redirectedUrl;
        //text_area.sett Platform.runLater(() -> text_area.appendText());
        //Platform.runLater(() -> text_area.appendText("\nPossible Entry URL : "+entry_url));
        
        System.out.println("2. Entry Url > "+entry_url);
        
        Platform.runLater(() ->text_area.appendText("\n\nEntry URL Found > "+entry_url));
         
        Crawl_EntryPage x=new Crawl_EntryPage(text_area,entry_url,filter_obj);//http://forum.ucweb.com"); //Get Links (Paths Only) From Entry Page

        entry_page_urls=x.crawl_page();

        //Filter URL's

        index_urls=filter_obj.filterIndexURL(entry_page_urls);
        thread_urls=filter_obj.filterThreadURL(entry_page_urls);
        
        Platform.runLater(() -> text_area.appendText("\n\nIndex URLS From Entry Page >"+index_urls.size()+index_urls.toString()+"\n"));
        
        System.out.println("7. Index URLS From Entry Page > "+index_urls.toString());

        
        Platform.runLater(() -> text_area.appendText("\n\nThread URLS From Entry Page > "+thread_urls.size()+thread_urls.toString()+"\n"));

        System.out.println("8. Thread URLS From Entry Page > "+thread_urls.toString());

        //---------------*/
        String entry_url_host=entry_url;
        
        //Crawling sub index pages
        if(StringUtils.countMatches(entry_url, "/")>2){ // remove baseline added while entry url generation phase 
                    entry_url_host=entry_url.substring(0,StringUtils.ordinalIndexOf(entry_url, "/", 3));
        }
        
        Platform.runLater(() -> text_area.appendText("\n\nStarting Crawling\n************************************************************"));
        
        //System.out.println
        Crawl_IndexPage crawli=new Crawl_IndexPage(filter_obj,text_area,entry_url_host,index_urls,thread_urls,ip_regex,tp_regex);
        crawli.crawl();
        /*for(String str0:crawli.getIndexURLArray()){
            Platform.runLater(() ->text_area.appendText("\n**INDEX**"+crawli.getIndexURLArray().size()+str0));
        }
        
        for(String str0:crawli.getThreadURLArray()){
            Platform.runLater(() ->text_area.appendText("\n**Thread**"+crawli.getThreadURLArray().size()+str0));
        }
        */
        
        
    }
    
    
}
