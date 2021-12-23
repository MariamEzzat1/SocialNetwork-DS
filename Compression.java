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
public class Compression {

    /**
     * @param args the command line arguments
     */
    public static void write_file(String s){
     try {
            File myObj = new File("compressed.txt");
            if (myObj.createNewFile()) {
        System.out.println("File created: " + myObj.getName());
      } else {
        System.out.println("File already exists.");
      }
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();}
     try{
            FileWriter writer = new FileWriter("compressed.txt", true);
            writer.write(s);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
 
    }
      
    public static string compression (String text){
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
ShowCode(root,"");
String x=encoding(text); 
//write_file(x);
return ("Encoded statement: "+"\n"+encoding(text)+"\n"+traverse(x,root ));
} 
 public static String printFileSize(String fileName) {
       String x = null,y = null;

        Path path = Paths.get(fileName);

        try {
            long bytes = Files.size(path);
             x=String.format("%,d bytes", bytes);
           y=String.format("%,d kilobytes", bytes / 1024);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return (x+"\n"+y);
    }
