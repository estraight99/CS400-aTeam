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
 * Adapted from https://o7planning.org/en/11533/opening-a-new-window-in-javafx
 * 
 * @author Dung Viet Bui
 *
 */
public class HelpView extends Stage {
  Scene scene;
  VBox root;
  Stage owner;
  GUIInformation information;
  Main mainInstance;

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


  private Node mapHelp() {
    VBox result = new VBox();
    
    Button makeTheUserRich = new Button("Make " + information.user.getName() + " rich!");
    Button makeTheUserPoor = new Button("Make " + information.user.getName() + " poor!");
    TextField newUsernameInput = new TextField();
    newUsernameInput.setPromptText("New username");
    Button changeUsername = new Button("Change username!");

    makeTheUserRich.setOnAction(event -> {
      this.information.user.setMoney((int) 1e5);
      try {
        mainInstance.updateRoot();
      } catch (IOException | ParseException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    });
    makeTheUserPoor.setOnAction(event -> {
      this.information.user.setMoney(10);
      try {
        mainInstance.updateRoot();
      } catch (IOException | ParseException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    });
    
    changeUsername.setOnAction(event -> {
      this.information.user.setName(newUsernameInput.getText());
      try {
        this.mainInstance.updateRoot();
      } catch (IOException | ParseException e) {
        // TODO Auto-generated catch block
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
