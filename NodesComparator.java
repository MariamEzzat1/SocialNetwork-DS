public class NodesComparator implements Comparator<HuffmanNode>{
    @Override
    public int compare (HuffmanNode x,HuffmanNode y){
    return x.getFreq()- y.getFreq();
    }}
