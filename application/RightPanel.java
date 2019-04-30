package application;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

/**
 * class that creates the right panel of the road builder application
 * 
 * @author Eli Straight
 */
public class RightPanel extends HBox {

  Label cost_lbl;
  TextField fromLeft_tf;
  TextField fromRight_tf;
  TextField toLeft_tf;
  TextField toRight_tf;
  GUIInformation information;
  Main mainInstance;

  RightPanel(Main mainInstance, GUIInformation information) {

    super();
    this.information = information;
    this.mainInstance = mainInstance;
    User user = information.user;
    
    GridPane top = new GridPane();
    
    // creating components
    Label info_lbl = new Label("Welcome " + user.getName());
    Label playerMoney_lbl = new Label("You Have: $" + user.getMoney());
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

    this.getChildren().add(top);
  }

  public int getFromX() {
    return Integer.parseInt(fromLeft_tf.getText());
  }

  public int getFromY() {
    return Integer.parseInt(fromRight_tf.getText());
  }

  public int getToX() {
    return Integer.parseInt(toLeft_tf.getText());
  }


  public int getToY() {
    return Integer.parseInt(toRight_tf.getText());
  }

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

  private void buildPath() {
    try {
      PathFinding pathFinder = new PathFindingBFS(information.getMap());
      Coordinate start = new Coordinate(getFromX(), getFromY());
      Coordinate finish = new Coordinate(getToX(), getToY());
      pathFinder.buildRoad(information.user, start, finish);
      mainInstance.updateRoot();
    } catch (IllegalArgumentException e) {
      new InvalidInputView(mainInstance.primaryStage,"The coordinates need to be positive integer from 1 to "+information.getMap().length+"!");
    } catch (NotEnoughMoneyException e) {
      new InvalidInputView(mainInstance.primaryStage,
          "You do not have enough money to build the path");
    } catch (Exception e) {
      new InvalidInputView(mainInstance.primaryStage,"The coordinates need to be positive integer from 1 to "+information.getMap().length+"!");
    }

  }
}
