package application;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * class that creates the right panel of the road builder application
 * 
 * @author Eli Straight
 */
public class RightPanel extends VBox {

  Label cost_lbl; // the label that displays the cost of to build the path
  TextField fromLeft_tf; // the text field that contains the x coordinate of the start
  TextField fromRight_tf; // the text field that contains the y coordinate of the start
  TextField toLeft_tf; // the text field that contains the x coordinate of the end
  TextField toRight_tf; // the text field that contains the y coordinate of the end
  GUIInformation information; // this contains the information for the GUI
  Main mainInstance; // this is the current Main instance of the program

  /**
   * This method creates a new instance of RightPanel with the current Main instance,
   * GUIInformation, and the text fields from the previous RightPanel
   * 
   * @param mainInstance is the running Main instance
   * @param information  is the information of the current game
   * @param field        is the text fields from the previous RightPanel
   */
  RightPanel(Main mainInstance, GUIInformation information, List<TextField> field) {
    this(mainInstance, information);
    this.fromLeft_tf.setText(field.get(0).getText());
    this.fromRight_tf.setText(field.get(1).getText());
    this.toLeft_tf.setText(field.get(2).getText());
    this.toRight_tf.setText(field.get(3).getText());
  }

  /**
   * This method creates a completely new instance of RightPanel.
   * 
   * @param mainInstance is the running Main instance
   * @param information  is the information of the current game
   */
  RightPanel(Main mainInstance, GUIInformation information) {

    super();
    this.information = information;
    this.mainInstance = mainInstance;
    User user = information.getUser();

    GridPane top = new GridPane();

    // creating components
    Label info_lbl = new Label("Welcome " + user.getName());
    Label playerMoney_lbl = new Label("You Have: ");
    Label money_lbl = new Label("$" + user.getMoney());
    Label from_lbl = new Label("From:");
    Label to_lbl = new Label("To:");
    Label buildCost_lbl = new Label("Cost:");
    cost_lbl = new Label("$0");

    Button build_btn = new Button("Build");
    build_btn.setOnAction(event -> {
      buildPath();
      // information.topLeft =
    });

    fromLeft_tf = new TextField();
    fromRight_tf = new TextField();
    toLeft_tf = new TextField();
    toRight_tf = new TextField();

    ChangeListener<String> listener = new ChangeListener<String>() {
      public void changed(ObservableValue<? extends String> observable, String oldValue,
          String newValue) {
        UpdateCostLabel();
      }
    };
    // Setting default text for text fieldsand adding listeners to upfate cost when text is changed
    fromLeft_tf.setPromptText("X");
    fromLeft_tf.setFocusTraversable(false);
    fromLeft_tf.textProperty().addListener(listener);

    fromRight_tf.setPromptText("Y");
    fromRight_tf.setFocusTraversable(false);
    fromRight_tf.textProperty().addListener(listener);

    toLeft_tf.setPromptText("X");
    toLeft_tf.setFocusTraversable(false);
    toLeft_tf.textProperty().addListener(listener);

    toRight_tf.setPromptText("Y");
    toRight_tf.setFocusTraversable(false);
    toRight_tf.textProperty().addListener(listener);

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

    // adding elements to the overall panel
    top.add(info_lbl, 0, 1);
    top.add(playerMoney_lbl, 0, 2);
    top.add(money_lbl, 1, 2);

    top.add(from_lbl, 0, 3);
    top.add(fromLeft_tf, 1, 3);
    top.add(fromRight_tf, 2, 3);

    top.add(to_lbl, 0, 4);
    top.add(toLeft_tf, 1, 4);
    top.add(toRight_tf, 2, 4);

    top.add(buildCost_lbl, 0, 5);
    top.add(cost_lbl, 2, 5);

    top.add(build_btn, 2, 6);

    top.setVgap(10.0);
    top.setHgap(5.0);

    this.getChildren().addAll(top,
        information.currentLocationView(fromLeft_tf, fromRight_tf, toLeft_tf, toRight_tf));
  }


  /**
   * @return the x coordinate of the starting point
   */
  public int getFromX() {
    return Integer.parseInt(fromLeft_tf.getText());
  }

  /**
   * @return the y coordinate of the starting point
   */
  public int getFromY() {
    return Integer.parseInt(fromRight_tf.getText());
  }

  /**
   * @return the x coordinate of the ending point
   */
  public int getToX() {
    return Integer.parseInt(toLeft_tf.getText());
  }


  /**
   * @return the y coordinate of the ending point
   */
  public int getToY() {
    return Integer.parseInt(toRight_tf.getText());
  }

  /**
   * This method updates the cost label when the information in the text fields are changed
   */
  private void UpdateCostLabel() {
    try {
      PathFinding pathFinder = new PathFindingBFS(information.getMap());
      Coordinate start = new Coordinate(getFromX(), getFromY());
      Coordinate finish = new Coordinate(getToX(), getToY());
      cost_lbl.setText("$" + pathFinder.evaluateCost(start, finish));
    } catch (Exception e) {
      // do nothing
    }
  }

  /**
   * This method builds the cheapest-shortest path to the graph when there is a signal from the
   * Build button.
   */
  private void buildPath() {
    try {
      PathFinding pathFinder = new PathFindingBFS(information.getMap());
      Coordinate start = new Coordinate(getFromX(), getFromY());
      Coordinate finish = new Coordinate(getToX(), getToY());
      pathFinder.buildRoad(information.getUser(), start, finish);
      mainInstance.updateRoot();
    } catch (IllegalArgumentException e) {
      new InvalidInputView(mainInstance.primaryStage, "Invalid Input",
          "The coordinates need to be positive integer from 1 to "
              + information.getMap().getLength() + "!");
    } catch (NotEnoughMoneyException e) {
      new InvalidInputView(mainInstance.primaryStage, "Not Enough Money",
          "You do not have enough money to build the path");
    } catch (Exception e) {
      new InvalidInputView(mainInstance.primaryStage, "Invalid Input",
          "The coordinates need to be positive integer from 1 to "
              + information.getMap().getLength() + "!");
    }

  }

  /**
   * @return a list of current text fields, which will be used in the new RightPanel
   */
  public List<TextField> getTextField() {
    List<TextField> result = new ArrayList<>();
    result.add(fromLeft_tf);
    result.add(fromRight_tf);
    result.add(toLeft_tf);
    result.add(toRight_tf);
    return result;
  }
}
