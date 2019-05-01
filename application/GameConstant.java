package application;

import java.io.File;

/**
 * This class contains the constant used in the game.
 * @author Dung Viet Bui
 *
 */
public class GameConstant {
  public static int ROAD_COST = 1; // the cost for each new road
  static int[] mx = new int[] {-1, 0, 1, 0}; // x axis change for moveMap operation: up, left, down, right
  static int[] my = new int[] {0, -1, 0, 1}; // y axis change for moveMap operation: up, left, down, right
  // file path for each size of map
  static final String smallPath = "."+File.separator+"database"+File.separator+"smallMap.json"; 
  static final String mediumPath = "."+File.separator+"database"+File.separator+"mediumMap.json";
  static final String bigPath = "."+File.separator+"database"+File.separator+"bigMap.json";
}
