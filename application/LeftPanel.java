package application;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.simple.parser.ParseException;
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
	
    private GUIInformation information; // the instance stores all the information for the GUI
    private LegendView legend; // an area displays all the legends
    private Main mainInstance; // is the Main instance running the program
    private ComboBox<String> comboBox; // a combo box where user can choose map size
    /**
     * This method creates a VBox instance of the left part of the GUI
     * @throws ParseException 
     * @throws IOException 
     * @throws FileNotFoundException 
     */
	public LeftPanel(GUIInformation information,Main mainInstance) throws FileNotFoundException, IOException, ParseException
	{
		super();
		this.information = information;
		this.legend = new LegendView();
		createComboBox();
		this.mainInstance = mainInstance;
	}
	
	/**
     * This method creates and returns a VBOX containing some labels to display help information
     * @return the VBOX containing help information
     */
	private Node createHelp()
	{
	  VBox help = new VBox();
	  Label label1 = new Label("Press A, S, D, W to move the map!");
	  Label label2 = new Label("Click on the map to choose locations");
	  help.getChildren().addAll(label1,label2);
	  help.setSpacing(5);
	  return help;
	}
	/**
	 * This method creates and returns a comboBox where user can choose map size
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
                "1000*1000"
            );
        if (information.getMap().getWidth()==GameConstant.smallMapSize)
          comboBox.getSelectionModel().select(0);
        if (information.getMap().getWidth()==GameConstant.mediumMapSize)
          comboBox.getSelectionModel().select(1);
        if (information.getMap().getWidth()==GameConstant.bigMapSize)
          comboBox.getSelectionModel().select(2);
        
        comboBox.setOnAction(event ->
        {
          if (comboBox.getValue().equals("10*10"))
            try {
              changeMap(0);
            } catch (IOException | ParseException e2) {
              // do nothing
              e2.printStackTrace();
            }
          else
          if (comboBox.getValue().equals("100*100"))
            try {
              changeMap(1);
            } catch (IOException | ParseException e1) {
              // do nothing
              e1.printStackTrace();
            }
          else
          if (comboBox.getValue().equals("1000*1000"))
            try {
              changeMap(2);
            } catch (IOException | ParseException e) {
              // do nothing
              e.printStackTrace();
            }
        });
        result.getChildren().addAll(label,comboBox);
        return result;
    }
    
    /**
	 * This method defines the behavior when user change the map size in the drop down list
     * @param newMapID is the ID of the new map ((0 - small, 1 - medium, 2 - big)
     * @throws FileNotFoundException is never thrown if no file is missing
     * @throws IOException is never thrown if no file is missing
     * @throws ParseException is never thrown if no file is missing
     */
    private void changeMap(int newMapID) throws FileNotFoundException, IOException, ParseException
    {
      try
      {
        information.changeMap(newMapID);
      }
      catch (Exception e)
      {
        // do nothing
      }
      mainInstance.updateRoot();
    }
    
    /**
	 * This method creates and returns a VBOX containing all the elements in the left panel
     * @param information the instance stores all the information for the GUI
     * @return a VBOX containing all the elements in the left panel
     */
    public Node getGUI(GUIInformation information)
    {
      this.information = information;
      VBox result = new VBox();
      MapView map = new MapView(information);
      result.getChildren().addAll(legend,map.getGridPane(),this.createHelp(),this.createComboBox());
      result.setSpacing(10); 
      return result;
    }
    
}
