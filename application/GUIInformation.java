package application;

import java.io.FileNotFoundException;

public class GUIInformation {
  User user;
  GameMap map;
  Coordinate topLeft;
  GUIInformation(User user, GameMap map,Coordinate topLeft)
  {
    this.user = user;
    this.map = map;
    this.topLeft = topLeft;
  }
  
  public void updateJSONFile() throws FileNotFoundException
  {
    user.updateJSONFile();
    map.updateJSONFile();
  }
}
