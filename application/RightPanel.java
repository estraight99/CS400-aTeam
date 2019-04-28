package application;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

/**
 * class that creates the right panel of the road builder application
 * @author Eli Straight
 */
public class RightPanel extends GridPane {
    
  Label cost_lbl;
  TextField fromLeft_tf;
  TextField fromRight_tf;
  TextField toLeft_tf;
  TextField toRight_tf;
  GUIInformation information;
  
  RightPanel(GUIInformation information) {
	
    super();
    this.information = information;
    User user = information.user;
    int cost = 0;
    // creating components
    Label info_lbl = new Label("Welcome " + user.getName());
    Label playerMoney_lbl = new Label("You Have: $" + user.getMoney());
    Label from_lbl = new Label("From:");
    Label to_lbl = new Label("To:");
    Label buildCost_lbl = new Label("Cost:");
    cost_lbl = new Label("$" + cost);

    Button build_btn = new Button("Build");

    fromLeft_tf = new TextField();
    fromRight_tf = new TextField();
    toLeft_tf = new TextField();
    toRight_tf = new TextField();

    // Setting default text for text fields
    fromLeft_tf.setPromptText("X");
    fromLeft_tf.setFocusTraversable(false);

    fromRight_tf.setPromptText("Y");
    fromRight_tf.setFocusTraversable(false);

    toLeft_tf.setPromptText("X");
    toLeft_tf.setFocusTraversable(false);

    toRight_tf.setPromptText("Y");
    toRight_tf.setFocusTraversable(false);

    // setting widths
    fromLeft_tf.setMaxWidth(50);
    fromRight_tf.setMaxWidth(50);
    toLeft_tf.setMaxWidth(50);
    toRight_tf.setMaxWidth(50);

    // changing labels's attributes
    info_lbl.setFont(Font.font("Arial", 15));
    playerMoney_lbl.setFont(Font.font("Arial", 15));
    from_lbl.setFont(Font.font("Arial", 15));
    to_lbl.setFont(Font.font("Arial", 15));
    buildCost_lbl.setFont(Font.font("Arial", 15));
    build_btn.setFont(Font.font("Arial", 15));
    cost_lbl.setFont(Font.font("Arial", 15));

    // changing TextField's width
    /*
     * fromLeft_tf.setPrefWidth(80); fromRight_tf.setPrefWidth(80); toLeft_tf.setPrefWidth(80);
     * toRight_tf.setPrefWidth(80);
     */

    /*
     * //setting spacing and adding to boxes from_hbox.setSpacing(20);
     * //from_hbox.getChildren().addAll(from_lbl, fromLeft_tf, fromRight_tf);
     * 
     * //to_hbox.setSpacing(20); //to_hbox.getChildren().addAll(to_lbl, toLeft_tf, toRight_tf);
     * //to_hbox.setMargin(toLeft_tf, new Insets(0, 0, 0, 22));
     * 
     * build_hbox.getChildren().addAll(build_btn); //build_hbox.setAlignment(Pos.BASELINE_RIGHT);
     * 
     * info_hbox.getChildren().addAll(info_lbl); //info_hbox.setAlignment(Pos.BASELINE_CENTER);
     * 
     * rightSide_vbox.setSpacing(25);
     */
    /*
     * rightSide_vbox.getChildren().addAll(info_hbox, playerMoney_lbl, from_hbox, to_hbox,
     * buildCost_lbl, build_hbox);
     */

    // changing the margins for rightSide_vbox's children
    /*
     * rightSide_vbox.setMargin(info_hbox, new Insets(40, 50, 30, 0));
     * rightSide_vbox.setMargin(playerMoney_lbl, new Insets(0, 50, 0, 0));
     * rightSide_vbox.setMargin(from_hbox, new Insets(0, 50, 0, 0));
     * rightSide_vbox.setMargin(to_hbox, new Insets(0, 50, 0, 0));
     * rightSide_vbox.setMargin(build_hbox, new Insets(0, 50, 0, 0));
     */

    // adding elements to the overall panel
    this.add(info_lbl, 0, 1);
    this.add(playerMoney_lbl, 0, 2);

    this.add(from_lbl, 0, 3);
    this.add(fromLeft_tf, 1, 3);
    this.add(fromRight_tf, 2, 3);

    this.add(to_lbl, 0, 4);
    this.add(toLeft_tf, 1, 4);
    this.add(toRight_tf, 2, 4);

    this.add(buildCost_lbl, 0, 5);
    this.add(cost_lbl, 2, 5);

    this.add(build_btn, 2, 6);

    this.setVgap(10.0);
	this.setHgap(5.0);

    }
  
  public int getFromX()
  {
    return Integer.parseInt(fromLeft_tf.getText());
  }
  
  public int getFromY()
  {
    return Integer.parseInt(fromRight_tf.getText());
  }
  
  public int getToX()
  {
    return Integer.parseInt(toLeft_tf.getText());
  }
  
  
  public int getToY()
  {
    return Integer.parseInt(toRight_tf.getText());
  }
  
  private void tryUpdateCostLabel()
  {
    try
    {
      PathFinding pathFinder = new PathFindingBFS(information.getMap());
      Coordinate start = new Coordinate(this.getFromX(),this.getFromY());
      Coordinate finish = new Coordinate(this.getToX(),this.getToY());
      System.out.println("I'm here");
      this.cost_lbl.setText("$"+pathFinder.evaluateCost(start, finish));
    }
    catch (Exception e)
    {
      // do nothing
    }
  }
  
}
