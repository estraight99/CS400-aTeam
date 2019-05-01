package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import org.json.simple.parser.ParseException;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * This class is the Main class of our Road Builder project.
 * 
 * Dung Viet Bui gets some help from
 * https://stackoverflow.com/questions/26619566/javafx-stage-close-handler to implement the close
 * window part
 * 
 * @author Dung Viet Bui, Eli Straight, Yuanbo Zhang
 */
public class Main extends Application {

  GUIInformation information; // the instance stores all the information for the GUI
  Stage primaryStage; // primary stage to display the main application window
  BorderPane root; // a borderPane layout to arrange all the elements
  LeftPanel leftPanel; // left panel of this borderPane
  RightPanel rightPanel; // right panel of this borderPane
  Scene scene; // the scene of this stage

  /**
   * This method initializes all GUI information
   * 
   * @throws FileNotFoundException is never thrown if no file is missing
   * @throws IOException           is never thrown if no file is missing
   * @throws ParseException        is never thrown if no file is missing
   */
  private void initializeInformation() throws FileNotFoundException, IOException, ParseException {
    User mainUser = new User("." + File.separator + "database" + File.separator + "user.json");

    Coordinate topLeft = new Coordinate(1, 1);

    information = new GUIInformation(mainUser, 1, topLeft, this);
  }

  /**
   * This method runs when application starts
   * 
   * @param primaryStage is the main window of the application
   */
  @Override
  public void start(Stage primaryStage) {
    try {
      this.primaryStage = primaryStage;
      primaryStage.setTitle("Road Builder");
      initializeInformation();
      initializeRoot();
      primaryStage.setOnCloseRequest(event -> {
        event.consume();
        new SaveGameWindow(primaryStage, information);
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * This method initializes the borderPane
   * 
   * @throws FileNotFoundException is never thrown if no file is missing
   * @throws IOException           is never thrown if no file is missing
   * @throws ParseException        is never thrown if no file is missing
   */
  private void initializeRoot() throws FileNotFoundException, IOException, ParseException {
    root = new BorderPane();
    scene = new Scene(root);
    updateRoot();
    primaryStage.setScene(scene);
    primaryStage.show();

  }

  /**
   * This method updates the borderPane when any change is made to the main window for example: a
   * new road is built, so the map must be updated
   * 
   * @throws FileNotFoundException is never thrown if no file is missing
   * @throws IOException           is never thrown if no file is missing
   * @throws ParseException        is never thrown if no file is missing
   */
  protected void updateRoot() throws FileNotFoundException, IOException, ParseException {
    leftPanel = new LeftPanel(information, this);
    if (rightPanel != null) {
      List<TextField> field = rightPanel.getTextField();
      rightPanel = new RightPanel(this, information, field);
    } else
      rightPanel = new RightPanel(this, information);

    scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

    HBox sceneCenter = new HBox();
    sceneCenter.getChildren().addAll(leftPanel.getGUI(information), rightPanel);
    sceneCenter.setSpacing(10.0);

    root.setTop(new TitleView(primaryStage, information, this));
    root.setLeft(sceneCenter);
    root.setPadding(new Insets(10));

    sceneCenter.getChildren().clear();
    sceneCenter.getChildren().addAll(leftPanel.getGUI(information), rightPanel);

    scene.setOnKeyPressed((event) -> {
      // System.out.println(information.topLeft);
      switch (event.getCode()) {
        case A:
        case LEFT:
          information.moveMap(1);
          break;
        case W:
        case UP:
          information.moveMap(0);
          break;
        case S:
        case DOWN:
          information.moveMap(2);
          break;
        case D:
        case RIGHT:
          information.moveMap(3);
          break;
        default:
          break;
      }
      try {
        updateRoot();
      } catch (IOException | ParseException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
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
    launch(args);
  }



}
