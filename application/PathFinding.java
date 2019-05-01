package application;

/**
 * This interface provides a structure for user to define their own algorithm
 * for pathFinding
 * 
 * @author Dung Viet Bui
 *
 */
public interface PathFinding {
  /**
   * This method analyze the cost from starting pointer to destination
   */
  public int evaluateCost(Coordinate start,Coordinate finish);
  /**
   * This method build the road on the map
   * @param user is the user running the program
   * @param start is the starting point
   * @param finish is the destinaiton
   */
  public void buildRoad(User user,Coordinate start,Coordinate finish);
}
