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

public class GUIInformation {
  static int[] mx = new int[] {-1, 0, 1, 0};
  static int[] my = new int[] {0, -1, 0, 1};
  static TypeOfLocationList typeOfLocationList = new TypeOfLocationList();

  User user;
  GameMap[] map;
  int mapID;
  Coordinate topLeft;
  Location currentLocation;
  Main mainInstance;

  GUIInformation(User user, int initMapID, Coordinate topLeft,Main mainInstance)
      throws FileNotFoundException, IOException, ParseException {
    this.user = user;
    this.map = new GameMap[3];
    this.map[0] = new GameMap(MapGenerator.smallPath);
    this.map[1] = new GameMap(MapGenerator.mediumPath);
    this.map[2] = new GameMap(MapGenerator.bigPath);
    this.mapID = initMapID;
    this.topLeft = topLeft;
    this.currentLocation = this.getMap().getLocation(1,1);
    this.mainInstance = mainInstance;
  }

  public GameMap getMap() {
    return this.map[this.mapID];
  }

  public void changeMap(int id) {
    this.mapID = id;
    this.topLeft = new Coordinate(1, 1);
    this.currentLocation = this.getMap().getLocation(1,1);
  }

  public void changeCurrentLocation(int x, int y) {
    this.currentLocation = this.getMap().getLocation(x,y);
  }

  private boolean ok(Coordinate newCoor) {
    return (1 <= newCoor.getX() && newCoor.getX() <= Math.max(1, getMap().getLength() - 9) && 1 <= newCoor.getY()
        && newCoor.getY() <= Math.max(1, getMap().getWidth() - 9));
  }

  public void moveMap(int direction) {
    Coordinate result = new Coordinate(topLeft.getX() + mx[direction], topLeft.getY() + my[direction]);
    // System.out.println((map.length-9)+" "+(map.width-9));
    if (ok(result))
      this.topLeft = result;
  }

  public void updateJSONFile() throws FileNotFoundException {
    user.updateJSONFile();
    for (int i = 0; i < map.length; i++)
      map[i].updateJSONFile();
  }

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
}
