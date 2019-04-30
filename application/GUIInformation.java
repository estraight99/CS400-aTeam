package application;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.simple.parser.ParseException;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class GUIInformation {
  static int[] mx = new int[]{-1,0,1,0};
  static int[] my = new int[]{0,-1,0,1};
  static TypeOfLocationList typeOfLocationList = new TypeOfLocationList();
  
  User user;
  GameMap[] map;
  int mapID;
  Coordinate topLeft;
  Location currentLocation;
  
  GUIInformation(User user,int initMapID, Coordinate topLeft) throws FileNotFoundException, IOException, ParseException
  {
    this.user = user;
    this.map = new GameMap[3];
    this.map[0] = new GameMap(MapGenerator.smallPath);
    this.map[1] = new GameMap(MapGenerator.mediumPath);
    this.map[2] = new GameMap(MapGenerator.bigPath);
    this.mapID = initMapID;
    this.topLeft = topLeft;
    this.currentLocation = this.getMap().content[1][1];
  }
  
  public GameMap getMap()
  {
    return this.map[this.mapID]; 
  }
  
  public void changeMap(int id)
  {
    this.mapID = id;
    this.topLeft = new Coordinate(1,1);
    this.currentLocation = this.getMap().content[1][1];
  }
  
  private boolean ok(Coordinate newCoor)
  {
    return (1<=newCoor.x && newCoor.x<=Math.max(1, getMap().length-9) && 1<=newCoor.y && newCoor.y<=Math.max(1,getMap().width-9));
  }
  
  public void moveMap(int direction)
  {
    Coordinate result = new Coordinate(topLeft.x+mx[direction],topLeft.y+my[direction]);
    //System.out.println((map.length-9)+" "+(map.width-9));
    if (ok(result))
      this.topLeft = result;
  }
  
  public void updateJSONFile() throws FileNotFoundException
  {
    user.updateJSONFile();
    for (int i=0; i<map.length; i++)
      map[i].updateJSONFile();
  }
  
  public Node currentLocationView()
  {
    VBox result = new VBox();
    Label title = new Label(GUIInformation.typeOfLocationList.getName(this.currentLocation.type));
    title.setFont(Font.font("Arial",FontWeight.BOLD,12));
    ImageView image = new ImageView(GUIInformation.typeOfLocationList.getImage(this.currentLocation.type));
    Button setAsStart = new Button("Set as Starting Point");
    Button setAsEnd = new Button("Set as Ending Point");
    HBox bottom = new HBox();
    bottom.getChildren().addAll(setAsStart,setAsEnd);
    result.getChildren().addAll(title,image,bottom);
    return result;
  }
}
