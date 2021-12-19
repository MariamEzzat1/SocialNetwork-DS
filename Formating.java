
package project;

import java.io.*;
import java.util.Scanner;
import java.util.Stack;
import static project.Gtree.readString;
public class Formating {
 private static String repeatString(int stack) {
     StringBuilder indent = new StringBuilder();
     for (int i = 0; i < stack; i++) {
        indent.append("    ");
     }
     return indent.toString();
}
    
       public static String getPrettyXml(String xml) {
    if (xml == null || xml.trim().length() == 0) return "";

    int stack = 0;
    StringBuilder pretty = new StringBuilder();
    String[] rows = xml.trim().replaceAll(">", ">\n").replaceAll("<", "\n<").split("\n");

    for (int i = 0; i < rows.length; i++) {
        if (rows[i] == null || rows[i].trim().length() == 0) continue;

        String row = rows[i].trim();
        if (row.startsWith("<?")|| row.startsWith("<!")) 
        {
            pretty.append(row + "\n");
        } 
        else if (row.startsWith("</")) 
        {
            String indent = repeatString(--stack);
            if (pretty.charAt(pretty.length()-1) == '\n')
            {
            pretty.append(indent + row + "\n");
            }
            else 
            {   
                pretty.append(row + "\n");
            }
        } 
        else if (row.startsWith("<") && row.endsWith("</") == false)
        {
            String indent = repeatString(stack++);
            pretty.append(indent + row + "\n");
            if (row.endsWith("</")) stack--;
        } 
        else 
        {
            String indent = repeatString(stack);
            pretty.append(indent + row + "\n");
        }
        
    }
    
    return pretty.toString().trim();
}
  
}
    

    

