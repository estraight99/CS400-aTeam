package application;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.simple.parser.ParseException;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * This class stores the information for the GUI when the program is running
 * @author Dung Viet Bui
 */
public class GUIInformation {
  //this list stores the information (including the heavy images) of different kinds of location 
  static TypeOfLocationList typeOfLocationList = new TypeOfLocationList(); 

  private User user; // is the user using the program
  private GameMap[] map; // is the current map of the program
  private int mapID; // is the id of the map the user is looking at
  private Coordinate topLeft; // is the top left of the map displayed on the screen
  private Location currentLocation; // is the current location displayed in the big box
  private Main mainInstance; // is the Main instance running the program

  /**
   * This method initializes a GUIInformation instance
   * @param user is the user currently using this program
   * @param initMapID is the first map the user will be looking at
   * @param topLeft is the top left coordinate of the map displayed on the screen
   * @param mainInstance is the Main instance running the program
   * @throws FileNotFoundException is never thrown if no file is missing
   * @throws IOException is never thrown if no file is missing
   * @throws ParseException is never thrown if no file is missing
   */
  public GUIInformation(User user, int initMapID, Coordinate topLeft,Main mainInstance)
      throws FileNotFoundException, IOException, ParseException {
    this.user = user;
    this.map = new GameMap[3];
    // preload the map
    this.map[0] = new GameMap(GameConstant.smallPath);
    this.map[1] = new GameMap(GameConstant.mediumPath);
    this.map[2] = new GameMap(GameConstant.bigPath);
    
    this.mapID = initMapID;
    this.topLeft = topLeft;
    this.currentLocation = this.getMap().getLocation(1,1);
    this.mainInstance = mainInstance;
  }

   /**
   * @return the map the program is using
   */
  public GameMap getMap() {
    return this.map[this.mapID];
  }

  /**
   * This method changes the current map to the new map
   * @param id is the id of the new map (0 - small, 1 - medium, 2 - big)
   */
  public void changeMap(int id) {
    this.mapID = id;
    this.topLeft = new Coordinate(1, 1);
    this.currentLocation = this.getMap().getLocation(1,1);
  }

  /**
   * This method changes the current location displayed in the big box
   * @param x is the x-coordinate of the new location
   * @param y is the y-coordinate of the new location
   */
  public void changeCurrentLocation(int x, int y) {
    this.currentLocation = this.getMap().getLocation(x,y);
  }

  /**
   * This method checks if the new top left coordinate is valid or not
   * @param newCoor the new top left coordinate of the map view
   * @return true if the new top left is valid, otherwise false
   */
  private boolean ok(Coordinate newCoor) {
    return (1 <= newCoor.getX() && newCoor.getX() <= Math.max(1, getMap().getLength() - 9) && 1 <= newCoor.getY()
        && newCoor.getY() <= Math.max(1, getMap().getWidth() - 9));
  }

  /**
   * This method moves the displayed map in the given direction
   * @param direction is an integer from 0 to 3 that represents a direction (up, down, left, right)
   */
  public void moveMap(int direction) {
    Coordinate result = new Coordinate(topLeft.getX() + GameConstant.mx[direction], topLeft.getY() + GameConstant.my[direction]);
    // System.out.println((map.length-9)+" "+(map.width-9));
    if (ok(result))
      this.topLeft = result;
  }

  /**
   * This method updates all JSON Files that are related to the game
   * @throws FileNotFoundException is never thrown
   */
  public void updateJSONFile() throws FileNotFoundException {
    user.updateJSONFile();
    for (int i = 0; i < map.length; i++)
      map[i].updateJSONFile();
  }

  /**
   * This method returns a Node that displays the full information of the current Location on the map
   * @param fromX is the text field that contains the x coordinate of the starting point
   * @param fromY is the text field that contains the y coordinate of the starting point
   * @param toX is the text field that contains the x coordinate of the ending point
   * @param toY is the text field that contains the y coordinate of the ending point
   * @return the Node that displays the full information of the current Location
   */
  public Node currentLocationView(TextField fromX, TextField fromY, TextField toX, TextField toY) {
    VBox result = new VBox();
    Label title = new Label(GUIInformation.typeOfLocationList.getName(this.currentLocation.type)
        + " (" + this.currentLocation.getX() + "," + this.currentLocation.getY() + ")");
    title.setFont(Font.font("Arial", FontWeight.BOLD, 15));
    ImageView image =
        new ImageView(GUIInformation.typeOfLocationList.getImage(this.currentLocation.type));
    image.setFitHeight(150);
    image.setFitWidth(200);
    Button setAsStart = new Button("Set as Starting Point");
    Button setAsEnd = new Button("Set as Ending Point");
    setAsStart.setOnAction(event -> {
      fromX.setText(((Integer) this.currentLocation.getX()).toString());
      fromY.setText(((Integer) this.currentLocation.getY()).toString());
    });
    setAsEnd.setOnAction(event -> {
      toX.setText(((Integer) this.currentLocation.getX()).toString());
      toY.setText(((Integer) this.currentLocation.getY()).toString());
    });
    result.getChildren().addAll(title, image, setAsStart, setAsEnd);
    result.setSpacing(5);
    return result;
  }

  /**
   * @return the current user who uses this program
   */
  public User getUser() {
    return this.user;
  }
  
  /**
   * @return the current Main instance of the program
   */
  public Main getMainInstance()
  {
    return this.mainInstance;
  }
  
  /**
   * @return the current top left coordinate of the displayed map.
   */
  public Coordinate getTopLeft()
  {
    return this.topLeft;
  }
}
