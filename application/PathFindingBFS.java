package application;

import java.util.ArrayList;
import java.util.LinkedList;

public class PathFindingBFS implements PathFinding{
  static int[] mx = new int[]{-1,0,1,0};
  static int[] my = new int[]{0,-1,0,1};
  static final int INF = (int) 1e9;
  
  GameMap map;
  int[][] dis;
  Location[][] trace;
  
  PathFindingBFS(GameMap map)
  {
    this.map = map;
  }

  private int getManhattanDistance(Coordinate A,Coordinate B)
  {
    return Math.abs(A.x-B.x)+Math.abs(A.y-B.y);
  }
  
  private boolean onShortestPath(Coordinate start,Coordinate finish,Coordinate middle)
  {
    return (getManhattanDistance(start,middle)+getManhattanDistance(middle,finish)==getManhattanDistance(start,finish));
  }
  
  @Override
  public int evaluateCost(Coordinate start, Coordinate finish) {
    dis = new int[map.getLength()+1][map.getWidth()+1];
    trace = new Location[map.getLength()+1][map.getWidth()+1];
    for (int i=1; i<=map.getLength(); i++)
      for (int j=1; j<=map.getWidth(); j++)
        dis[i][j] = PathFindingBFS.INF;
    
    LinkedList<Location> queue = new LinkedList<Location>();
    dis[start.x][start.y] = 0;
    queue.addFirst(map.getLocation(start));
    do
    { 
      Location current = queue.getFirst();
      queue.removeFirst();
      for (Direction i : Direction.values())
      {
        Coordinate newLocation = new Coordinate(current.getX()+mx[i.ordinal()],current.getY()+my[i.ordinal()]);
        if (map.isValid(newLocation) && onShortestPath(start,finish,newLocation))
        {
          int tmpCost = current.getRoadCostWithDirection(i.ordinal());
          if (dis[newLocation.x][newLocation.y]>dis[current.getX()][current.getY()]+tmpCost)
          {
            dis[newLocation.x][newLocation.y]=dis[current.getX()][current.getY()]+tmpCost;
            trace[newLocation.x][newLocation.y]=current; 
            if (tmpCost==0)
              queue.addFirst(map.getLocation(newLocation));
            else
              queue.addLast(map.getLocation(newLocation));
          }
        }
      }
    }
    while (!queue.isEmpty());
    
    return dis[finish.x][finish.y];
  }

  @Override
  public void buildRoad(User user,Coordinate start, Coordinate finish) {
    this.evaluateCost(start, finish);
    Location current = map.getLocation(finish);
    ArrayList<Location> result = new ArrayList<>();
    do
    {
      result.add(current);
      current = trace[current.getX()][current.getY()];
    }
    while (current!=null);
    
    for (int i=0; i+1<result.size(); i++)
      result.get(i).buildRoadTo(user,result.get(i+1));
  }
}
