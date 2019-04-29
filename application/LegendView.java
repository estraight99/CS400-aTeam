package application;

import java.io.File;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;

public class LegendView extends FlowPane {
	
	LegendView()
	{
	    for (int i=1; i<=8; i++)
	    {
	      ImageView legend = createLegend(i);
	      Label label = new Label("Type "+i);
	      this.getChildren().add(legend);
	      this.getChildren().add(label);
	    }
		this.setHgap(5);
		this.setMaxWidth(200.0);
	}
	
	private ImageView createLegend(int typeOfLocation)
    {
      Image legend = new Image(getClass().getResourceAsStream(".."+File.separator+"img"+File.separator+"legend"+File.separator+typeOfLocation+".png"));
      ImageView locationView = new ImageView(legend);
      locationView.setFitHeight(10);
      locationView.setFitWidth(10);
      return locationView;
    }
}
