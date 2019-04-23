package application;

import java.io.File;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class MapView extends GridPane {
	MapView()
	{
		this.setVgap(1);
		this.setHgap(1);
		
		for (int i = 1; i <= 4; i++)
			for (int j = 1; j <= 4; j++)
				this.add(createLocation(0), i, j);
		
		for (Integer i = 1; i <= 4; i++) {
        	Label label = new Label(i.toString());
        	//label.setMinSize(50, 50);
        	label.setAlignment(Pos.CENTER);
        	this.add(label, 0, i);
        }
		for (Integer j = 1; j <= 4; j++) {
        	Label label = new Label(j.toString());
        	label.setAlignment(Pos.CENTER);
        	this.add(label, j, 0);
        }
	}
	
	private ImageView createLocation(int typeOfRoad)
    {
      typeOfRoad = 0;
      Image location = new Image(getClass().getResourceAsStream(".."+File.separator+"img"+File.separator+"road"+File.separator+typeOfRoad+".png"));
      ImageView locationView = new ImageView(location);
      locationView.setFitHeight(50);
      locationView.setFitWidth(50);
      return locationView;
    }
}
