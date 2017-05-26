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

/**
 *
 * @author rakesh
 */
public class Crawl_IndexPage { // Crawls Index Pages
    
    ArrayList<String> index_urls,thread_urls;
    String i_regex,t_regex,url;
    final TextArea text_area;
    Filter filter;
    String exclude_parameters[]={"action=","fromuid=","from=","page=",";all","sort=", "extra=", "filter=","lastpage=","orderby=","digest=","dateline=","specialType=","typeId=","prefix=","sortby=","detect=","order="};
    public Crawl_IndexPage(Filter f,TextArea text_area,String url,ArrayList<String> index_urls,ArrayList<String> thread_urls,String i_regex,String t_regex)
    {
        this.index_urls=index_urls;
        this.thread_urls=thread_urls;
        this.i_regex=i_regex;
        this.t_regex=t_regex;
        this.url=url;
        this.text_area=text_area;
        filter=f;
    }
    
    public void crawl() {
        
        try{
        
        Filter filter=new Filter(i_regex,t_regex);
        //f_obj.deDuplicate(index_urls);
        ArrayList<String> temp_urls=new ArrayList<>();
        for(int i=0;i<index_urls.size();i++){
            String str=index_urls.get(i);
            str=url+"/"+str;
            
            
            System.out.println("\nLocation :  >> "+str);
            final String str_final=str;
            Platform.runLater(() ->text_area.appendText("\n\nLocation :  >> "+str_final));
            
            //Download str
            //Extract Links
            //DeDuplicate it
            //Match it with regex and append in appropriate regex
            
            Crawl_URL e_obj=new Crawl_URL(text_area,str,filter);
            temp_urls=e_obj.get_url_paths(str);            
            temp_urls=filter.deDuplicate(temp_urls);

            
            for(String str1:temp_urls){

                if(str1.startsWith("/")){   //remove first slash if there is any bcz regex does not expect slash at begining
                    str1=str1.substring(1,str1.length());
                }
                
                if(str1.endsWith("/")){   //remove last slash if there is any bcz websites behavior changes
                    str1=str1.substring(0,str1.length()-1);
                }
                
                if(str1 == null){
                    continue;
                }
                
                for(String exclude : exclude_parameters)    //check if the url contains excluded strings
                {
                    if(str1.contains(exclude)){
                        str1="";
                    }
                }
                
                if(str1.equals("")){ //if url contains excluded string then continue to next iteration
                    continue;
                }
                
                if(filter.isIndexURL(str1))
                {
   
                    if(!index_urls.contains(str1))  //to avoid duplicates
                    {                    
                        System.out.println("\nAdding Index URL:"+str1);
                        final String str_final1=str1;
                        Platform.runLater(() ->text_area.appendText("\nAdding Index URL >> "+str_final1));
                        index_urls.add(str1);

                    }
                }
                else if(filter.isThreadURL(str1))
                {
                    if(!thread_urls.contains(str1))  //to avoid duplicates
                    {
                        System.out.println("\nAdding Thread URL:"+str1);
                        final String str_final1=str1;
                        Platform.runLater(() ->text_area.appendText("\nAdding Thread URL >> "+str_final1));
                        thread_urls.add(str1);
                    }
                }
            }
            
        }
        }
        catch(IOException | ClassNotFoundException | SQLException e){
                
            System.out.println("\nCause of error"+e.getLocalizedMessage());
            
                
                }
 
            
        
        
        
    }
    
    public ArrayList<String> getIndexURLArray(){
        return index_urls;
    }
    
    public ArrayList<String> getThreadURLArray(){
        return thread_urls;
    }
    
}
