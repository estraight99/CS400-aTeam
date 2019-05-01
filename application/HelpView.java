package application;

import java.io.IOException;
import org.json.simple.parser.ParseException;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * This class represents the help stage, it extends the Stage class
 * Adapted from https://o7planning.org/en/11533/opening-a-new-window-in-javafx
 * 
 * @author Dung Viet Bui
 *
 */
public class HelpView extends Stage {
  Scene scene; // the scene of this stage
  VBox root; // a VBOX containing all the elements in helpView
  Stage owner; // owner of this stage, which is the primary stage
  GUIInformation information; // the instance stores all the information for the GUI
  Main mainInstance; // is the Main instance running the program

  /**
   * This method initialized an HelpView stage where user can access some special features
   * like changing their username, increasing or decreasing the money they own
   * @param owner is the primary stage
   * @param information the instance stores all the information for the GUI
   * @param mainInstance is the Main instance running the program
   */
  public HelpView(Stage owner, GUIInformation information, Main mainInstance) {
    this.setTitle("Special Features");
    root = new VBox();

    scene = new Scene(root);
    this.owner = owner;
    this.setScene(scene);
    this.initModality(Modality.WINDOW_MODAL);
    this.initOwner(owner);
    this.setX(owner.getX() + owner.getWidth() / 4);
    this.setY(owner.getY() + owner.getHeight() / 4);
    this.information = information;
    this.mainInstance = mainInstance;
    root.setPadding(new Insets(10));

    root.getChildren().addAll(this.mapHelp());

    this.show();
  }

  /**
   * This method creates and returns a VOBX containing all the elements in the helpView
   * Features in this VBOX: 
   * 1. a button allowing user to increase the money they own
   * 2. a button allowing user to decrease the money they own
   * 3. A textField allowing user to change their user name
   * @return a VBOX that contains all the elements in helpView
   */
  private Node mapHelp() {
    VBox result = new VBox();
    
    Button makeTheUserRich = new Button("Make " + information.getUser().getName() + " rich!");
    Button makeTheUserPoor = new Button("Make " + information.getUser().getName() + " poor!");
    TextField newUsernameInput = new TextField();
    newUsernameInput.setPromptText("New username");
    Button changeUsername = new Button("Change username!");

    makeTheUserRich.setOnAction(event -> {
      this.information.getUser().setMoney((int) 1e5);
      try {
        mainInstance.updateRoot();
      } catch (IOException | ParseException e) {
        e.printStackTrace();
      }
    });
    makeTheUserPoor.setOnAction(event -> {
      this.information.getUser().setMoney(10);
      try {
        mainInstance.updateRoot();
      } catch (IOException | ParseException e) {
        e.printStackTrace();
      }
    });
    
    changeUsername.setOnAction(event -> {
      this.information.getUser().setName(newUsernameInput.getText());
      try {
        this.mainInstance.updateRoot();
      } catch (IOException | ParseException e) {
        e.printStackTrace();
      }
    });

    HBox buttonBox = new HBox();
    buttonBox.getChildren().addAll(makeTheUserRich, makeTheUserPoor);
    buttonBox.setSpacing(10);
    HBox changeUsernameBox = new HBox();
    changeUsernameBox.getChildren().addAll(newUsernameInput,changeUsername);
    changeUsernameBox.setSpacing(10);
    result.getChildren().addAll(buttonBox,changeUsernameBox);
    result.setSpacing(10);
    return result;
  }

}
