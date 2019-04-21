package application;
	
import javafx.application.Application;
import javafx.geometry.Insets;
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
<<<<<<< HEAD
import javafx.scene.layout.VBox;
=======
import javafx.scene.layout.GridPane;
>>>>>>> 0bfe1d1f80b303c87e0240ee3c3895505c979126
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class Main extends Application {
  
    private Node createTitle()
    {
      Label title = new Label("Road Builder");
      title.setFont(Font.font("Arial",FontWeight.BOLD,20));
      
      return title;
    }
    
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
    
    private Node createTextForComboBox()
    {
    	Text text = new Text();
    	text.setText("Type :");
    	
		return text;
    }
    
    private Node createComboBox()
    {
    	ComboBox <String> comboBox = new ComboBox<String>();
		comboBox.getItems().addAll(
			    "5*5",
			    "6*6",
			    "7*7"
			);
		return comboBox;
    }
    
    private ImageView createLocation(int typeOfRoad)
    {
      typeOfRoad = 0;
      Image location = new Image(getClass().getResourceAsStream("/img/road/"+typeOfRoad+".png"));
      ImageView locationView = new ImageView(location);
      locationView.setFitHeight(10);
      locationView.setFitWidth(10);
      return locationView;
    }
    
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,1000,800);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			root.setTop(createTitle());
			root.setLeft(createComboBox());
			root.setRight(createRightPanel(100,10));
			root.setPadding(new Insets(10));
			
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
	  result.getChildren().addAll(drawMap(),createComboBox());
	  return result;
	}
	private Node createRightPanel(int money, int cost) {
	    Group rightSide = new Group();
	    
	    Label info_lbl = new Label("Player's Info");
	    Label playerMoney_lbl = new Label("You Have: $" + money);
	    Label from_lbl = new Label("From: ");
	    Label to_lbl = new Label("To: ");
	    Label buildCost_lbl = new Label("Cost: $" + cost);
	    
	    Button build_btn = new Button("Build");
	    
	    TextField fromLeft_tf = new TextField();
	    TextField fromRight_tf = new TextField();
	    TextField toLeft_tf = new TextField();
		TextField toRight_tf = new TextField();

		return null;
	}
	
	private Node drawMap() {
		GridPane grid = new GridPane();
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++)
				grid.add(createLocation(0), i, j);
		return grid;
	}
}
