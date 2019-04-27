package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
      content[x][y] = new Location(x,y,name,type,roadInformation);
    }
  }
  
  public boolean isValid(Coordinate c)
  {
    return (1<=c.x && c.x<=length && 1<=c.y && c.y<=width);
  }
  
  public Location getLocation(int x,int y)
  {
    return content[x][y];
  }
  
  public Location getLocation(Coordinate pos)
  {
    return content[pos.x][pos.y];
  }
  
  
  public int getLength() {
    return length;
  }

  public void setLength(int length) {
    this.length = length;
  }

  public int getWidth() {
    return width;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  @Override
  public String toString() {
    String result = this.length+" "+this.width;
    return result;
  }
  
  public Location[][] getMap(Coordinate topLeft,Coordinate bottomRight)
  {
    Location[][] result = new Location[bottomRight.x-topLeft.x+1][bottomRight.y-topLeft.y+1];
    for (int i=0; i<bottomRight.x-topLeft.x+1; i++)
      for (int j=0; j<bottomRight.y-topLeft.y+1; j++)
        result[i][j] = content[topLeft.x+i][topLeft.y+j];
    return result;
  }
  
  public static void main(String[] args) throws FileNotFoundException, IOException, ParseException
  {
    GameMap smallMap = new GameMap("."+File.separator+"database"+File.separator+"smallMap.json");
    System.out.println(smallMap);
    PathFinding pathFinder = new PathFindingBFS(smallMap);
    System.out.println(pathFinder.evaluateCost(new Coordinate(3,1), new Coordinate(1,3)));
    User user = new User("bvd",100);
    pathFinder.buildRoad(user, new Coordinate(3,1), new Coordinate(1,3));
    System.out.println(user.getMoney());
  }
  
}
