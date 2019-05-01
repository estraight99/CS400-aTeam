package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.simple.parser.ParseException;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;

/**
 * This class defines the area where all the legends are displayed, it extends FlowPane class
 * 
 * @author Yuanbo Zhang
 *
 */
public class LegendView extends FlowPane {
	
	/**
	 * This method initialize a legendView where all the legends are displayed
     * @throws FileNotFoundException is never thrown if no file is missing
     * @throws IOException is never thrown if no file is missing
     * @throws ParseException is never thrown if no file is missing
     */
	LegendView() throws FileNotFoundException, IOException, ParseException
	{
	    TypeOfLocationList list = new TypeOfLocationList();
	    
	    for (int i=1; i<list.getSize(); i++)
	    {
	      ImageView legend = createLegend(i);
	      Label label = new Label(list.getName(i));
	      HBox pair = new HBox();
	      pair.getChildren().addAll(legend,label);
	      this.getChildren().add(pair);
	    }
		this.setHgap(5);
		this.setMaxWidth(350.0);
	}
	
	/**
	 * This method loads a legend icon and return the fitted imageView
	 * @param typeOfLocation the type of this map block
     * @return an imageView of the legend icon
     */
	private ImageView createLegend(int typeOfLocation)
    {
      Image legend = new Image(getClass().getResourceAsStream(".."+File.separator+"img"+File.separator+"legend"+File.separator+typeOfLocation+".png"));
      ImageView locationView = new ImageView(legend);
      locationView.setFitHeight(10);
      locationView.setFitWidth(10);
      return locationView;
    }
}
