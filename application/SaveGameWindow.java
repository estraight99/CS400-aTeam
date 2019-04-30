package application;

import java.io.FileNotFoundException;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SaveGameWindow extends Stage {
  Scene scene;
  VBox root;
  Stage owner;
  GUIInformation information;

  public SaveGameWindow(Stage owner, GUIInformation information) {
    this.setTitle("Save Game?");
    root = new VBox();

    scene = new Scene(root);

    this.owner = owner;
    this.information = information;

    this.setScene(scene);
    this.initModality(Modality.WINDOW_MODAL);
    this.initOwner(owner);
    this.setX(owner.getX() + owner.getWidth() / 4);
    this.setY(owner.getY() + owner.getHeight() / 4);

    root.setPadding(new Insets(10));
    root.setSpacing(5);
    HBox twoButtons = new HBox();
    twoButtons.getChildren().addAll(yesButton(), noButton());
    twoButtons.setSpacing(10.0);
    twoButtons.setAlignment(Pos.CENTER);
    root.getChildren().addAll(this.message(),twoButtons);

    this.show();
  }

  private Label message() {
    return new Label("Do you want to save the game?");
  }

  private Button yesButton() {
    Button result = new Button("Yes");
    result.setOnAction(event -> {
      try {
        information.updateJSONFile();
        owner.close();
      } catch (FileNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    });
    return result;
  }

  private Button noButton() {
    Button result = new Button("No");
    result.setOnAction(event -> {owner.close();});
    return result;
  }



}
