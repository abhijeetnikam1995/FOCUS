/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package focus;

import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JTextArea;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author rakesh
 */
public class Start_Crawling {
    
    JTextArea text_area;
   
    public void start(JTextArea jtf) throws IOException{
        
        text_area=jtf; //TextFieldArea object to update progress
        String entry_url,host;
        String tp_regex,ip_regex,turl,iurl;
        ArrayList<String> entry_page_urls,index_urls,thread_urls;

        ///*--------------
        
        //Find Entry URL

        
        host="http://forum.ucweb.com/";   //HOST
        turl="forum.ucweb.com/forum.php?mod=viewthread&tid=1134508&extra=page%3D1";   //ThreadURL without schema
        iurl="forum.ucweb.com/forum.php?mod=forumdisplay&fid=104";      //IndexURL without shema
        
        
        //Note : Do not add slash in the host URL
        System.out.println("1. Host > "+host);
        
        Entry_URLDiscovery entry_url_obj=new Entry_URLDiscovery(host);
        entry_url=entry_url_obj.get_entry_url();
        //entry_url=entry_url_obj.redirectedUrl;
        text_area.append("\nPossible Entry URL : "+entry_url);
        
        System.out.println("2. Entry Url > "+entry_url);

        
        //---------------*/
        
        ///*--------------
        //Generate Regex For Thread Pages
        //url="forum.ucweb.com/forum.php?mod=viewthread&tid=1103035&extra=page%3D1";
        //url="forum.statcounter.com/vb/showthread.php?t=35854";//www.discogs.com/forum/thread/403125";
        System.out.println("3. Thread URL > "+turl);

        
        //Note : regex only matches the path not the complete URL
        turl=turl.substring(turl.indexOf("/")+1, turl.length());
        String[] str={turl}; //convert url string to string array
        //generate regex
        Regex_Generate rj=new Regex_Generate(str);
        tp_regex=rj.generateRegex(str); // get the regex
        text_area.append("\nThread Page Regex : "+tp_regex);
        
        System.out.println("4. Thread Regex > "+tp_regex);

        
        //*---------------
        
        //Generate Regex For Index Pages
        //url="forum.ucweb.com/forum.php?mod=forumdisplay&fid=274";
        //url="forum.statcounter.com/vb/forumdisplay.php?f=47";//www.discogs.com/forum/topic/17";
        System.out.println("5. Index URL > "+iurl);

        
        //Note : regex only matches the path not the complete URL
        iurl=iurl.substring(iurl.indexOf("/")+1, iurl.length());
        String str1[]={iurl}; //convert url string to string array
        //generate regex
        rj=new Regex_Generate(str1);
        ip_regex=rj.generateRegex(str1); // get the regex
        text_area.append("\nIndex Page Regex : "+ip_regex);
        
        System.out.println("6. Index Regex > "+ip_regex);

                
        //Initialize filter class
        Filter filter_obj=new Filter(ip_regex,tp_regex); //pass regex to filter class
        
        
        /*---------------*/
        
        //Crawling Entry Page
        
        
        Crawl_EntryPage x=new Crawl_EntryPage(entry_url);//http://forum.ucweb.com"); //Get Links (Paths Only) From Entry Page
        
        
        
        
        entry_page_urls=x.crawl_page();
        //System.out.println(">>>"+x.crawl_page().toString());
        
        //---------------------
        //Filter URL's

        index_urls=filter_obj.filterIndexURL(entry_page_urls);
        thread_urls=filter_obj.filterThreadURL(entry_page_urls);
        
        text_area.append("\nIndex URLS :"+index_urls.size()+index_urls.toString());
        
        System.out.println("7. Index URLS From Entry Page > "+index_urls.toString());

        
        text_area.append("\nThread URLS : "+thread_urls.size()+thread_urls.toString());

        System.out.println("8. Thread URLS From Entry Page > "+thread_urls.toString());

        //---------------*/
        String entry_url_host=entry_url;
        //Crawling sub index pages
        if(StringUtils.countMatches(entry_url, "/")>2){ // remove baseline added while entry url generation phase 
                    entry_url_host=entry_url.substring(0,StringUtils.ordinalIndexOf(entry_url, "/", 3));
        }
        
        //System.out.println
        Crawl_IndexPage crawli=new Crawl_IndexPage(text_area,entry_url_host,index_urls,thread_urls,ip_regex,tp_regex);
        crawli.crawl();
        for(String str0:crawli.getIndexURLArray()){
            text_area.append("\n**INDEX**"+crawli.getIndexURLArray().size()+str0);
        }
        
        for(String str0:crawli.getThreadURLArray()){
            text_area.append("\n**Thread**"+crawli.getThreadURLArray().size()+str0);
        }
        
        
    }
    
    
}
