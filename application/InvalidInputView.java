package application;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * This class represents the invalidInput stage, it extends the Stage class
 * 
 * @author Eli Straight
 *
 */
public class InvalidInputView extends Stage{
    Scene scene; // the scene of this stage
    VBox root; // a VBOX containing all the elements in InvalidInputView
    Stage owner; // owner of this stage, which is the primary stage

    /**
     * This method initialized an InvalidInputView stage which user will receive when they
     * input illegal coordinates
     * @param owner is the primary stage
     * @param title is the title for this stage
     * @param message is the error message displays on the stage
     */
    public InvalidInputView(Stage owner, String title, String message) {
      this.setTitle(title);
      root = new VBox();
      
      scene = new Scene(root);
      this.owner = owner;
      this.setScene(scene);
      this.initModality(Modality.WINDOW_MODAL);
      this.initOwner(owner);
      this.setX(owner.getX()+owner.getWidth()/4);
      this.setY(owner.getY()+owner.getHeight()/4);
      
      root.setPadding(new Insets(10));
      
      root.getChildren().addAll(this.inputProblem(message));
      this.show();
    }

    /**
     * This method creates and returns a label containing the error message
     *
     * @param message is the message displays on the stage
     * @return a label containing the error message
     */
    private Node inputProblem(String message) {
	Label messagePrompt = new Label(message);
	return messagePrompt;
    }
}
