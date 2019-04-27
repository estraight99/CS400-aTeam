package application;

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
}
