package application;

import javafx.scene.layout.VBox;

public class LeftPanel extends VBox{
	LeftPanel()
	{
		super();
	    this.getChildren().addAll(new LegendView(),new MapView());
	}
}
