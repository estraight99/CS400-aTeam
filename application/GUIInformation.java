package application;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.simple.parser.ParseException;

public class GUIInformation {
  static int[] mx = new int[]{-1,0,1,0};
  static int[] my = new int[]{0,-1,0,1};
  
  User user;
  GameMap[] map;
  int mapID;
  Coordinate topLeft;
  
  GUIInformation(User user,int initMapID, Coordinate topLeft) throws FileNotFoundException, IOException, ParseException
  {
    this.user = user;
    this.map = new GameMap[3];
    this.map[0] = new GameMap(MapGenerator.smallPath);
    this.map[1] = new GameMap(MapGenerator.mediumPath);
    this.map[2] = new GameMap(MapGenerator.bigPath);
    this.mapID = initMapID;
    this.topLeft = topLeft;
  }
  
  public GameMap getMap()
  {
    return this.map[this.mapID]; 
  }
  
  public void changeMap(int id)
  {
    this.mapID = id;
  }
  
  private boolean ok(Coordinate newCoor)
  {
    return (1<=newCoor.x && newCoor.x<=Math.max(1, getMap().length-9) && 1<=newCoor.y && newCoor.y<=Math.max(1,getMap().width-9));
  }
  
  public void moveMap(int direction)
  {
    Coordinate result = new Coordinate(topLeft.x+mx[direction],topLeft.y+my[direction]);
    //System.out.println((map.length-9)+" "+(map.width-9));
    if (ok(result))
      this.topLeft = result;
  }
  
  public void updateJSONFile() throws FileNotFoundException
  {
    user.updateJSONFile();
    for (int i=0; i<map.length; i++)
      map[i].updateJSONFile();
  }
}
