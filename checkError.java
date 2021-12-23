public static String CheckError(String [] lines){
       String str2= null;
       String str=null;
       ArrayList<String> Fixerror = new ArrayList<>();
       Stack<String> stk = new Stack();
       Stack <String> stk_openclose=new Stack();
       Stack <String> stk2=new Stack();
       String indent_opening="";
       String indent_closing="";
       int counter=0;
       for(int i=0;i<lines.length;i++)
       {
           
           if(lines[i].contains("<")&&!lines[i].contains("/")&& !((lines[i].charAt(lines[i].indexOf("<")+1) == '!') || (lines[i].charAt(lines[i].indexOf("<")+1) == '?'))  )
           {
               indent_opening=lines[i].substring(0, lines[i].indexOf("<"));
               //System.out.println(indent_opening+"opening"+lines[i]);
           }
           else if(lines[i].contains("</")){
               
                   indent_closing=lines[i].substring(0, lines[i].indexOf("<"));
               //System.out.println(indent_closing+"close"+lines[i]);
           }
        lines[i]=lines[i].trim();
        Fixerror.add(lines[i]);
        String str1 = lines[i].substring(0,lines[i].length());
        int startOpenning = lines[i].indexOf("<");
        int endOpening = lines[i].indexOf(">");
        boolean x = str1.contains(" ");
        
        if( lines[i].contains("<")  && ( (lines[i].charAt(startOpenning+1) == '!') || (lines[i].charAt(startOpenning+1) == '?')  ) || lines[i].trim().length()==0  )
           {
            
               continue;
           }
          
                   // openning
                
            if( lines[i].contains("<")  && (lines[i].charAt(startOpenning+1) != '/') )
            {       
                 if( !x  )
                 {
                    
                    str = lines[i].substring(startOpenning+1,endOpening);
                    stk.push(str);
                    stk_openclose.push(indent_opening);
                    counter++;
              
                 }
                
                else 
                {
                    
                str = lines[i].substring(startOpenning+1,lines[i].indexOf(" "));
                stk.push(str);
                stk_openclose.push(indent_opening);
                counter++;
                }
            }
    
            //  closing
            int startClosing= lines[i].indexOf("/");
            int endClosing=lines[i].length()-1;
            if(!Fixerror.get(i-1).startsWith("<", 0) && !lines[i].contains("/")){
               
                    if(str2.compareTo(stk.peek()) !=0)
                    {
                      
                       stk.pop();
                        System.out.println(Fixerror.set(i,"</"+stk.peek()+">"));
                      
                       Fixerror.set(i-1,lines[i].replaceAll(lines[i],"</"+stk.peek()+">"));
                       Fixerror.add(i,lines[i]);
                     if(str.compareTo(stk.peek())==0&&indent_opening.compareTo( stk_openclose.peek())==0){
                       
                       //Fixerror.set(i,lines[i]);
                       System.out.println( Fixerror.get(i)+"nnnnnnn");
                      
                         stk_openclose.pop();
                         
                           //System.out.println("Error in consistency");
                           //Fixerror.add(lines[i]);
                     
               stk.pop();
              stk.push(str);
              
              }}}
            if( startClosing != -1 )
                {
                    str2 = lines[i].substring(startClosing+1,endClosing);
                   counter--;
              //no error
                    if (str2.compareTo(stk.peek()) == 0 )
                    {
                        stk.pop();
                        stk_openclose.pop();
                        stk2.push(str2);
                        
                    }
                    else
                    {
                        //System.out.println("Error in consistency");
                       
                        Stack <String> err = new Stack();
                        err.push(str2);
                        //System.out.println(err + lines[i]);
                        //System.out.println(stk.peek());
                        lines[i]=lines[i].replace(str2, stk.peek()); 
                        stk.pop();
                        Fixerror.set(i,lines[i]);
                       
                    
                       
                        stk2.pop();
                    
                    }
                    
                }
              
             
            // System.out.println(Fixerror[i]);
             if(!stk2.empty()){
                
                       
              //  System.out.println(  "Error in consistency");
            }
         
            if (stk.empty())
            {
                
                System.out.println("File is consistent");

            }
         System.out.println(Fixerror.get(i));
    }
return Fixerror.toString();
    }
