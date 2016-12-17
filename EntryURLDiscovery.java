/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package focus;
import org.apache.commons.lang3.StringUtils;
import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
/**
 *
 * @author Rakesh
 */
public class EntryURLDiscovery {
    String url,host,schema;
    ArrayList<String> external_urls=new ArrayList<String>();
    public EntryURLDiscovery(String url){
        if(StringUtils.countMatches(url, "/")>3) //if given url contains path then remove it
            url=url.substring(0, StringUtils.ordinalIndexOf(url, "/", 3));
        
        this.url=url;
        schema=url.substring(0,url.indexOf("/")+2); //http or https ??
            }
    
    public String get_entry_url() throws IOException
    {       System.out.println(url);
           ArrayList<String> url_paths=get_url_paths(url); //get all url paths 
           String matched_baseline=baseline_url(url_paths);
           if(matched_baseline=="NOT_FOUND"){
               if(checksubdomains().length()>2)
                   return checksubdomains();
               else
                    matched_baseline="";
           }
           //System.out.println("schema:"+schema+"\nhost:"+host+"\nmatched_baseline");
           
           String entry_url=schema+host+matched_baseline;
           return entry_url;
    }
    
    public String checksubdomains() // return subdomain where forum is hosted
    {
        String subdomain_name;
        
        for(String i : external_urls)
        {
            if(i.contains("://")&&i.contains(".")){
            subdomain_name=i.substring(i.indexOf("/")+2, i.indexOf("."));
            if(subdomain_name.matches("forum")|subdomain_name.matches("community")|subdomain_name.matches("bbs")|subdomain_name.matches("discus")|subdomain_name.matches("board"))
            {
                return i;
            }
            }
        }
        return "";
    }
    
    
    public ArrayList<String> get_url_paths(String url) throws IOException
    {
        System.setProperty("http.proxyHost", "127.0.0.1"); //for debugging purpose
        System.setProperty("http.proxyPort", "8080"); //for debugging purpose
        ArrayList<String> extracted_urls_path=new ArrayList<String>();
        String user_url=url;//"https://affinity.serif.com/forum/index.php?/forum/4-news-and-information/";
        String host;//="affinity.serif.com";//user_url.substring(0);
        String urlwithhost="",urlwithouthost="";
        //URL should end with "/" for below codes to run hence
        
        if(!user_url.endsWith("/"))
        {
            user_url=user_url+"/";
        }
        
        int slashslash = user_url.indexOf("//") + 2; //SO
        host = user_url.substring(slashslash, user_url.indexOf('/', slashslash)); // SO,  Extract hostname from given URL
        this.host=host;
        System.out.println("Host : "+host); 

        Document doc = Jsoup.connect(user_url).userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36")
     .followRedirects(true).get();
        
        String redirect_url=null; //to store URL of HTML redirects
        Elements meta = doc.select("html head meta");
        if (meta.attr("http-equiv").contains("REFRESH")|meta.attr("http-equiv").contains("refresh")|meta.attr("HTTP-EQUIV").contains("REFRESH")|meta.attr("HTTP-EQUIV").contains("refresh")) //For HTML Redirects
        { 
            redirect_url = meta.attr("content").split("=")[1];
            redirect_url=redirect_url.replace("'", ""); // remove ' from URL
            redirect_url=redirect_url.replace("\"", ""); // remove " from URL
            String new_url=schema+host+redirect_url;
            doc = Jsoup.connect(new_url).userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36")
     .followRedirects(true).get();
        }
        
        System.out.println(host);
        
        //if(StringUtils.countMatches(url, "/")>4) //if given url contains path then remove it
         //   host=redirect_url.substring(0, StringUtils.ordinalIndexOf(redirect_url, "/", 4)); // Extract hostname from given URL
        //else
        //    host=redirect_url.substring(0, redirect_url.length());
        
        Elements links = doc.select("a[href]"); // a with href
        for(int i=0;i<links.size();i++) //extracting, filtering and storing links in array
        {
            if(links.get(i).absUrl("href").contains("https://"+host) | links.get(i).absUrl("href").contains("http://"+host)|links.get(i).absUrl("href").contains("https://"+"www."+host) | links.get(i).absUrl("href").contains("http://"+"www."+host)) // www cz sometimes URL's in pages starts with www and we might miss them as external links
            {
                urlwithhost=links.get(i).absUrl("href");
                if(urlwithhost.startsWith("http://"))
                {
                    urlwithouthost=urlwithhost.substring("http://".length()+host.length(),urlwithhost.length());
                }   
                   
                else if(urlwithhost.startsWith("https://"))
                {
                    urlwithouthost=urlwithhost.substring("https://".length()+host.length(),urlwithhost.length());
                }   
                
                extracted_urls_path.add(urlwithouthost); // Save extracted links of same host in array
                System.out.println(links.get(i).absUrl("href")); 
            
            }
            else
            {
                external_urls.add(links.get(i).absUrl("href")+"");
                System.out.println("External Link :"+links.get(i).absUrl("href"));
            } 
        }
        
        
        
        for(String i : extracted_urls_path)
        {
            System.out.println("Paths : "+i);
        }
        return extracted_urls_path;
    }
    
    public String baseline_url(ArrayList<String> url_paths)
    {
        String baseline_matched_paths="NOT_FOUND";
        for(int i=0;i<url_paths.size();i++)
        {
            if(url_paths.get(i).endsWith("/forum")|url_paths.get(i).endsWith("/forum/")|url_paths.get(i).endsWith("/board")|url_paths.get(i).endsWith("/bbs")|url_paths.get(i).endsWith("/community")|url_paths.get(i).endsWith("/discus")|url_paths.get(i).endsWith("/board/")|url_paths.get(i).endsWith("/bbs/")|url_paths.get(i).endsWith("/community/")|url_paths.get(i).endsWith("/discus/"))
            {
                baseline_matched_paths=url_paths.get(i);
            }
        }
        return baseline_matched_paths;
    }
    
    
    
}
