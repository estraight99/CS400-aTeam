package application;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

/**
 * This class represents the map of the Game
 * 
 * Dung Viet Bui gets some help from https://www.geeksforgeeks.org/parse-json-java/ for the JSON
 * Parsing part.
 * 
 * @author Dung Viet Bui, Yuanbo Zhang
 *
 */
public class GameMap {
  private int length; // this is the length (also called the vertical length) of the Map
  private int width; // this is the width (the horizontal length) of the Map
  private Location[][] content; // this array stores the Location on the Map
  private String pathToJSONFile; // this is the path to the JSON file of this Map.

  /**
   * This method initializes a GameMap from the information stored in the given JSON file.
   * 
   * @param pathToJSONFile is the path to the JSON file of the map
   * @throws FileNotFoundException is never thrown if no file is missing
   * @throws IOException           is never thrown if no file is missing
   * @throws ParseException        is never thrown if no file is missing
   */
  public GameMap(String pathToJSONFile) throws FileNotFoundException, IOException, ParseException {
    this.pathToJSONFile = pathToJSONFile;
    // based on https://www.geeksforgeeks.org/parse-json-java/
    Object obj = new JSONParser().parse(new FileReader(pathToJSONFile));
    JSONObject jo = (JSONObject) obj;
    this.length = ((Long) (jo.get("length"))).intValue();
    this.width = ((Long) (jo.get("width"))).intValue();
    content = new Location[length + 1][width + 1];

    JSONArray data = (JSONArray) jo.get("data");

    for (Object i : data) {
      JSONObject joLevel2 = (JSONObject) i;
      int x = ((Long) joLevel2.get("x")).intValue();
      int y = ((Long) joLevel2.get("y")).intValue();
      // String name = (String) joLevel2.get("name");
      // String type = (String) joLevel2.get("type");
      int typeInformation = ((Long) joLevel2.get("type")).intValue();
      int roadInformation = ((Long) joLevel2.get("road")).intValue();
      content[x][y] = new Location(x, y, "", roadInformation, typeInformation);
    }

  }

  /**
   * This method checks if a coordinate is a valid location in the GameMap.
   * 
   * @param c is a given coordinate
   * @return true if c is in the map, otherwise false
   */
  public boolean isValid(Coordinate c) {
    return (1 <= c.getX() && c.getX() <= length && 1 <= c.getY() && c.getY() <= width);
  }

  /**
   * This method gets the Location instance of a specific location on the map
   * 
   * @param x is the x-coordinate of the wanted Location
   * @param y is the y-coordinate of the wanted Location
   * @return the Location instance of the given location
   */
  public Location getLocation(int x, int y) {
    return content[x][y];
  }

  /**
   * This method gets the Location instance of a specific location on the map
   * 
   * @param pos is the Coordinate of the given location
   * @return the Location instance of the given location
   */
  public Location getLocation(Coordinate pos) {
    return content[pos.getX()][pos.getY()];
  }


  /**
   * @return the length of the map
   */
  public int getLength() {
    return length;
  }

  /**
   * @return the width of the map
   */
  public int getWidth() {
    return width;
  };

  @Override
  public String toString() {
    String result = this.length + " " + this.width;
    return result;
  }

  /**
   * This method gets a portion of the map from a top left coordinate and a bottom right coordinate
   * @param topLeft is the top left coordinate of the map
   * @param bottomRight is the bottom right coordinate of the map
   * @return a portion of the map in the form of a 2d-array of Location
   */
  public Location[][] getMap(Coordinate topLeft, Coordinate bottomRight) {
    int width = bottomRight.getX() - topLeft.getX();
    int length = bottomRight.getY() - topLeft.getY() + 1;
    Location[][] result = new Location[width][length];
    for (int i = 0; i < width; i++)
      for (int j = 0; j < length; j++)
        result[i][j] = content[topLeft.getX() + i][topLeft.getY() + j];
    return result;
  }

  /**
   * This method updates the map in the JSON File
   * @throws FileNotFoundException is never thrown if no file is missing
   */
  @SuppressWarnings("unchecked")
  public void updateJSONFile() throws FileNotFoundException {
    JSONObject jo = new JSONObject();
    jo.put("length", this.length);
    jo.put("width", this.width);

    JSONArray data = new JSONArray();
    for (int i = 1; i <= length; i++)
      for (int j = 1; j <= width; j++) {
        JSONObject location = content[i][j].createJSONObject();
        data.add(location);
      }

    jo.put("data", data);

    PrintWriter pw = new PrintWriter(this.pathToJSONFile);
    pw.write(jo.toJSONString());

    pw.flush();
    pw.close();

  }

}
