package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.json.simple.parser.ParseException;
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

  User mainUser;
  GameMap gameMap;
  GUIInformation information;
  LeftPanel leftPanel;
  RightPanel rightPanel;
  BorderPane root;
  Stage primaryStage;
  
  private void initializeInformation() throws FileNotFoundException, IOException, ParseException
  {
    mainUser = new User("." + File.separator + "database" + File.separator + "user.json");
    
    gameMap =
        new GameMap("." + File.separator + "database" + File.separator + "mediumMap.json");
    
    Coordinate topLeft = new Coordinate(1, 1);
    
    information = new GUIInformation(mainUser, gameMap, topLeft);
  }
  @Override
  public void start(Stage primaryStage) {
    try {
      this.primaryStage = primaryStage;
      
      initializeInformation();
      primaryStage.setTitle("Road Builder");
      updateRoot();
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
  
  
  private void updateRoot()
  {
    leftPanel = new LeftPanel(information);
    rightPanel = new RightPanel(information);
    root.setTop(new TitleView(primaryStage));
    root.setLeft(sceneCenter);
    root.setPadding(new Insets(10));
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
