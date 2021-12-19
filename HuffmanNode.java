public class HuffmanNode {
    
     int freq; 
    char c;
    HuffmanNode l;
    HuffmanNode r;
  public HuffmanNode(){}
    public HuffmanNode (char c,int f){
     freq=f;
     this.c=c;
    
    }
    public void setFreq(int freq){this.freq=freq;}
     public void setchar(char c){this.c=c;}
   public int getFreq(){return this.freq;}
     public  char getchar(){return this.c;}
    
}
