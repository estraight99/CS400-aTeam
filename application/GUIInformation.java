package application;

import java.io.FileNotFoundException;

public class GUIInformation {
  static int[] mx = new int[]{-1,0,1,0};
  static int[] my = new int[]{0,-1,0,1};
  
  User user;
  GameMap map;
  Coordinate topLeft;
  GUIInformation(User user, GameMap map,Coordinate topLeft)
  {
    this.user = user;
    this.map = map;
    this.topLeft = topLeft;
  }
  
  private boolean ok(Coordinate newCoor)
  {
    return (1<=newCoor.x && newCoor.x<=map.length && 1<=newCoor.y && newCoor.y<=map.width);
  }
  
  public void moveMap(int direction)
  {
    Coordinate result = new Coordinate(topLeft.x+mx[direction],topLeft.y+my[direction]);
    if (ok(result))
      this.topLeft = result;
  }
  
  public void updateJSONFile() throws FileNotFoundException
  {
    user.updateJSONFile();
    map.updateJSONFile();
  }
}
