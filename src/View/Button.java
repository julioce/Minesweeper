package View;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.JButton;

public class Button {
	public static int buttonWidth = 30;
	public static int buttonHeight = 30;

	public static void generateField(int x, int y, int value) {
		JButton button = new JButton();
		
		button.setBounds(y*buttonWidth, x*buttonHeight, buttonWidth, buttonHeight);
		button.setMargin(new Insets(1,1,1,1)); 
		button.setFont(new Font("Arial", Font.BOLD, 12));
		
		switch(value){
			case 0:
				button.setText(" ");
				break;
			case -1:
				button.setText("x");
				break;
			default:
				button.setText(Integer.toString(value));
				break;
		}
		
		Window.panel.add(button);
	}

}
