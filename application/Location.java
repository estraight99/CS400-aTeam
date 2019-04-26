package application;

public class Location {
  int x,y;
  String type;
  String name;
  int roadInformation;
  public Location(int x,int y,String type,String name,int roadInformation)
  {
    this.x = x;
    this.y = y;
    this.type = type;
    this.name = name;
    this.roadInformation = roadInformation;
  }
  public int getX() {
    return x;
  }
  public void setX(int x) {
    this.x = x;
  }
  public int getY() {
    return y;
  }
  public void setY(int y) {
    this.y = y;
  }
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public int getRoadInformation() {
    return roadInformation;
  }
  public void setRoadInformation(int roadInformation) {
    this.roadInformation = roadInformation;
  }
  
}
