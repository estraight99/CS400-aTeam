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
    Node comboBox;
    /**
     * This method creates a VBox instance of the left part of the GUI
     */
	LeftPanel(GUIInformation information)
	{
		super();
		this.information = information;
		this.legend = new LegendView();
		this.comboBox = this.createComboBox();
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
                "100*100",
                "1000*2000"
            );
        comboBox.getSelectionModel().selectFirst();
        comboBox.setOnAction(event ->
        {
          if (comboBox.getValue().equals("10*10"))
          {
            changeMap(MapGenerator.smallPath);
          }
          else
          if (comboBox.getValue().equals("100*100"))
          {
            changeMap(MapGenerator.mediumPath);
          }
          else
          if (comboBox.getValue().equals("1000*2000"))
          {
            changeMap(MapGenerator.bigPath);
          }
        });
        result.getChildren().addAll(label,comboBox);
        return result;
    }
    
    private void changeMap(String path)
    {
      try
      {
        information.map = new GameMap(path);
      }
      catch (Exception e)
      {
        // do nothing
      }
      
      
    }
    
    
    public Node getGUI(GUIInformation information)
    {
      this.information = information;
      VBox result = new VBox();
      MapView map = new MapView(information);
      result.getChildren().addAll(legend,map.getGridPane(),comboBox);
      result.setSpacing(10); 
      return result;
    }
}
