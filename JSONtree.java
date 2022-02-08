package project;

import java.util.ArrayList;
import java.io.IOException;
import java.util.ArrayList;
import static project.Formattig.getPrettyXml;
import static project.Reading.readString;

public class JSONtree {
    
  private Node root;

    public JSONtree() 
    {
        
    }
    
    // print tree with tagnames and depths  
    public void print (Node root)
    {
    
    for (int i = 0;i<root.children.size();i++)
    {
        System.out.print(root.children.get(i).getDepth());
           System.out.println(root.children.get(i).getTagName());
    }
    }
      // method to get the children of each node and put it in an arraylist
    public ArrayList <Node> get_children (Node node) throws NullPointerException
    {
    ArrayList <Node> temp = new ArrayList <>() ;
    int n =  root.children.indexOf(node) ;
    int d = root.children.get(n).getDepth();
         for (int j = n + 1 ; j<root.children.size();j++)
         {
             if((d != root.children.get(j).getDepth())&& root.children.get(j).getDepth() == d+1)
             {         
              temp.add(root.children.get(j));
             }
             else 
             {
               if(root.children.get(j).getDepth()== d)
               {   
                   break;
    
               }
               else{
                    continue;}
             }
         }
         return temp;
    }
    
    //traverse tree and setting the tag name, value and attribute of the nodes 
    public Node Insert(String []arr){
          Node curr = new Node () ;
          int d = 0;
         String text = "";
       
         for(int i=0;i<arr.length;i++){
        
             arr[i]=arr[i].trim();
             
         int startOpenning = arr[i].indexOf("<");
         int endStarting = arr[i].indexOf(">"); 
         int startClosing = arr[i].indexOf("/");
         int endClosing = arr[i].length()-1;
         String str1 = arr[i].substring(0,arr[i].length());
         String str2,s,s2= null;
               
 boolean x = str1.contains(" ");

// openning tag

  if( arr[i].contains("<")  && (arr[i].charAt(startOpenning+1) != '/')  ){
                 Node child1 = new Node ();
               
                 if (i==0)
                         {
                         curr = child1;
                         curr.parent=null;
                         curr.setDepth(d);
                         root = curr;
                         }
                 else
                 {
                 d ++ ;
                  curr = child1;
                  curr.addChild(child1);
                  curr.setClosingTag(false);
                  curr.setDepth(d);
                 
                  root.children.add(curr);
                 }
               
                  if(x)
                  {
                      str1 = str1.substring(1,str1.indexOf(' '));
                      curr.setTagName(str1);
                      curr.setTagAttributes(arr[i].substring(arr[i].indexOf(' ')+1,arr[i].length()-1)  );
                
                  }
                 
                  else {
                      curr.setTagName(str1.substring(1, arr[i].length()-1));
                   

                  }
  }
             
             // tag value  
           else if (! arr[i].contains("<")&& arr[i].trim().length()!= 0 ) {
                 curr.setTagValue(arr[i]);
             }
            //closing tag 
             if ( startClosing != -1 )
             {
                 str2 = arr[i].substring(2,arr[i].length());
                 d--;
                 curr.setClosingTag(true) ;
                 curr = curr.parent ;
             }
          }
           return root;
        }
    
        private static String repeatString(int stack)
       {
     StringBuilder indent = new StringBuilder();
     for (int i = 0; i < stack; i++) {
        indent.append("    ");
     }
     return indent.toString();
       }   
    
// Converting the Xml file to JSON    
public String ConvertTOJSON (String [] arr){
    
    ArrayList <Node> tmp = new ArrayList<>();
    ArrayList <Node> tmp2,tmp3,tmp4 = new ArrayList<>();
    Node root = Insert(arr);
 
    String text = "{\n" +repeatString(1)+ "\"" + root.getTagName()+ ":\"{\n";
            tmp = get_children(root.children.get(0));
          
            text+= repeatString(2) +"\""+root.children.get(0).getTagName()+"\":[\n";
              for (int k = 0; k<tmp.size();k++)
              {
                tmp2 = get_children(tmp.get(k));
                if(tmp2.isEmpty())
                {
                text+=repeatString(3)+"\""+tmp.get(k).getTagName()+"\":"+"\""+tmp.get(k).getTagValue()+"\",\n";   
                }
                else
                {    
                    text+=repeatString(3)+"\""+tmp.get(k).getTagName()+"\":[\n";
                    
                    for(int f=0;f<tmp2.size();f++)
                    {
                        tmp3 = get_children(tmp2.get(f));
                    if(tmp2.size()>1 && tmp2.get(0).getTagName().equals(tmp2.get(1).getTagName()))
                    {
                      for(int m=0;m<tmp3.size();m++)
                    {  
                    tmp4 = get_children(tmp3.get(m));
                    if(tmp4.isEmpty())
                    {
                        text+=repeatString(4)+"{\n";
                    text+=repeatString(5)+"\""+tmp3.get(m).getTagName()+"\":"+"\""+tmp3.get(m).getTagValue()+"\""; 
                    if(tmp3.size()==1){
                         if(f<tmp2.size()-1)
                        {text+="\n"+repeatString(4)+"},\n";}
                        else{text+="\n"+repeatString(4)+"}\n";}
                        
                    }
                    else{text+=",\n";}
                    
                    }
                    else
                    {
                      text+=repeatString(5)+"\""+tmp3.get(m).getTagName()+"\":";  
                        for(int n = 0;n<tmp4.size();n++){
                         if(tmp4.size()>1 && tmp4.get(n).getTagName().equals(tmp4.get(n+1).getTagName())){
                         text+="[\n"+repeatString(6)+"\""+tmp4.get(n).getTagValue()+"\",\n"+repeatString(6)+"\""+tmp4.get(n+1).getTagValue()+"\"\n"+repeatString(5)+"]\n";
                          break;
                         }
                         else 
                         {
                          text+="{\n"+repeatString(6)+"\""+tmp4.get(0).getTagName()+"\":"+"\""+tmp4.get(0).getTagValue()+"\"\n";
                          text+=repeatString(5)+"}\n";
                         }
                        
                        }
                        if(f<tmp2.size()-1)
                        {text+=repeatString(4)+"},\n";}
                        else{text+=repeatString(4)+"}\n";}
                   
                    }
                    
                    } 
                   
                    }
               
                    else
                    {
                    text+=repeatString(4)+"\""+tmp2.get(f).getTagName()+"\":{\n";
                    for(int m=0;m<tmp3.size();m++)
                    {
                    tmp4 = get_children(tmp3.get(m));
                    if(tmp4.isEmpty())
                    {
                    text+=repeatString(5)+"\""+tmp3.get(m).getTagName()+"\":"+"\""+tmp3.get(m).getTagValue()+"\",\n";   
                    
                    }
                    else
                    {
                      text+=repeatString(5)+"\""+tmp3.get(m).getTagName()+"\":[\n";  
                        for(int n = 0;n<tmp4.size();n++){
                       if(tmp4.size()>1 && tmp4.get(n).getTagName().equals(tmp4.get(n+1).getTagName())){
                         text+=repeatString(6)+"\""+tmp4.get(n).getTagValue()+"\",\n"+repeatString(6)+"\""+tmp4.get(n+1).getTagValue()+"\"\n"+repeatString(5)+"]\n";
                         text+=repeatString(4)+"}\n";
                         }
                         break;
                        }
                   
                    }
                    }
                    
                    }
                    }
                  text+=repeatString(3)+"]\n";  
                }
           
              }
              text+=repeatString(2)+"]\n";
              text+=repeatString(1)+"}\n}";
             return text;
        }
