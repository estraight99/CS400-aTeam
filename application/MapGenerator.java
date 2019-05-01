package application;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * This class represents a map generator: reading from a JSON file and creates the map
 * 
 * @author Dung Viet Bui
 *
 */
public class MapGenerator {
	
	/**
     * This method generate a map based on provided JSON file and size of the map
     * @param pathToJSONFile is the path to the JSON file of the map
     * @param length is the length of the map, or how many rows
     * @param width is the width of the map, or how many columns
     * @throws FileNotFoundException is never thrown if no file is missing
     */
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
  
  /**
   * This method generates all three maps for the game
   */
  public static void main(String[] args) throws FileNotFoundException
  {
    generateMap(GameConstant.smallPath,10,10);
    generateMap(GameConstant.mediumPath,100,100);
    generateMap(GameConstant.bigPath,1000,1000);
    System.out.println("Done!");
  }
}
