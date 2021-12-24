 public static String CheckConsistent(String [] lines){
       String str2= null;
       String str=null;
       String cons="";

       Stack<String> stk = new Stack();
       Stack<String> stk2 = new Stack();

       for(int i=0;i<lines.length;i++)
       {  
        // deleting the spaces at the end and the begining of the lines of the array
        lines[i]=lines[i].trim();
              
        String str1 = lines[i].substring(0,lines[i].length());
    
     int startOpenning = lines[i].indexOf("<");
     int endStarting = lines[i].indexOf(">"); 
           boolean x = str1.contains(" ");
           boolean spaces = lines[i].indexOf(" ") < lines [i].indexOf ("</");
          // skip the lines containing <? or <! 
           if( lines[i].contains("<")  && ( (lines[i].charAt(startOpenning+1) == '!') || (lines[i].charAt(startOpenning+1) == '?')  ) || lines[i].trim().length() == 0    )
           {
            
               continue;
           }
        
                   // checking the opening tag
                   
            if( lines[i].contains("<")  && (lines[i].charAt(startOpenning+1) != '/')  )
            {      
                   // pushing the tag name in the stack
                 if( !x )
                 {
                    str = lines[i].substring(startOpenning+1,endStarting);
                    stk.push(str);
                   
                 }
                 else if(spaces){
                     str = lines[i].substring(startOpenning+1,endStarting);
                    stk.push(str);
                   
                 }

               // check if the line contains spaces which means containing attribute
                else 
                {
                str = lines[i].substring(startOpenning+1,lines[i].indexOf(" "));
                stk.push(str);
              
               
                }
            }
            //  checking the closing tag
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
                }
            }
                // no error
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
   
