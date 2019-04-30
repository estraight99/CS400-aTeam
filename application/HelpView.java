package application;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Adapted from https://o7planning.org/en/11533/opening-a-new-window-in-javafx
 * @author Dung Viet Bui
 *
 */
public class HelpView extends Stage
{
  Scene scene;
  VBox root;
  Stage owner;
  public HelpView(Stage owner) {
    this.setTitle("Help");
    root = new VBox();
    
    scene = new Scene(root);
    this.owner = owner;
    this.setScene(scene);
    this.initModality(Modality.WINDOW_MODAL);
    this.initOwner(owner);
    this.setX(owner.getX()+owner.getWidth()/4);
    this.setY(owner.getY()+owner.getHeight()/4);
    
    root.setPadding(new Insets(10));
    
    root.getChildren().addAll(this.mapHelp());
    
    this.show();
  }
  
  
  private Node mapHelp()
  {
    Label controlTheMap = new Label("Use A, S, D, W to move the map left, down, right, up respectively!");
    return controlTheMap;
  }
  
}
