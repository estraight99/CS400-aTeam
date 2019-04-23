package application;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class LegendView extends ListView<Node> {
	
	LegendView()
	{
		super();
		this.add(createLegend("red"), 0, 0);
		this.add(createLegend("green"), 0, 1);
		Label label1 = new Label("station1");
		
    	grid.add(label1, 1, 0);
    	Label label2 = new Label("station2");
    	grid.add(label2, 1, 1);
		return grid;
	}
	
	private ImageView createLegend(String color)
    {
      
      Image legend = new Image(getClass().getResourceAsStream("/img/legend/"+color+".png"));
      ImageView locationView = new ImageView(legend);
      locationView.setFitHeight(10);
      locationView.setFitWidth(10);
      return locationView;
    }
}
