/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package focus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javax.swing.JTextArea;

/**
 *
 * @author rakesh
 */
public class Start_Crawling {
    
    JTextArea text_area;
    
   
    public void start(JTextArea jtf) throws IOException{
        
         text_area=jtf; //TextFieldArea object to update progress
         String url,entry_url,host;
         String tp_regex,ip_regex;
         ArrayList<String> entry_page_urls,index_urls,thread_urls;
        
        BufferedReader input_taker=new BufferedReader(new InputStreamReader(System.in));
        /*--------------*/
        
        

        
        ///*--------------
        
        //Find Entry URL
        
        //System.out.print("Enter the URL : ");
        //url=input_taker.readLine();
        host="http://forum.ucweb.com";
        Entry_URLDiscovery entry_url_obj=new Entry_URLDiscovery(host);
        entry_url=entry_url_obj.get_entry_url();
        text_area.append("\nPossible Entry URL : "+entry_url);
        //---------------*/
        
        ///*--------------
        //Generate Regex For Thread Pages
        
        //System.out.print("Enter Any Thread Page URL : http://");
        //input_taker=new BufferedReader(new InputStreamReader(System.in));
        //url=input_taker.readLine();
        url="forum.ucweb.com/forum.php?mod=viewthread&tid=1103035&extra=page%3D1";
        //Note : regex only matches the path not the complete URL
        url=url.substring(url.indexOf("/")+1, url.length());
        String[] str={url}; //convert url string to string array
        //generate regex
        Regex_Generate rj=new Regex_Generate(str);
        tp_regex=rj.generateRegex(str); // get the regex
        text_area.append("\nThread Page Regex : "+tp_regex);
        
        //*---------------
        
        //Generate Regex For Index Pages
        
        //System.out.print("Enter Any Index Page URL : http://");
        //input_taker=new BufferedReader(new InputStreamReader(System.in));
        //url=input_taker.readLine();
        url="forum.ucweb.com/forum.php?mod=forumdisplay&fid=274";
        //Note : regex only matches the path not the complete URL
        url=url.substring(url.indexOf("/")+1, url.length());
        String str1[]={url}; //convert url string to string array
        //generate regex
        rj=new Regex_Generate(str1);
        ip_regex=rj.generateRegex(str1); // get the regex
        text_area.append("\nIndex Page Regex : "+ip_regex);
        
        
                
        //Initialize filter class
        Filter filter_obj=new Filter(ip_regex,tp_regex); //pass regex to filter class
        
        
        /*---------------*/
        
        //Crawling Entry Page
        
        Crawl_EntryPage x=new Crawl_EntryPage("http://forum.ucweb.com"); //Get Links (Paths Only) From Entry Page
        entry_page_urls=x.crawl_page();
        //System.out.println(">>>"+x.crawl_page().toString());
        
        //---------------------
        //Filter URL's

        index_urls=filter_obj.filterIndexURL(entry_page_urls);
        thread_urls=filter_obj.filterThreadURL(entry_page_urls);

        text_area.append("\nIndex URLS :"+index_urls.size()+index_urls.toString());
        
        text_area.append("\nThread URLS :"+thread_urls.size()+thread_urls.toString());

        //---------------*/
        
        //Crawling sub index pages
        
        Crawl_IndexPage crawli=new Crawl_IndexPage(text_area,host,index_urls,thread_urls,ip_regex,tp_regex);
        crawli.crawl();
        for(String str0:crawli.getIndexURLArray()){
            text_area.append("\n**INDEX**"+crawli.getIndexURLArray().size()+str0);
        }
        
        for(String str0:crawli.getThreadURLArray()){
            text_area.append("\n**Thread**"+crawli.getThreadURLArray().size()+str0);
        }
        
        
    }
    
    
}
