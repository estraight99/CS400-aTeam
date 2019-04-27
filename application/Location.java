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
  
  
  private boolean getRoadWithDirection(int direction)
  {
    return (((roadInformation>>direction) & 1)==1);
  }
  
  private void buildRoadWithDirection(int direction)
  {
    this.roadInformation|=(1<<direction);
  }
  
  public void buildRoad(User user, int direction)
  {
    if (!this.getRoadWithDirection(direction))
    {
      user.pay(GameConstant.ROAD_COST);
      this.buildRoadWithDirection(direction);
    }
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
  
  
  @Override
  public String toString() {
    return "Location [type=" + type + ", name=" + name + ", roadInformation=" + roadInformation
        + "]";
  }
  
}
