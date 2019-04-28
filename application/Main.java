package application;

import java.io.File;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * This class is the Main class of our Road Builder project.
 * 
 * @author Dung Viet Bui, Eli Straight, Yuanbo Zhang
 */
public class Main extends Application {

  @Override
  public void start(Stage primaryStage) {
    try {
      User mainUser = new User("." + File.separator + "database" + File.separator + "user.json");
      GameMap gameMap =
          new GameMap("." + File.separator + "database" + File.separator + "mediumMap.json");
      Coordinate topLeft = new Coordinate(1, 1);
      GUIInformation information = new GUIInformation(mainUser, gameMap, topLeft);
      primaryStage.setTitle("Road Builder");
      BorderPane root = new BorderPane();
      Scene scene = new Scene(root);
      scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
      HBox sceneCenter = new HBox();
      sceneCenter.getChildren().addAll(new LeftPanel(information), new RightPanel(information));
      sceneCenter.setSpacing(10.0);
      root.setTop(new TitleView());
      root.setLeft(sceneCenter);
      root.setPadding(new Insets(10));

      scene.setOnKeyTyped(new EventHandler<KeyEvent>() {

        @Override
        public void handle(KeyEvent event) {
          switch (event.getCode()) {
            case UP:
              information.moveMap(0);
              break;
            case LEFT:
              information.moveMap(1);
              break;
            case RIGHT:
              information.moveMap(2);
              break;
            case DOWN:
              information.moveMap(3);
              break;
            default:
              break;
          }
        }

      });



      primaryStage.setScene(scene);
      primaryStage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * This method launches the GUI
   * 
   * @param args is ignored by this method.
   */
  public static void main(String[] args) {
    launch(args);
  }


}
