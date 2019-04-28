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
  
  GUIInformation information;
  Stage primaryStage;
  BorderPane root;
  LeftPanel leftPanel;
  Scene scene;
  
  private void initializeInformation() throws FileNotFoundException, IOException, ParseException
  {
    User mainUser = new User("." + File.separator + "database" + File.separator + "user.json");
    
    GameMap gameMap =
        new GameMap("." + File.separator + "database" + File.separator + "mediumMap.json");
    
    Coordinate topLeft = new Coordinate(1, 1);
    
    information = new GUIInformation(mainUser, 1, topLeft);
  }
  @Override
  public void start(Stage primaryStage) {
    try {
      this.primaryStage = primaryStage;
      primaryStage.setTitle("Road Builder");
      initializeInformation();
      initializeRoot();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  private void initializeRoot()
  {
    root = new BorderPane();
    scene = new Scene(root);
    updateRoot();
    primaryStage.setScene(scene);
    primaryStage.show();
  }
  
  protected void updateRoot()
  {
    leftPanel = new LeftPanel(information,this);
    RightPanel rightPanel = new RightPanel(information);
    
    scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    
    HBox sceneCenter = new HBox();
    sceneCenter.getChildren().addAll(leftPanel.getGUI(information), rightPanel);
    sceneCenter.setSpacing(10.0);
    
    root.setTop(new TitleView(primaryStage));
    root.setLeft(sceneCenter);
    root.setPadding(new Insets(10));
    
    sceneCenter.getChildren().clear();
    sceneCenter.getChildren().addAll(leftPanel.getGUI(information), rightPanel);
    
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
      updateRoot();
    });
    
  }
  /**
   * This method launches the GUI
   * 
   * @param args is ignored by this method.
   * @throws ParseException 
   * @throws IOException 
   * @throws FileNotFoundException 
   */
  public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
    GameMap[] map = new GameMap[3];
    map[0] = new GameMap(MapGenerator.smallPath);
    map[1] = new GameMap(MapGenerator.mediumPath);
    map[2] = new GameMap(MapGenerator.bigPath);
    launch(args);
    
  }


}
