package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * 
 * @author Dung Viet Bui
 *
 */
public class MapGenerator {
  @SuppressWarnings("unchecked")
  public static void generateMap(String pathToJSONFile,int length,int width) throws FileNotFoundException
  {
    JSONObject jo = new JSONObject();
    jo.put("length", length);
    jo.put("width", width);
    
    Random rand = new Random();
    JSONArray data = new JSONArray();
    for (int i=1; i<=length; i++)
      for (int j=1; j<=width; j++)
      {
        int haveHouse = rand.nextInt(9);
        JSONObject location;
        if (haveHouse==1)
          location = (new Location(i,j,"",0,rand.nextInt(9))).createJSONObject();
        else
          location = (new Location(i,j,"",0,0)).createJSONObject();
          
        data.add(location);
      }
    
    jo.put("data", data);
    
    PrintWriter pw = new PrintWriter(pathToJSONFile); 
    pw.write(jo.toJSONString()); 
      
    pw.flush(); 
    pw.close(); 
  }
  
  public static void main(String[] args) throws FileNotFoundException
  {
    generateMap(GameConstant.smallPath,10,10);
    generateMap(GameConstant.mediumPath,100,100);
    generateMap(GameConstant.bigPath,1000,1000);
    System.out.println("Done!");
  }
}
