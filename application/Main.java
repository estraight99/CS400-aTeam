package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


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
    	text.setText("size:");
    	
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
    
    
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			root.setTop(createTitle());
			root.setRight(createHelpButton());
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
}
