package application;

import java.io.File;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
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

      LeftPanel leftPanel = new LeftPanel(information);
      RightPanel rightPanel = new RightPanel(information);

      primaryStage.setTitle("Road Builder");
      BorderPane root = new BorderPane();
      Scene scene = new Scene(root);
      scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
      HBox sceneCenter = new HBox();
      sceneCenter.getChildren().addAll(leftPanel.getGUI(information), rightPanel);
      sceneCenter.setSpacing(10.0);
      root.setTop(new TitleView(primaryStage));
      root.setLeft(sceneCenter);
      root.setPadding(new Insets(10));

      scene.setOnKeyPressed((event) -> {
        // System.out.println(information.topLeft);
        switch (event.getCode()) {
          case A:
            information.moveMap(1);
            break;
          case W:
            information.moveMap(0);
            break;
          case S:
            information.moveMap(2);
            break;
          case D:
            information.moveMap(3);
            break;  
          case DIGIT0:
          case DIGIT1:
          case DIGIT2:
          case DIGIT3:
          case DIGIT4:
          case DIGIT5:
          case DIGIT6:
          case DIGIT7:
          case DIGIT8:
          case DIGIT9:
            try
            {
              PathFinding pathFinder = new PathFindingBFS(gameMap);
              Coordinate start = new Coordinate(rightPanel.getFromX(),rightPanel.getFromY());
              Coordinate finish = new Coordinate(rightPanel.getToX(),rightPanel.getToY());
              System.out.println("I'm here");
              rightPanel.cost_lbl.setText("$"+pathFinder.evaluateCost(start, finish));
            }
            catch (Exception e)
            {
              // do nothing
            }
            break;
          default:
            break;
        }
        sceneCenter.getChildren().clear();
        sceneCenter.getChildren().addAll(leftPanel.getGUI(information), rightPanel);
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
