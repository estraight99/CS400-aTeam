package application;

public class Coordinate {
  
  public int x,y;
  Coordinate(int x,int y)
  {
    this.x = x;
    this.y = y;
  }
  
  @Override
  public boolean equals(Object x)
  {
    if (!(x instanceof Coordinate))
      return false;
    Coordinate other = (Coordinate) x;
    return (this.x==other.x && this.y==other.y);
  }
  
}
