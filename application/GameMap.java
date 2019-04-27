package application;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import org.json.simple.JSONArray; 
import org.json.simple.JSONObject; 
import org.json.simple.parser.*; 

public class GameMap {
  int length,width;
  Location[][] content;
  
  public GameMap(String pathToJSONFile) throws FileNotFoundException, IOException, ParseException
  {
    // based on https://www.geeksforgeeks.org/parse-json-java/
    Object obj = new JSONParser().parse(new FileReader(pathToJSONFile));
    JSONObject jo = (JSONObject) obj;
    this.length = ((Long) (jo.get("length"))).intValue();
    this.width = ((Long) (jo.get("width"))).intValue();
    content = new Location[length+1][width+1];
    
    JSONArray data = (JSONArray) jo.get("data");
   
    for (Object i : data)
    {
      JSONObject joLevel2 = (JSONObject) i;
      int x = ((Long) joLevel2.get("x")).intValue();
      int y = ((Long) joLevel2.get("y")).intValue();
      String name = (String) joLevel2.get("name");
      String type = (String) joLevel2.get("type");
      int roadInformation = ((Long) joLevel2.get("road")).intValue();
      content[x][y] = new Location(name,type,roadInformation);
    }
  }
  
  @Override
  public String toString() {
    String result = this.length+" "+this.width;
    return result;
  }
  
  
  public static void main(String[] args) throws FileNotFoundException, IOException, ParseException
  {
    GameMap smallMap = new GameMap("."+File.separator+"database"+File.separator+"smallMap.json");
    System.out.println(smallMap);
  }
  
}
