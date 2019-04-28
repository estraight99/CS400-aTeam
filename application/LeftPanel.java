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
public class LeftPanel {
	
    GUIInformation information;
    LegendView legend;
    Main mainInstance;
    ComboBox<String> comboBox;
    /**
     * This method creates a VBox instance of the left part of the GUI
     */
	LeftPanel(GUIInformation information,Main mainInstance)
	{
		super();
		this.information = information;
		this.legend = new LegendView();
		createComboBox();
		this.mainInstance = mainInstance;
	}
	
	/**
     * @return a ComboBox that helps user choose the type of map.
     */
    private Node createComboBox()
    {
        HBox result = new HBox();
        Label label = new Label("Choose Map Size: ");
        comboBox = new ComboBox<String>();
        comboBox.getItems().addAll(
                "10*10",
                "100*100",
                "1000*2000"
            );
        if (information.map.getWidth()==10)
          comboBox.getSelectionModel().select(0);
        if (information.map.getWidth()==100)
          comboBox.getSelectionModel().select(1);
        if (information.map.getWidth()==1000)
          comboBox.getSelectionModel().select(2);
        
        comboBox.setOnAction(event ->
        {
          if (comboBox.getValue().equals("10*10"))
            changeMap(MapGenerator.smallPath);
          else
          if (comboBox.getValue().equals("100*100"))
            changeMap(MapGenerator.mediumPath);
          else
          if (comboBox.getValue().equals("1000*2000"))
            changeMap(MapGenerator.bigPath);
        });
        result.getChildren().addAll(label,comboBox);
        return result;
    }
    
    private void changeMap(String path)
    {
      try
      {
        information.map = new GameMap(path);
        information.topLeft = new Coordinate(1,1);
      }
      catch (Exception e)
      {
        // do nothing
      }
      mainInstance.updateRoot();
    }
    
    public Node getGUI(GUIInformation information)
    {
      this.information = information;
      VBox result = new VBox();
      MapView map = new MapView(information);
      result.getChildren().addAll(legend,map.getGridPane(),this.createComboBox());
      result.setSpacing(10); 
      return result;
    }
}
