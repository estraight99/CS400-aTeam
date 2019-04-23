package application;
    
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * This class is the Main class of our Road Builder project.
 * @author Dung Viet Bui, Eli Straight, Yuanbo Zhang
 */
public class Main extends Application {
    
    /**
     * @return a Node instance which is the Help button of the program
     */
    private Node createHelpButton()
    {
      Image helpIcon = new Image(getClass().getResourceAsStream("/img/help.png"));
      ImageView helpImageView = new ImageView(helpIcon);
      helpImageView.setFitHeight(50);
      helpImageView.setFitWidth(50);
      Button helpButton = new Button();
      helpButton.setGraphic(helpImageView);
      return helpButton;
    }
    
    /**
     * @return a ComboBox that helps user choose the type of map.
     */
    private Node createComboBox()
    {
        HBox result = new HBox();
        Label label = new Label("Choose Map Size: ");
        ComboBox <String> comboBox = new ComboBox<String>();
        comboBox.getItems().addAll(
                "5*5",
                "6*6",
                "7*7"
            );
        result.getChildren().addAll(label,comboBox);
        return result;
    }
    
    
    
    @Override
    public void start(Stage primaryStage) {
        try {
            primaryStage.setTitle("Road Builder");
            BorderPane root = new BorderPane();
            Scene scene = new Scene(root,800,400);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            root.setLeft(createLeftPanel());
            root.setCenter(new RightPanel(100,10));
            
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    private VBox createLeftPanel()
    {
      VBox result = new VBox();
      result.getChildren().addAll(new TitleView(),new LegendView(),new MapView(),createComboBox());
      return result;
    }
	
	
}
