package application;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * This class represents 
 * @author Dung Viet Bui
 *
 */
public class TitleView extends Label {
	TitleView()
	{
		super("Road Builder");
		this.setFont(Font.font("Arial",FontWeight.BOLD,20));
	}
}
