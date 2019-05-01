package application;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * This class implements the BFS 0-1 algorithm used to find the cheapest-shortest path to build the
 * roads.
 * 
 * @author Dung Viet Bui
 *
 */
public class PathFindingBFS implements PathFinding {

  static final int INF = (int) 1e9; // infinity variable, used to mark unvisited vertices

  private GameMap map; // the map of the Game
  private int[][] dis; // the distance array used to mark distance between the start and the point
  private Location[][] trace; // this array is used to recover the optimal path

  /**
   * This method initializes a PathFinding instance for the given map
   * 
   * @param map is the map of the Game
   */
  public PathFindingBFS(GameMap map) {
    this.map = map;
  }

  /**
   * This method calculates the length of the shortest path between two locations
   * 
   * @param A is the first location
   * @param B is the second location
   * @return the length of the shortest path between two locations
   */
  private int getManhattanDistance(Coordinate A, Coordinate B) {
    return Math.abs(A.getX() - B.getX()) + Math.abs(A.getY() - B.getY());
  }

  /**
   * This method checks if there is the point middle is on a shortest path between start and finish.
   * In other words, the method determines whether there should be a directed edge to middle in the
   * implicit graph
   * 
   * @param start  is the starting point
   * @param finish is the ending point
   * @param middle is a point in the middle of the above points
   * @return true if middle is on the shortest path between start and finish, otherwise false.
   */
  private boolean onShortestPath(Coordinate start, Coordinate finish, Coordinate middle) {
    return (getManhattanDistance(start, middle)
        + getManhattanDistance(middle, finish) == getManhattanDistance(start, finish));
  }

  @Override
  public int evaluateCost(Coordinate start, Coordinate finish) {
    // the algorithm is presented in README
    if (!map.isValid(start) || !map.isValid(finish))
      return 0;
    dis = new int[map.getLength() + 1][map.getWidth() + 1];
    trace = new Location[map.getLength() + 1][map.getWidth() + 1];
    for (int i = 1; i <= map.getLength(); i++)
      for (int j = 1; j <= map.getWidth(); j++)
        dis[i][j] = PathFindingBFS.INF;

    LinkedList<Location> queue = new LinkedList<Location>();
    dis[start.getX()][start.getY()] = 0;
    queue.addFirst(map.getLocation(start));
    do {
      Location current = queue.getFirst();
      queue.removeFirst();
      for (int i = 0; i < 4; i++) {
        Coordinate newLocation = new Coordinate(current.getX() + GameConstant.mx[i],
            current.getY() + GameConstant.my[i]);
        if (map.isValid(newLocation) && onShortestPath(start, finish, newLocation)) {
          int tmpCost = current.getRoadCostWithDirection(i);
          if (dis[newLocation.getX()][newLocation.getY()] > dis[current.getX()][current.getY()]
              + tmpCost) {
            dis[newLocation.getX()][newLocation.getY()] =
                dis[current.getX()][current.getY()] + tmpCost;
            trace[newLocation.getX()][newLocation.getY()] = current;
            if (tmpCost == 0)
              queue.addFirst(map.getLocation(newLocation));
            else
              queue.addLast(map.getLocation(newLocation));
          }
        }
      }
    } while (!queue.isEmpty());

    return dis[finish.getX()][finish.getY()];
  }

  @Override
  public void buildRoad(User user, Coordinate start, Coordinate finish) {
    if (!map.isValid(start) || !map.isValid(finish))
      throw new IllegalArgumentException(
          "The coordinates need to be positive integer from 1 to " + map.getLength() + "!");

    if (user.getMoney() < this.evaluateCost(start, finish))
      throw new NotEnoughMoneyException();

    Location current = map.getLocation(finish);
    List<Location> result = new ArrayList<>();

    do {
      result.add(current);
      current = trace[current.getX()][current.getY()];
    } while (current != null);

    for (int i = 0; i + 1 < result.size(); i++)
      result.get(i).buildRoadTo(user, result.get(i + 1));
  }
}
