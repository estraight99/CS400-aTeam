package application;

public class Location {
  String type;
  String name;
  int roadInformation;
  public Location(String type,String name,int roadInformation)
  {
    this.type = type;
    this.name = name;
    this.roadInformation = roadInformation;
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
