public class HuffmanTree {
    static String  arr_code[]=new String [128];
     public static void  ShowCode (HuffmanNode x,String code){//recursive function to print code 
        arr_code[x.getchar()]=code;
      if (x.l== null&& x.r== null){
           System.out.println(x.getchar()+ ":" + code);
            return; // base case
       }
       ShowCode (x.l,code+"0"); // left tarversal has value of 0
       ShowCode (x.r,code+"1"); // right traversal has value of 1
   }
public static String encoding(String ABC){
    String Out="";
   for (int i=0;i<ABC.length();i++){
       Out+=arr_code[ABC.charAt(i)];      
   }
    return Out;
}
public static String traverse(String x, HuffmanNode root){
    String out ="Decoded Statement: ";
    HuffmanNode node = root;
    for (int i=0;i<x.length();i++){
        if (x.charAt(i)=='0'){
            node=node.l;
        }
        else 
            node = node.r;
        if (node.l==null && node.r== null){
            out +=node.c;
            node=root;
        }
    }
    return out+'\0';

}
