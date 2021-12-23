 public static String CheckConsistent(String [] lines){
       String str2= null;
       String str=null;
       String cons="";

       Stack<String> stk = new Stack();
       Stack<String> stk2 = new Stack();

       for(int i=0;i<lines.length;i++)
       {  
        lines[i]=lines[i].trim();
              
        String str1 = lines[i].substring(0,lines[i].length());
     //   System.out.println(str1);
     int startOpenning = lines[i].indexOf("<");
     int endStarting = lines[i].indexOf(">"); 
           boolean x = str1.contains(" ");
           boolean spaces = lines[i].indexOf(" ")< lines [i].indexOf ("</");
           if( lines[i].contains("<")  && ( (lines[i].charAt(startOpenning+1) == '!') || (lines[i].charAt(startOpenning+1) == '?')  )   )
           {
            
               continue;
           }
        
                   // openning
                   
            if( lines[i].contains("<")  && (lines[i].charAt(startOpenning+1) != '/')  )
            {       
                 if( !x )
                 {
                    str = lines[i].substring(startOpenning+1,endStarting);
                    stk.push(str);
                   
                 }
                 else if(spaces){
                     str = lines[i].substring(startOpenning+1,endStarting);
                    stk.push(str);
                   
                 }

            
                else 
                {
                str = lines[i].substring(startOpenning+1,lines[i].indexOf(" "));
                stk.push(str);
              
               
                }
            }
            //  closing
            int startClosing= lines[i].indexOf("/");
            int endClosing=lines[i].length()-1;
            
            if( startClosing != -1  )
                {
                    str2 = lines[i].substring(startClosing+1,endClosing);
                   //  System.out.println(str2);
                   stk2.push(str2);
                    if (str2.compareTo(stk.peek()) == 0 )
                    {
                        stk.pop();
                       
                        stk2.pop();
                       

                    }
                    else
                    {
                       
                            cons="Error in consistency";
                       
                        
                    
                }}
                
            if (stk.empty())
            {
                    cons="File is consistent";

            }
            if(!stk2.empty()){
                
                        cons="Error in consistency";
            }
       }
return cons;
    }
   
