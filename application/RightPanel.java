package application;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class RightPanel extends GridPane
{
	RightPanel(int money,int cost)
	{
		super();
		//creating components
	    
		HBox next_row = new HBox();
	    
	    VBox first_column = new VBox();
	    VBox second_column = new VBox();
	    VBox third_column = new VBox();
	    
	    next_row.getChildren().addAll(first_column,second_column,third_column);
	    
	    Label info_lbl = new Label("Player's Info");
	    Label playerMoney_lbl = new Label("You Have: $" + money);
	    Label from_lbl = new Label("From:");
	    Label to_lbl = new Label("To:");
	    Label buildCost_lbl = new Label("Cost:");
	    Label cost_lbl = new Label("$"+cost);
	    
	    Button build_btn = new Button("Build");
	    
	    TextField fromLeft_tf = new TextField();
	    TextField fromRight_tf = new TextField();
	    TextField toLeft_tf = new TextField();
	    TextField toRight_tf = new TextField();
	    
	    first_column.getChildren().addAll(from_lbl,to_lbl,buildCost_lbl);
	    second_column.getChildren().addAll(fromLeft_tf,toLeft_tf);
	    third_column.getChildren().addAll(fromRight_tf,toRight_tf,build_btn);
	    
	    //changing labels's attributes
	    info_lbl.setFont(Font.font("Arial",15));
	    playerMoney_lbl.setFont(Font.font("Arial",15));
	    from_lbl.setFont(Font.font("Arial",15));
	    to_lbl.setFont(Font.font("Arial",15));
	    buildCost_lbl.setFont(Font.font("Arial",15));
	    build_btn.setFont(Font.font("Arial",15));
	    cost_lbl.setFont(Font.font("Arial",15));
	    
	    //changing TextField's width
	    /*fromLeft_tf.setPrefWidth(80);
	    fromRight_tf.setPrefWidth(80);
	    toLeft_tf.setPrefWidth(80);
	    toRight_tf.setPrefWidth(80);*/
	    
	    
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
	    
	    
	    this.add(info_lbl, 0, 1);
	    this.add(playerMoney_lbl,0,2);
	    
	    this.add(from_lbl,0,3);
	    this.add(fromLeft_tf, 1, 3);
	    this.add(fromRight_tf, 2, 3);
	    
	    this.add(to_lbl, 0, 4);
	    this.add(toLeft_tf, 1, 4);
	    this.add(toRight_tf, 2, 4);
	    
	    this.add(buildCost_lbl, 0, 5);
	    this.add(cost_lbl, 2, 5);
	    
	    
	    this.add(build_btn, 2, 6);
	    
	    this.add(createHelpButton(), 3, 1);
	    
	    this.setVgap(10.0);
	    this.setHgap(5.0);
	    
	    
	    
	}
	
	/**
     * @return a Node instance which is the Help button of the program
     */
    private Node createHelpButton()
    {
      Image helpIcon = new Image(getClass().getResourceAsStream("/img/help.png"));
      ImageView helpImageView = new ImageView(helpIcon);
      helpImageView.setFitHeight(15);
      helpImageView.setFitWidth(15);
      Button helpButton = new Button();
      helpButton.setGraphic(helpImageView);
      return helpButton;
    }
	
}
