package application;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This class is used to test the Path Finding algorithm used in the program
 * 
 * @author Dung Viet Bui, Yuanbo Zhang, Eli Straight
 *
 */
class PathFindingTest {

  PathFinding bestPathFinder;
  User user;

  @BeforeAll
  static void setUpBeforeClass() throws Exception {}

  @AfterAll
  static void tearDownAfterClass() throws Exception {}

  @BeforeEach
  void setUp() throws Exception {}

  @AfterEach
  void tearDown() throws Exception {}

  /**
   * This test tests the PathFinder in the case that there is a cross at the middle of the map (as
   * shown in the sample input in the xTeam Proposal)
   */
  @Test
  void test001_simple_cross() {
    bestPathFinder = new PathFindingBFS(new GameMap(3, 3));
    user = new User("bvd", 100);
    bestPathFinder.buildRoad(user, new Coordinate(1, 1), new Coordinate(1, 2));
    bestPathFinder.buildRoad(user, new Coordinate(1, 2), new Coordinate(3, 2));
    bestPathFinder.buildRoad(user, new Coordinate(1, 1), new Coordinate(2, 1));
    bestPathFinder.buildRoad(user, new Coordinate(2, 1), new Coordinate(2, 3));
    bestPathFinder.buildRoad(user, new Coordinate(3, 1), new Coordinate(1, 3));
    if (user.getMoney() != 92)
      fail("After building all these path, money should be " + 92 + ", but money="
          + user.getMoney());
  }

  /**
   * This test tests the PathFinder in the "staircase" case (there are 3 pre-built paths, the path
   * finder need to take advantage of the longest one the build the 4th path)
   */
  @Test
  void test002_staircase() {
    bestPathFinder = new PathFindingBFS(new GameMap(10, 10));
    user = new User("bvd", 100);
    bestPathFinder.buildRoad(user, new Coordinate(2, 1), new Coordinate(2, 2));
    bestPathFinder.buildRoad(user, new Coordinate(3, 1), new Coordinate(3, 5)); // the longest one
    bestPathFinder.buildRoad(user, new Coordinate(4, 1), new Coordinate(4, 3));
    bestPathFinder.buildRoad(user, new Coordinate(1, 1), new Coordinate(5, 5));
    if (user.getMoney() != 89)
      fail("After building all these path, money should be " + 89 + " , but money="
          + user.getMoney());
  }

  /**
   * This test tests if the PathFinder throws the right Exception when the input integers are larger
   * than the given range.
   */
  @Test
  void test003_too_big_coordinates() {
    bestPathFinder = new PathFindingBFS(new GameMap(100, 100));
    user = new User("bvd", 100);
    try
    {
      bestPathFinder.buildRoad(user, new Coordinate(1,1000), new Coordinate(10,10));
      fail("No exception was thrown");
    }
    catch (IllegalArgumentException e)
    {
      // do nothing
    }
    catch (Exception e)
    {
      fail("IllegalArgumentException was not thrown");
    }
  }
  
  /**
   * This test tests if the PathFinder throws the right Exception when the input integers are smaller
   * than the given range.
   */
  @Test
  void test004_too_small_coordinates()
  {
    bestPathFinder = new PathFindingBFS(new GameMap(100, 100));
    user = new User("bvd", 100);
    try
    {
      bestPathFinder.buildRoad(user, new Coordinate(1,100), new Coordinate(10,0));
      fail("No exception was thrown");
    }
    catch (IllegalArgumentException e)
    {
      // do nothing
    }
    catch (Exception e)
    {
      fail("IllegalArgumentException was not thrown");
    }
  }
  
  /**
   * This test tests if the PathFinder can throws an exception when the User does not have enough money
   */
  @Test
  void test005_no_money_exception()
  {
    bestPathFinder = new PathFindingBFS(new GameMap(100,100));
    user = new User("bvd",100);
    try
    {
      bestPathFinder.buildRoad(user, new Coordinate(1,1), new Coordinate(100,100));
      fail("No exception was thrown");
    }
    catch (NotEnoughMoneyException e)
    {
      // do nothing
    }
    catch (Exception e)
    {
      fail("A wrong exception was thrown");
    }
  }
  
  /**
   * This test is used to test the speed of the PathFinder
   */
  @Test
  void test006_speed_test()
  {
    bestPathFinder = new PathFindingBFS(new GameMap(10000,1000));
    user = new User("bvd",(int) 1e9);
    bestPathFinder.buildRoad(user, new Coordinate(1,1), new Coordinate(10000,1000));
    if (user.getMoney()==(int) 1e9)
      fail("The money of the user does not decrease!");
  }
}
