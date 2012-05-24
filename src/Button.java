import java.awt.Font;
import java.awt.Insets;

import javax.swing.JButton;

public class Button {
	protected static int buttonWidth = 30;
	protected static int buttonHeight = 30;

	public static void generateField(int x, int y, int value) {
		JButton button = new JButton();
		
		button.setBounds(x*buttonWidth, y*buttonHeight, buttonWidth, buttonHeight);
		button.setMargin(new Insets(1,1,1,1)); 
		button.setFont(new Font("Arial", Font.PLAIN, 10));
		
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
		
		View.window.add(button);
	}

}
