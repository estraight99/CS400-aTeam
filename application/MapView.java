package application;

import java.io.File;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class MapView extends GridPane {
	MapView(GameMap map)
	{
		this.setVgap(0.1);
		this.setHgap(0.1);
		
		int n = map.getLength();
		int m = map.getWidth();
		
		for (int i = 1; i <= n; i++)
			for (int j = 1; j <= m; j++)
				this.add(createLocation(map.getLocation(i, j)), j, i);
		
		for (Integer i = 1; i <= n; i++) {
        	Label label = new Label(i.toString());
        	//label.setMinSize(50, 50);
        	//label.setAlignment(Pos.CENTER);
        	this.add(label, 0, i);
        }
		for (Integer j = 1; j <= m; j++) {
        	Label label = new Label(j.toString());
        	//label.setAlignment(Pos.CENTER);
        	this.add(label, j, 0);
		}	
	}
	
	/**
	 * This method creates an ImageView of a tile on the graph.
	 * @param typeOfRoad is the road information in integer. 0<=typeOfRoad<=16
	 * @return an ImageView with that typeOfRoad
	 */
	private ImageView createLocation(Location loc)
    {
	  int typeOfRoad = loc.getRoadInformation();
      Image location = new Image(getClass().getResourceAsStream(".."+File.separator+"img"+File.separator+"road"+File.separator+typeOfRoad+".png"));
      ImageView locationView = new ImageView(location);
      locationView.setFitHeight(50);
      locationView.setFitWidth(50);
      return locationView;
    }
}
