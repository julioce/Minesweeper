package View;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AlertWindow implements ActionListener{
	
	public static JLabel message = new JLabel("Invalid values!");
	public static JButton okButton = new JButton("Ok");
	public static JFrame alertWindow = new JFrame("Error");
	
	public AlertWindow(){
		JPanel panel = new JPanel(null);

		okButton.addActionListener(this);
		okButton.setActionCommand("Ok");
		okButton.setMnemonic(KeyEvent.VK_ENTER);
		
		message.setBounds(80, 20, 100, 20);
		okButton.setBounds(80, 50, 100, 20);
		
		panel.add(message);
		panel.add(okButton);
		
		/* Configura a janela */
		PopupWindow.popupWindow.setEnabled(false);
		alertWindow.add(panel);
		alertWindow.setLocationRelativeTo(PopupWindow.popupWindow);
		alertWindow.setLocation(Window.WIDTH/4+20, Window.HEIGHT/4+10);
		alertWindow.setPreferredSize(new Dimension(270, 100));
		alertWindow.setResizable(false);
		alertWindow.pack();
		alertWindow.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Ok")){
			PopupWindow.popupWindow.setEnabled(true);
			alertWindow.dispose();
		}
	}
}
