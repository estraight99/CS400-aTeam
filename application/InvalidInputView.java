package application;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class InvalidInputView extends Stage{
    Scene scene;
    VBox root;
    Stage owner;

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

    private Node inputProblem(String message) {
	Label messagePrompt = new Label(message);
	return messagePrompt;
    }
}
