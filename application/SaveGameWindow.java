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

/**
 * This class creates a Save Game window, which is used at the end of the game
 * 
 * Adapted from https://o7planning.org/en/11533/opening-a-new-window-in-javafx
 * 
 * @author Dung Viet Bui
 */
public class SaveGameWindow extends Stage {

  private Scene scene; // the scene of this SaveGameWindow
  private VBox root; // the root of this SaveGameWindow
  private Stage owner; // the main window of the game

  private GUIInformation information; // the information for the GUI

  /**
   * This method creates a SaveGameWindow at the end of the game
   * 
   * @param owner       is the main window of the game
   * @param information is the information for the GUI
   */
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
    root.getChildren().addAll(this.message(), twoButtons);

    this.show();
  }

  /**
   * This method creates a Label with the message
   * 
   * @return a Label with the save game message
   */
  private Label message() {
    return new Label("Do you want to save the game?");
  }

  /**
   * This method creates the Yes button for the window
   * 
   * @return a Yes button that can call the appropriate method to save the game
   */
  private Button yesButton() {
    Button result = new Button("Yes");
    result.setOnAction(event -> {
      try {
        information.updateJSONFile();
        owner.close();
      } catch (FileNotFoundException e) {
        // do nothing
        e.printStackTrace();
      }
    });
    return result;
  }

  /**
   * This method creates a No button for the window
   * 
   * @return a No button
   */
  private Button noButton() {
    Button result = new Button("No");
    result.setOnAction(event -> {
      owner.close();
    });
    return result;
  }



}
