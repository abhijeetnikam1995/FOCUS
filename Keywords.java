/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package focus;

/**
 *
 * @author Dev
 */
public class Keywords {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String k1[]={"http://forum.ucweb.com","android","iphone","ios"};
        String kk1[]={"http://fjnujhyorum.ulcweb.com","android","blackberry","lol"};
        String uk="android,lol,ios";
        String k2[]=uk.split(",");
        int flag1=0,flag2=0; // flag1=k1 
        for(String str: k1)
        {
            for(String str2: k2 )
            {
                if(str.equals(str2))
                {
                    System.out.println(str);
                    flag1=flag1+1;
                    
                    
                }
            }
        }      
        for(String str: kk1)
        {
            for(String str2: k2 )
            {
                if(str.equals(str2))
                {
                    System.out.println(str);
                    flag2=flag2+1;
                    
                    
                }
            }
        }
        if(flag1==flag2)
        {
        System.out.println(k1[0]+kk1[0]);
        }
        else if(flag1>flag2)
        {
            System.out.println(k1[0]);
        }
        else
        {
            System.out.println(kk1[0]);
        }
    }
    
}


