package application;

/**
 * This class represents a Coordinate of a Location on the Map of the Game
 * @author Dung Viet Bui
 */
public class Coordinate {
  
  private int x,y;
  
  /**
   * This method creates an instance of Coordinate with the given x and y
   * @param x is the vertical coordinate of the Location
   * @param y is the horizontal coordinate of the Location
   */
  public Coordinate(int x,int y)
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

  @Override
  public String toString() {
    return "Coordinate [x=" + x + ", y=" + y + "]";
  }

  /**
   * This method returns the x-coordinate of this location
   * @return the x-coordinate
   */
  public int getX() {
    return x;
  }

  /**
   * This method returns the y-coordinate of this location
   * @return the y-coordinate
   */
  public int getY() {
    return y;
  }
  
  
  
  
}
