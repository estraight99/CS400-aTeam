package application;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import org.json.simple.JSONArray; 
import org.json.simple.JSONObject; 
import org.json.simple.parser.*; 

public class GameMap {
  int n,m;
  Location[][] content;
  
  public GameMap(String pathToJSONFile) throws FileNotFoundException, IOException, ParseException
  {
    // based on https://www.geeksforgeeks.org/parse-json-java/
    Object obj = new JSONParser().parse(new FileReader(pathToJSONFile));
    JSONObject jo = (JSONObject) obj;
    this.n = (int) jo.get("n");
    this.m = (int) jo.get("m");
    content = new Location[n+1][m+1];
    
    JSONArray data = (JSONArray) jo.get("data");
   
    for (Object i : data)
    {
      JSONObject joLevel2 = (JSONObject) i;
      int x = (int) joLevel2.get("x");
      int y = (int) joLevel2.get("y");
      String name = (String) joLevel2.get("name");
      String type = (String) joLevel2.get("type");
      int roadInformation = (int) joLevel2.get("road");
      content[x][y] = new Location(name,type,roadInformation);
    }
  }
  
}
