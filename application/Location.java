package application;

import org.json.simple.JSONObject;

/**
 * This class represents one location (block) of the map
 * 
 * @author Dung Viet Bui
 *
 */
public class Location {
  private Coordinate coordinate; // coordinate of this location
  private int type; // type of this location
  private int roadInformation; // road information shows every road that connects to this location

  /**
   * This method initialize a location on the map
   * @param x is the x coordinate of this location
   * @param y is the y coordinate of this location
   * @param name is the name of this location
   * @param roadInformation shows all adjacent roads
   * @param typeInformation is the type of this location
   */
  public Location(int x, int y, int roadInformation,int typeInformation) {
    this.coordinate = new Coordinate(x, y);
    this.type = typeInformation;
    this.roadInformation = roadInformation;
  }

  /**
   * getter method
   * @return x coordinate
   */
  public int getX() {
    return coordinate.getX();
  }

  /**
   * getter method
   * @return y coordinate
   */
  public int getY() {
    return coordinate.getY();
  }

  /**
   * getter method
   * @return cost from this block to an adjacent block based on the direction and road information
   */
  public int getRoadCostWithDirection(int direction) {
    return 1 - ((roadInformation >> direction) & 1);
  }

  /**
   * helper method for buildRoad
   */
  private void buildRoadWithDirection(int direction) {
    this.roadInformation |= (1 << direction);
  }

  /**
   * build the road, update the road information
   * @param direction of the new road
   */
  public void buildRoad(int direction) {
    this.buildRoadWithDirection(direction);
  }

  /**
   * getter method
   * @return the road information
   */
  public int getRoadInformation() {
    return roadInformation;
  }

  /**
   * setter method
   * @param new road information
   */
  public void setRoadInformation(int roadInformation) {
    this.roadInformation = roadInformation;
  }

  /**
   * Override equals method
   * @return result of comparison
   */
  @Override
  public boolean equals(Object x) {
    if (!(x instanceof Location))
      return false;
    Location other = (Location) x;
    return (other.coordinate.equals(this.coordinate));
  }

  /**
   * This method returns the direction from this location to the provided location
   * @param provided location
   * @return the direction from this location to the provided location, -1 if fail
   */
  private int getDirectionTo(Location location) {
    for (int i = 0; i < 4; i++) {
      int nx = this.getX() + GameConstant.mx[i];
      int ny = this.getY() + GameConstant.my[i];
      if (location.getX() == nx && location.getY() == ny)
        return i;
    }
    return -1;
  }

  /**
   * Helper method for buildRoadTo method
   * @param user is the user currently using this program
   * @param location is the provided location
   */
  protected void buildRoadToOneDirection(User user, Location location) {
    int direction = getDirectionTo(location);
    this.buildRoad(direction);
  }

  /**
   * This method create a road from this location to another provided location
   * @param user is the user currently using this program
   * @param location is the provided location
   */
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

  /**
   * getter method
   * @return the type of this location
   */
  public int getTypeInformation() {
    return this.type;
  }

}
