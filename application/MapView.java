package application;

import java.io.File;
import java.io.IOException;
import org.json.simple.parser.ParseException;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class MapView {
  static Image[][] allPossibleImage;
  GUIInformation information;


  MapView(GUIInformation information) {
    this.information = information;
    allPossibleImage = new Image[16][9];
  }

  /**
   * This method creates an ImageView of a tile on the graph.
   * 
   * @param typeOfRoad is the road information in integer. 0<=typeOfRoad<=16
   * @return an ImageView with that typeOfRoad
   */
  private ImageView createLocation(Location loc) {
    int typeOfRoad = loc.getRoadInformation();
    int typeOfLocation = loc.getTypeInformation();
    Image location = allPossibleImage[typeOfRoad][typeOfLocation];
    if (location == null) {
      location = new Image(getClass().getResourceAsStream(".." + File.separator + "img"
          + File.separator + "road" + File.separator + typeOfRoad + "_" + typeOfLocation + ".png"));
      allPossibleImage[typeOfRoad][typeOfLocation] = location;
    }
    ImageView locationView = new ImageView(location);
    locationView.setFitHeight(30);
    locationView.setFitWidth(30);
    return locationView;
  }

  public GridPane getGridPane() {
    GridPane result = new GridPane();

    result.setVgap(0.1);
    result.setHgap(0.1);


    int n = information.getMap().length;
    int m = information.getMap().width;

    int x = Math.min(information.topLeft.x, n - 9);
    int y = Math.min(information.topLeft.y, m - 9);

    for (int i = x; i <= x + 9; i++)
      for (int j = y; j <= y + 9; j++) {
        Node current = createLocation(information.getMap().getLocation(i, j));
        final int I = i;
        final int J = j;
        // based on
        // https://stackoverflow.com/questions/25550518/add-eventhandler-to-imageview-contained-in-tilepane-contained-in-vbox
        current.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> {
          information.changeCurrentLocation(I, J);
          try {
            information.mainInstance.updateRoot();
          } catch (IOException | ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
          event.consume();
        });
        result.add(current, j, i);
      }

    for (Integer i = x; i <= x + 9; i++) {
      Label label = new Label(i.toString());
      // label.setMinSize(50, 50);
      // label.setAlignment(Pos.CENTER);
      result.add(label, 0, i);
    }
    for (Integer j = y; j <= y + 9; j++) {
      Label label = new Label(j.toString());
      // label.setAlignment(Pos.CENTER);
      result.add(label, j, 0);
    }

    return result;
  }
}
