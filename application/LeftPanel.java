package application;

import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class LeftPanel extends VBox{
	
	
	LeftPanel()
	{
		super();
	    this.getChildren().addAll(new LegendView(),new MapView(),this.createComboBox());
	    this.setSpacing(10);
	}
	
	/**
     * @return a ComboBox that helps user choose the type of map.
     */
    private Node createComboBox()
    {
        HBox result = new HBox();
        Label label = new Label("Choose Map Size: ");
        ComboBox <String> comboBox = new ComboBox<String>();
        comboBox.getItems().addAll(
                "5*5",
                "6*6",
                "7*7"
            );
        result.getChildren().addAll(label,comboBox);
        return result;
    }
}
