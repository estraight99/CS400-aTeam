package application;

/**
 * 
 * @author Dung Viet Bui
 *
 */
public interface PathFinding {
  public int evaluateCost(Coordinate start,Coordinate finish);
  public void buildRoad(User user,Coordinate start,Coordinate finish);
}
