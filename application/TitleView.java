package application;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * This class represents the title of the program
 * @author Dung Viet Bui
 *
 */
public class TitleView extends Label {
    /**
     * This method initializes a title for the program.
     */
	TitleView()
	{
		super("Road Builder");
		this.setFont(Font.font("Arial",FontWeight.BOLD,20));
	}
}
