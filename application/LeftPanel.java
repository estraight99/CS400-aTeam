package application;

import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * This class represents the left part of the GUI
 * @author Dung Viet Bui, Yuanbo Zhang
 */
public class LeftPanel extends VBox{
	
    /**
     * This method creates a VBox instance of the left part of the GUI
     */
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
                "10*10",
                "1000*1000",
                "10000*10000"
            );
        result.getChildren().addAll(label,comboBox);
        return result;
    }
}
