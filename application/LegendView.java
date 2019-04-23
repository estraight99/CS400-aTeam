package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;

public class LegendView extends FlowPane {
	
	LegendView()
	{
		Label label1 = new Label("Factory");
    	Label label2 = new Label("Sawmill");
		this.getChildren().addAll(createLegend("red"),label1,createLegend("blue"),label2);
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
