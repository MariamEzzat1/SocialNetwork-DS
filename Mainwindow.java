public class Main {
   public static void main (String[] args) {
    //reading xml file
  String text = readString("C:/Users/Salma/Desktop/xmlfile.txt");
  String [] arr = readArray("C:\\Users\\Salma\\Documents\\NetBeansProjects\\project\\src\\project\\Xml.xml");
  String[] array = readArray("C:/Users/Salma/Desktop/xmlfile.txt"); 
  
  //formating
  String format = getPrettyXml(text);
  System.out.println(format);
  
//check consistency
String consistency = Consistency.CheckConsistent(arr);
  System.out.println(consistency);
  
//check error
String format1 = getPrettyXml(text);
String [] array1 = format.split("\n");
String error = checkError.CheckError(array1);
System.out.println(error);

//minifying  
String out = minifying (array);
System.out.println(out);

//convertToJSON
String format = getPrettyXml(text);
String [] array = format.split("\n");
JSONtree tree = new JSONtree();
String json = tree.ConvertTOJSON(array);
System.out.println(json);

}

//compression
String out1 = minifying (array);
String comp = compression(out1);
 }   
}
