import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.util.mxCellRenderer;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.image.BufferedImage;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.xml.parsers.ParserConfigurationException;
import static junit.runner.Version.id;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import static org.junit.Assert.assertTrue;
import org.xml.sax.SAXException; 
 
 public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        // TODO code application logic here
        DefaultDirectedGraph<String, DefaultEdge> g = new DefaultDirectedGraph<String, DefaultEdge>(DefaultEdge.class);
      DocumentBuilderFactory factory =  DocumentBuilderFactory.newInstance();
      try{
      DocumentBuilder builder = factory.newDocumentBuilder();
      Document document = builder.parse(new File("C:\\Users\\Maria\\Downloads\\sample.xml"));
      document.getDocumentElement().normalize();
      NodeList userlist=document.getElementsByTagName("user");
      for(int i=0;i<userlist.getLength();i++){
      Node user=userlist.item(i);
       Element userElement=(Element)user;
      String id= userElement.getElementsByTagName("id").item(0).getTextContent();
      //vertix
      if(!g.containsVertex(id)){g.addVertex(id);}
       if(user.getNodeType()==Node.ELEMENT_NODE)
      {
         String []arr= userElement.getElementsByTagName("followers").item(0).getTextContent().split("\n");
         for(int k=0;k<arr.length;k++){
         if(arr[k].trim().length()==0)
             continue;
         else
             System.out.println(arr[k].trim());
         if(!g.containsVertex(arr[k].trim())) 
         {if(!g.containsEdge(id, arr[k].trim()))
                g.addVertex(arr[k].trim());}
                                                  
               g.addEdge(id, arr[k].trim());
         
         

      }
      
      
      }
      File imgFile = new File("C:\\Users\\Maria\\Downloads\\graph.png");
    imgFile.createNewFile();

    JGraphXAdapter<String, DefaultEdge> graphAdapter = 
            
      new JGraphXAdapter<String, DefaultEdge>(g);
    mxIGraphLayout layout = new mxCircleLayout(graphAdapter);
    layout.execute(graphAdapter.getDefaultParent());
    
    BufferedImage image = mxCellRenderer.createBufferedImage(graphAdapter, null, 2, Color.WHITE, true, null);
 
    ImageIO.write(image, "PNG", imgFile);
    assertTrue(imgFile.exists());
     Desktop dt = Desktop.getDesktop();
    dt.open(imgFile);
      }
      
      }
      
      catch(ParserConfigurationException | SAXException | IOException e){}

    } 
}
}
