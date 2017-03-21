/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package focus;

import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JTextArea;

/**
 *
 * @author rakesh
 */
public class Crawl_IndexPage { // Crawls Index Pages
    
    ArrayList<String> index_urls,thread_urls;
    String i_regex,t_regex,url;
    JTextArea text_area;
    
    public Crawl_IndexPage(JTextArea text_area,String url,ArrayList<String> index_urls,ArrayList<String> thread_urls,String i_regex,String t_regex)
    {
        this.index_urls=index_urls;
        this.thread_urls=thread_urls;
        this.i_regex=i_regex;
        this.t_regex=t_regex;
        this.url=url;
        this.text_area=text_area;
    }
    
    public void crawl() throws IOException{
        
        
        Filter filter=new Filter(i_regex,t_regex);
        //f_obj.deDuplicate(index_urls);
        ArrayList<String> temp_urls=new ArrayList<>();
        for(int i=0;i<index_urls.size();i++){
            String str=index_urls.get(i);
            str=url+"/"+str;
            text_area.append("\nIndex URL >> "+str);
            //Download str
            //Extract Links
            //DeDuplicate it
            //Match it with regex and append in appropriate regex
            
            Entry_URLDiscovery e_obj=new Entry_URLDiscovery(str);
            temp_urls=e_obj.get_url_paths(str);
           // System.out.println("B Length : "+temp_urls.size());
            
            temp_urls=filter.deDuplicate(temp_urls);
            //System.out.println("A Length : "+temp_urls.size());

            //System.exit(0);
            
            for(String str1:temp_urls){
                //System.out.println("Sub Index URL >> "+str1);

                if(str1.startsWith("/")){   //remove first slash if there is any bcz regex does not expect slash at begining
                    str1=str1.substring(1,str1.length());
                }
                
                 if(str1.endsWith("/")){   //remove last slash if there is any bcz websites behavior changes
                    str1=str1.substring(0,str1.length()-1);
                }
                
                if(filter.isIndexURL(str1))
                {
                    
                    if(!index_urls.contains(str1))  //to avoid duplicates
                    {                    
                        text_area.append("\nAdding Index URL >> "+str1);
                        index_urls.add(str1);

                    }
                }
                else if(filter.isThreadURL(str1))
                {
                    if(!thread_urls.contains(str1))  //to avoid duplicates
                    {
                        text_area.append("\nAdding Thread URL >> "+str1);
                        thread_urls.add(str1);
                    }
                }
            }
            
            //System.out.println("Index : "+index_urls.size()+"\n"+index_urls);
            //System.out.println("Thread : "+thread_urls.size()+"\n"+thread_urls);

            //System.exit(1);
            
            
        }
        
        
    }
    
    public ArrayList<String> getIndexURLArray(){
        return index_urls;
    }
    
    public ArrayList<String> getThreadURLArray(){
        return thread_urls;
    }
    
}
