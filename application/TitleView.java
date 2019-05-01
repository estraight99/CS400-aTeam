package application;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * This class represents the title of the program
 * @author Dung Viet Bui
 *
 */
public class TitleView extends BorderPane {
  
    private Stage primaryStage;
	private GUIInformation information;
	private Main mainInstance;
	
	/**
     * This method initializes a title for the program.
     */
    public TitleView(Stage primaryStage,GUIInformation information,Main mainInstance)
	{
		super();
		this.primaryStage = primaryStage;
		this.information = information;
		Label title = new Label("Road Builder");
		title.setFont(Font.font("Arial",FontWeight.BOLD,20));
		this.mainInstance = mainInstance;
		super.setLeft(title);
		super.setRight(createHelpButton());
	}
	
	/**
     * @return a Node instance which is the Help button of the program
     */
    private Node createHelpButton()
    {
      HBox help = new HBox();
      Image helpIcon = new Image(getClass().getResourceAsStream("/img/help.png"));
      ImageView helpImageView = new ImageView(helpIcon);
      helpImageView.setFitHeight(15);
      helpImageView.setFitWidth(15);
      Button helpButton = new Button();
      helpButton.setGraphic(helpImageView);
      helpButton.setOnAction(event ->
      {
        new HelpView(primaryStage,information,mainInstance);
      });
      
      Label label = new Label("Click this button to access some special features!");
      help.getChildren().addAll(label,helpButton);
      help.setSpacing(5);
      return help;
    }
}
