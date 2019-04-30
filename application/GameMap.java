package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import org.json.simple.JSONArray; 
import org.json.simple.JSONObject; 
import org.json.simple.parser.*; 

public class GameMap {
  int length,width;
  Location[][] content;
  String pathToJSONFile;
  
  public GameMap(String pathToJSONFile) throws FileNotFoundException, IOException, ParseException
  {
    this.pathToJSONFile = pathToJSONFile;
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
      //String name = (String) joLevel2.get("name");
      //String type = (String) joLevel2.get("type");
      int typeInformation = ((Long) joLevel2.get("type")).intValue();
      int roadInformation = ((Long) joLevel2.get("road")).intValue();
      content[x][y] = new Location(x,y,"",roadInformation,typeInformation);
    }
    
  }
  
  public boolean isValid(Coordinate c)
  {
    return (1<=c.getX() && c.getX()<=length && 1<=c.getY() && c.getY()<=width);
  }
  
  public Location getLocation(int x,int y)
  {
    return content[x][y];
  }
  
  public Location getLocation(Coordinate pos)
  {
    return content[pos.getX()][pos.getY()];
  }
  
  
  public int getLength() {
    return length;
  }

  public void setLength(int length) {
    this.length = length;
  }

  public int getWidth() {
    return width;
  };

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
    int width = bottomRight.getX()-topLeft.getX();
    int length = bottomRight.getY()-topLeft.getY()+1;
    Location[][] result = new Location[width][length];
    for (int i=0; i<width; i++)
      for (int j=0; j<length; j++)
        result[i][j] = content[topLeft.getX()+i][topLeft.getY()+j];
    return result;
  }
  
  @SuppressWarnings("unchecked")
  public void updateJSONFile() throws FileNotFoundException
  {
    JSONObject jo = new JSONObject();
    jo.put("length", this.length);
    jo.put("width", this.width);
    
    JSONArray data = new JSONArray();
    for (int i=1; i<=length; i++)
      for (int j=1; j<=width; j++)
      {
        JSONObject location = content[i][j].createJSONObject();
        data.add(location);
      }
    
    jo.put("data", data);
    
    PrintWriter pw = new PrintWriter(this.pathToJSONFile); 
    pw.write(jo.toJSONString()); 
      
    pw.flush(); 
    pw.close(); 
    
  }
  
}
