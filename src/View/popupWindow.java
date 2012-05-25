package View;
import Model.Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class popupWindow extends JPanel implements ActionListener {
	private static final long serialVersionUID = -5427647397840827953L;
	
	public static JLabel lines = new JLabel("Lines", SwingConstants.LEFT);
	public static JLabel columns = new JLabel("Columns", SwingConstants.LEFT);
	public static JTextField linesValue = new JTextField();
	public static JTextField columnsValue = new JTextField();
	public static JButton startButton = new JButton("Start");
	
	public static ButtonGroup difficultyTypes = new ButtonGroup();
	public static JRadioButtonMenuItem difficultyEasy = new JRadioButtonMenuItem("Easy");
	public static JRadioButtonMenuItem difficultyIntermediary = new JRadioButtonMenuItem("Intermediary");
	public static JRadioButtonMenuItem difficultyHard = new JRadioButtonMenuItem("Hard");
	
	public static JFrame popupSizeWindow = new JFrame("Configurations");
	
	protected popupWindow() {
		JPanel panel = new JPanel(null);
		difficultyEasy.addActionListener(this);
		difficultyEasy.setActionCommand("setEasy");
		difficultyIntermediary.addActionListener(this);
		difficultyIntermediary.setActionCommand("setIntermediary");
		difficultyHard.addActionListener(this);
		difficultyHard.setActionCommand("setHard");
		difficultyEasy.setSelected(true);
		startButton.addActionListener(this);
		startButton.setActionCommand("start");
		startButton.setMnemonic(KeyEvent.VK_ENTER);
		
		/* Adiciona dificuldades no grupo do option */
		difficultyTypes.add(difficultyEasy);
		difficultyTypes.add(difficultyIntermediary);
		difficultyTypes.add(difficultyHard);
		
		/* Posiciona os itens */
		lines.setBounds(10, 10, 80, 20);
		columns.setBounds(10, 30, 80, 20);
		linesValue.setBounds(90, 10, 80, 20);
		columnsValue.setBounds(90, 30, 80, 20);
		difficultyEasy.setBounds(10, 55, 80, 20);
		difficultyIntermediary.setBounds(10, 75, 120, 20);
		difficultyHard.setBounds(10, 95, 80, 20);
		difficultyEasy.setForeground(Color.BLACK);
		difficultyIntermediary.setForeground(Color.BLACK);
		difficultyHard.setForeground(Color.BLACK);
		startButton.setBounds(190, 95, 100, 20);
		
		/* Adiciona no painel */
		panel.add(linesValue);
		panel.add(columnsValue);
		panel.add(lines);
		panel.add(columns);
		panel.add(difficultyEasy);
		panel.add(difficultyIntermediary);
		panel.add(difficultyHard);
		panel.add(startButton);
		
		/* Configura a janela */
		popupSizeWindow.add(panel);
		popupSizeWindow.setLocationRelativeTo(Window.window);
		popupSizeWindow.setLocation(Window.WIDTH/4, Window.HEIGHT/4);
		popupSizeWindow.setPreferredSize(new Dimension(300, 120));
		popupSizeWindow.setResizable(false);
		popupSizeWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		popupSizeWindow.pack();
		popupSizeWindow.setVisible(true);
	}

	public void actionPerformed(ActionEvent arg0) {
		
		try {
			int lines = Integer.parseInt(linesValue.getText());
			int columns = Integer.parseInt(columnsValue.getText());
			
			if(arg0.getActionCommand().equals("start") && lines > 6 && columns > 6) {
				
				/* Configura as linhas e colunas */
				Main.setSize(lines, columns);
				
				/* Configura a dificuldade */
				String difficultyType = difficultyTypes.getSelection().getActionCommand();
				
				if(difficultyType == "setEasy") {
					Main.setDifficulty(1);
				}else if(difficultyType == "setIntermediary"){
					Main.setDifficulty(2);
				}else if(difficultyType == "setHard"){
					Main.setDifficulty(3);
				}else{
					Main.setDifficulty(1);
				}
				
				Main.generateField();

				popupWindow.popupSizeWindow.dispose();
				Window.window.setEnabled(true);
				Window.gameMenu.setEnabled(true);
			}
		} catch (Exception e) {
		}
		
	}

}
