package application;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class TitleView extends Label {
	TitleView()
	{
		super("Road Builder");
		this.setFont(Font.font("Arial",FontWeight.BOLD,20));
	}
}
