/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package focus;

/**
 *
 * @author rakesh
 */



public class Regex_Generate {
    
        String train_set; //Store first training set in variable to compare it with other training sets char by char
        StringBuilder regex=new StringBuilder();
        String escapeChars="\\$.*+?{}|[]()^";   // Characters that needs to be escaped before generating regex
        int count=0; // count for keeping count of  training sets

       
    public String generateRegex(String[] training_set)  
    {
                //Scan URL's char by char and Generate Regex
        
        for(int i=0;i<training_set[0].length();i++)  //loop all characters
        {   
            count=0; //reset counter
            
            for(int j=0;j<training_set.length;j++) //loop all training sets
            {
            if(train_set.charAt(i)==training_set[j].charAt(i))    //compare first training set with others char by char
                {
                    count=count+1;  // increment counter ; it acts as flag to indicate either a perticular char from all training sets is same or not
                }
            }       
            

            if(count==training_set.length) // if char from all traaining sets is same
            {
                    if(escapeChars.contains(String.valueOf(train_set.charAt(i)))){  //Escape chars 
                        regex.append("\\"+train_set.charAt(i));  // Escape the char
                        continue;
                        
                    }
                    else if("1234567890".contains(String.valueOf(train_set.charAt(i)))) {  // digits regex keyword [probably to avoid bugs]
                        regex.append("(\\d)*");   //bcz there can size of number can vary (id=0,id=11,id=100,etc)
                        continue;
                    }
                    else if("&".matches(String.valueOf(train_set.charAt(i)))){
                        regex.append("(&|&amp;)");      // because sometimes & could be encoded and sometimes not
                        continue;
                    }
                    //System.out.println("Matched "+train_set.charAt(j));
                    regex.append(train_set.charAt(i));
            
            }
            
            else
            {
                    regex.append(".");
            }
            
        }
        return regex.toString();
        
    }
    
    //Length of all training sets must be same
    
    public  Regex_Generate(String[] training_set)
    {
        train_set=training_set[0];
    }
    /**
     * @param args the command line arguments
     */
  
    
}

