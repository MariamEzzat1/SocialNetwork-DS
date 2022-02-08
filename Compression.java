import java.util.PriorityQueue;
import static compression.HuffmanTree.ShowCode;
import static compression.HuffmanTree.encoding;
import static compression.HuffmanTree.traverse;
import static compression.Min.minifying;
import static compression.Min.readArray;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Mariem
 */
public class Compression_final {

static String trav=null;
    
    public static String compression (String text){
int index=0; int numOffreq=0;
int [] ASCII=new int[128];

List<Character> letter = new ArrayList<Character>();
int [] freq=new int[128];
for (int i =0;i<text.length();i++){
    char temp=text.charAt(i);

        ASCII[text.charAt(i)]+=1;

}

for (int i =0;i <ASCII.length;i++){
    if (ASCII[i]>0){
        letter.add((char)i);
        freq[index]=ASCII[i];
        index++;
        }  
   }
Character letterarr[]=letter.toArray(new Character[letter.size()]);
for(int i=0;i<freq.length;i++){
    int exx;
   numOffreq++;
    }
 PriorityQueue<HuffmanNode> q = new PriorityQueue<HuffmanNode>(numOffreq++, new NodesComparator());
for (int i =0;i<letterarr.length ;i++){
   HuffmanNode n=new HuffmanNode();
   n.setchar(letterarr[i]); 
   n.setFreq(freq[i]);
   n.l=null; n.r=null;
   q.add(n); 
}
HuffmanNode root = null;
while (q.size()>1){
    HuffmanNode x=q.peek();
    q.poll();
    HuffmanNode y=q.peek();
    q.poll();
    HuffmanNode S=new HuffmanNode();

    S.freq=x.freq+y.freq;
   S.l=x;S.r=y;
   root=S;
   q.add(S);
    }
//System.out.println("Each character and its corresponding huffmancode is listed below: ");
ShowCode(root,"");
String x=encoding(text); 


//System.out.println(traverse(x,root));


 trav=traverse(x,root);
return (encoding(text));
} 
    public static void main(String[] args) {
         //TODO code application logic here
String [] array = readArray("C:\\Users\\El.Takwa\\Downloads\\sample.xml");
String text=minifying(array);
String compressed=compression (text);
System.out.println("Encoded Statement: "+compressed);
System.out.println (trav);
System.out.println("Size before: "+arrSize(array)+" bytes");
int size_af=Size(compression(text));
System.out.println("Size after: "+ size_af+" bytes");

}
//function to calculate size of string in bytes "after compression"
static int  Size(String x)
       {
           return (x.length()/8);
       }
 //function to calculate size of array of strings in bytes "before compression"
static int arrSize(String[] x ){
int size=0;
for (int i=0;i<x.length;i++)
    for (int j=0;j<x[i].length();j++)
    {
        size+=1; // every char is 1 byte in size
    }
return size;
}
    }
  
