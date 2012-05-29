package View;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.JButton;

public class Button extends JButton {
	private static final long serialVersionUID = 2930913215979598247L;
	
	Color[] array = {Color.BLACK, Color.BLUE, new Color(0, 151, 0), Color.RED, Color.CYAN, Color.GRAY, Color.MAGENTA, Color.PINK, Color.YELLOW};
	
	public static int buttonWidth = 30;
	public static int buttonHeight = 30;
	
	public int value;
	public int i;
	public int j;
	
	public Button(int i, int j, int value){
		this.value = value;
		this.i = i;
		this.j = j;
		
		this.setBounds(i*buttonWidth, j*buttonHeight, buttonWidth, buttonHeight);
		this.setMargin(new Insets(1,1,1,1)); 
		this.setFont(new Font("Arial", Font.BOLD, 12));
		this.setValue(value);
		
		Window.panel.add(this);
	}
	
	public void setValue(int value){
		switch(value){
			case 0:
				this.setText(" ");
				break;
			case -1:
				this.setText("x");
				break;
			default:
				this.setText(Integer.toString(value));
				this.setForeground(array[value]);
				break;
		}
	
	}

}
