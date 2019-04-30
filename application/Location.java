package application;

import org.json.simple.JSONObject;

/**
 * 
 * @author Dung Viet Bui
 *
 */
public class Location {
  Coordinate coordinate;
  int type;
  String name;
  int roadInformation;

  public Location(int x, int y, String name, int roadInformation,int typeInformation) {
    this.coordinate = new Coordinate(x, y);
    this.type = typeInformation;
    this.name = name;
    this.roadInformation = roadInformation;
  }


  public int getX() {
    return coordinate.getX();
  }

  public int getY() {
    return coordinate.getY();
  }

  public int getRoadCostWithDirection(int direction) {
    return 1 - ((roadInformation >> direction) & 1);
  }

  private void buildRoadWithDirection(int direction) {
    this.roadInformation |= (1 << direction);
  }

  public void buildRoad(int direction) {
    this.buildRoadWithDirection(direction);
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

  @Override
  public boolean equals(Object x) {
    if (!(x instanceof Location))
      return false;
    Location other = (Location) x;
    return (other.coordinate.equals(this.coordinate));
  }


  private int getDirectionTo(Location location) {
    for (int i = 0; i < 4; i++) {
      int nx = this.getX() + GameConstant.mx[i];
      int ny = this.getY() + GameConstant.my[i];
      if (location.getX() == nx && location.getY() == ny)
        return i;
    }
    return -1;
  }

  protected void buildRoadToOneDirection(User user, Location location) {
    int direction = getDirectionTo(location);
    this.buildRoad(direction);
  }

  public void buildRoadTo(User user, Location location) {
    int direction = getDirectionTo(location);

    if (this.getRoadCostWithDirection(direction) != 0) {
      this.buildRoad(direction);
      location.buildRoad((direction+2)%4);
      user.pay(GameConstant.ROAD_COST);
    }
  }


  @SuppressWarnings("unchecked")
  public JSONObject createJSONObject() {
    JSONObject jo = new JSONObject();
    jo.put("x", this.getX());
    jo.put("y", this.getY());
    jo.put("type", this.type);
    jo.put("road", this.roadInformation);
    //jo.put("name", this.name);
    return jo;
  }


  public int getTypeInformation() {
    return this.type;
  }

}
