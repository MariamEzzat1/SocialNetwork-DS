package project;

import java.util.ArrayList;
import static project.Formattig.getPrettyXml;
import static project.Reading.readArray;
import static project.Reading.readString;

public class JSONtree {
    // node class 
 public class Node{
 
       private String tagName;
       private String tagValue;
       private String tagAttributes;
       private boolean closingTag;
       private int depth;
       private ArrayList<Node> children= new ArrayList<Node>();
       private Node parent ;
       public Node() {
           tagName = null;
           tagValue = null;
           tagAttributes = null;
           closingTag = false;
           depth = 0;
           parent=null;
           
        }

        public Node(String name,String Attribute,String value,boolean close,int depth, Node parent ) {
            this.children = new ArrayList<>();
            this.tagName = name;
            this.tagAttributes=Attribute;
            this.tagValue=value;
            this.closingTag=close;
            this.depth=depth;
            this.parent=parent;
        }

        public String getTagName() {
            return tagName;
        }

        public void setTagName(String tagName) {
            this.tagName = tagName;
        }

        public String getTagValue() {
            return tagValue;
        }

        public void setTagValue(String tagValue) {
            this.tagValue = tagValue;
        }

        public String getTagAttributes() {
            return tagAttributes;
        }

        public void setTagAttributes(String tagAttributes) {
            this.tagAttributes = tagAttributes;
        }

        public boolean isClosingTag() {
            return closingTag;
        }

        public void setClosingTag(boolean closingTag) {
            this.closingTag = closingTag;
        }

        public int getDepth() {
            return depth;
        }

        public void setDepth(int depth) {
            this.depth = depth;
        }
        
         public Node addChild(Node child) {
             child.setParent(this);
             this.children.add(child);
             return child;
         }
 
      public void addChildren(ArrayList<Node> children) {
        children.forEach(each -> each.setParent(this));
        this.children.addAll(children);
         }
 
 
     public ArrayList<Node> getChildren() {
     return children ;
     }

 
private void setParent(Node parent) {
this.parent = parent;
}
 
public Node getParent() {
return parent;
}
 
public Node getRoot( ) {
        if( parent == null){
             return this;
         }
        return parent.getRoot();
       }
}
    
  private Node root;

    public JSONtree() 
    {
        root = null;
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
    public ArrayList <Node> get_children (Node node)
    {
    ArrayList <Node> temp = new ArrayList <>() ;
    int n =  root.children.indexOf(node) ;
    int d = root.children.get(n).getDepth();
         for (int j = n + 1; j<root.children.size()-1;j++)
         {
             if((root.children.get(n).getDepth()!= root.children.get(j).getDepth()))
             {         
              temp.add(root.children.get(j));
             }
             else 
             {
             return temp;
             }
         }
         //if(root.children.get(n+1).getDepth()== d+1)
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
    
// Converting the Xml file to JSON    
public String ConvertTOJSON (String []arr){
    ArrayList <Node> tmp,tmp2 = new ArrayList<>();
    Node root = Insert(arr);
    boolean flag = false;
    int counter = 0;
    String text = "{\n"+"\"" + root.getTagName()+":\"{\n";

    for (int i = 0 ;i< arr.length;i++) 
    {  
        flag = false;
        counter = 0;
        for (int j = i+1; j< root.children.size();j++)
        {
             String name = root.children.get(i).getTagName();

             if(((root.children.get(j).getTagName()).equals(name)) && (root.children.get(i).getDepth() == root.children.get(j).getDepth()))
             {
             flag = true;
             counter ++;
             
                 if(j == i+1)
                 {
                  text += "\"" + root.children.get(i).getTagValue()+"\",\n"+"\""+ root.children.get(j).getTagValue()+"\"";
             
                 }
                 else 
                 {
                 text += ",\n" +"\"" + root.children.get(j).getTagValue()+"\"";
                
                 }   
                  text += "\n},\n";
                 
                 text += "}\n";
             }
             
             }

         if (flag == false)
         {
         text += "\"" + root.children.get(i).getTagName()+ "\":";
         tmp = get_children(root.children.get(i));
         //System.out.println("sandra");

              if(tmp.size() == 0)
              {
              text += "\"" + root.children.get(i).getTagValue()+"\",\n";
             //  System.out.println("mama");
              }         
             else 
              {
              text += "{\n";
              }                        
         } 
               if(flag == true) 
        { 
            i += counter ;
           break;       }       
    }
     text += "}";
    return text;
   }
