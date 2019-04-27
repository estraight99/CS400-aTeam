package application;

public class Coordinate {
  static int[] mx = new int[]{-1,0,1,0};
  static int[] my = new int[]{0,-1,0,1};
  
  int x,y;
  Coordinate(int x,int y)
  {
    this.x = x;
    this.y = y;
  }
  
  Coordinate nextCoordinate(Direction direction)
  {
    return new Coordinate(x+mx[direction.ordinal()],y+my[direction.ordinal()]);
  }
}
