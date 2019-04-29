package application;

import java.io.File;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class MapView {
    static Image[] allPossibleImage;
    GUIInformation information;
    
    
	MapView(GUIInformation information)
	{
	  this.information = information;
	  allPossibleImage = new Image[16];
	}
	
	/**
	 * This method creates an ImageView of a tile on the graph.
	 * @param typeOfRoad is the road information in integer. 0<=typeOfRoad<=16
	 * @return an ImageView with that typeOfRoad
	 */
	private ImageView createLocation(Location loc)
    {
	  int typeOfRoad = loc.getRoadInformation();
	  Image location = allPossibleImage[typeOfRoad];
	  if (location==null)
	  {
	    location = new Image(getClass().getResourceAsStream(".."+File.separator+"img"+File.separator+"road"+File.separator+typeOfRoad+"_0.png"));
	    allPossibleImage[typeOfRoad] = location;
	  }
      ImageView locationView = new ImageView(location);
      locationView.setFitHeight(20);
      locationView.setFitWidth(20);
      return locationView;
    }

  public GridPane getGridPane() {
    GridPane result = new GridPane();
    
    result.setVgap(0.1);
    result.setHgap(0.1);
    
    
    int n = information.getMap().length;
    int m = information.getMap().width;
    
    int x = Math.min(information.topLeft.x,n-9);
    int y = Math.min(information.topLeft.y,m-9);
    
    for (int i = x; i <= x+9; i++)
        for (int j = y; j <= y+9; j++)
            result.add(createLocation(information.getMap().getLocation(i, j)), j, i);
    
    for (Integer i = x; i <= x+9; i++) {
        Label label = new Label(i.toString());
        //label.setMinSize(50, 50);
        //label.setAlignment(Pos.CENTER);
        result.add(label, 0, i);
    }
    for (Integer j = y; j <= y+9; j++) {
        Label label = new Label(j.toString());
        //label.setAlignment(Pos.CENTER);
        result.add(label, j, 0);
    }
    VBox dotVbox = new VBox();
    Image dot = new Image(getClass().getResourceAsStream(".."+File.separator+"img"+File.separator+"legend"+File.separator+"green.png"));
    ImageView dotView = new ImageView(dot);
    dotView.setFitHeight(8);
    dotView.setFitWidth(8);
    dotVbox.getChildren().add(dotView);
    dotVbox.setAlignment(Pos.CENTER);
    result.add(dotVbox, 1, 8);
    
    return result;
  }
}
