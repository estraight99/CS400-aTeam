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
            Scene scene = new Scene(root,600,400);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            root.setLeft(createLeftPanel());
            root.setCenter(createRightPanel(100,10));
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
      result.getChildren().addAll(new TitleView(),new LegendView(),new MapView(),createComboBox());
      return result;
    }
    
    private Node createRightPanel(int money, int cost) {
	    //creating components
	    VBox rightSide_vbox = new VBox(); 
	    
	    HBox next_row = new HBox();
	    
	    VBox first_column = new VBox();
	    VBox second_column = new VBox();
	    VBox third_column = new VBox();
	    
	    next_row.getChildren().addAll(first_column,second_column,third_column);
	    
	    HBox from_hbox = new HBox();
	    HBox to_hbox = new HBox();
	    //need hbox for button and info for alignment
	    HBox build_hbox = new HBox();
	    HBox info_hbox = new HBox();
	    
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
	    
	    first_column.getChildren().addAll(from_lbl,to_lbl,buildCost_lbl);
	    second_column.getChildren().addAll(fromLeft_tf,toLeft_tf);
	    third_column.getChildren().addAll(fromRight_tf,toRight_tf,build_btn);
	    
	    //changing labels's attributes
	    info_lbl.setFont(Font.font("Arial",FontWeight.BOLD,20));
	    playerMoney_lbl.setFont(Font.font("Arial",20));
	    from_lbl.setFont(Font.font("Arial",20));
	    to_lbl.setFont(Font.font("Arial",20));
	    buildCost_lbl.setFont(Font.font("Arial",20));
	    build_btn.setFont(Font.font("Arial",20));
	    
	    //changing TextField's width
	    fromLeft_tf.setPrefWidth(80);
	    fromRight_tf.setPrefWidth(80);
	    toLeft_tf.setPrefWidth(80);
	    toRight_tf.setPrefWidth(80);
	    
	    
	    /*//setting spacing and adding to boxes
	    from_hbox.setSpacing(20);
	    //from_hbox.getChildren().addAll(from_lbl, fromLeft_tf, fromRight_tf);
	    
	    //to_hbox.setSpacing(20);
	    //to_hbox.getChildren().addAll(to_lbl, toLeft_tf, toRight_tf);
	    //to_hbox.setMargin(toLeft_tf, new Insets(0, 0, 0, 22));
	    
	    build_hbox.getChildren().addAll(build_btn);
	    //build_hbox.setAlignment(Pos.BASELINE_RIGHT);
	    
	    info_hbox.getChildren().addAll(info_lbl);
	    //info_hbox.setAlignment(Pos.BASELINE_CENTER);
	    
	    rightSide_vbox.setSpacing(25);*/
	    /*rightSide_vbox.getChildren().addAll(info_hbox, playerMoney_lbl, 
		    from_hbox, to_hbox, buildCost_lbl, build_hbox);*/
	    
	    //changing the margins for rightSide_vbox's children
	    /*rightSide_vbox.setMargin(info_hbox, new Insets(40, 50, 30, 0));
	    rightSide_vbox.setMargin(playerMoney_lbl, new Insets(0, 50, 0, 0));
	    rightSide_vbox.setMargin(from_hbox, new Insets(0, 50, 0, 0));
	    rightSide_vbox.setMargin(to_hbox, new Insets(0, 50, 0, 0));
	    rightSide_vbox.setMargin(build_hbox, new Insets(0, 50, 0, 0));*/
	    next_row.setSpacing(20);
	    rightSide_vbox.setSpacing(10);
	    first_column.setSpacing(10);
	    second_column.setSpacing(10);
	    third_column.setSpacing(10);
	    rightSide_vbox.getChildren().addAll(info_lbl,playerMoney_lbl,next_row);
	    
	    return rightSide_vbox;
	}
	
	
}
