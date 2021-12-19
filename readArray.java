public static String[] readArray(String file){
int ctr=0;
         try{
         Scanner myfile=new Scanner(new File(file));
         while(myfile.hasNext()){
             ctr =ctr+1;
             myfile.nextLine();  
         }
         String[] words = new String[ctr];
         Scanner s2 = new Scanner (new File(file));
         for(int i=0;i<ctr;i++){
            if(s2.hasNext())
            {words[i] = s2.nextLine();
         
            }
         }
         return words;
         }
         catch(FileNotFoundException e){
         }
         return null;
}
