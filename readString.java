public static String readString (String file)
{
String text = "";
        try
         {
            Scanner s = new Scanner (new File(file));
            while(s.hasNext()){
            text = text + s.nextLine()+ " ";
        }
        }
catch (FileNotFoundException e){
System.out.println("file not found");
}
return text;
}
