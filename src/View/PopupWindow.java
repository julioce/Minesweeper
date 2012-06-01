package View;
import Controller.Main;
import Model.Field;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class PopupWindow extends JPanel implements ActionListener {
	private static final long serialVersionUID = -5427647397840827953L;
	
	public static JLabel lines = new JLabel("Lines", SwingConstants.LEFT);
	public static JLabel columns = new JLabel("Columns", SwingConstants.LEFT);
	public static JTextField linesValue = new JTextField();
	public static JTextField columnsValue = new JTextField();
	public static JButton startButton = new JButton("Start");
	
	public static JLabel bombQuantity = new JLabel("Bombs", SwingConstants.LEFT);
	public static JTextField bombQuantityValue = new JTextField();
	
	public static ButtonGroup difficultyTypes = new ButtonGroup();
	public static JRadioButton difficultyEasy = new JRadioButton("Easy");
	public static JRadioButton difficultyIntermediary = new JRadioButton("Intermediary");
	public static JRadioButton difficultyHard = new JRadioButton("Hard");
	
	public static JFrame popupWindow = new JFrame("Configurations");
	
	static AlertWindow alertWindow;
	
	public PopupWindow() {
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
		linesValue.setBounds(90, 10, 60, 20);
		columnsValue.setBounds(90, 30, 60, 20);
		bombQuantity.setBounds(10, 50, 80, 20);
		bombQuantityValue.setBounds(90, 50, 60, 20);
		
		difficultyEasy.setBounds(10, 75, 80, 20);
		difficultyIntermediary.setBounds(10, 95, 120, 20);
		difficultyHard.setBounds(10, 115, 80, 20);
		
		startButton.setBounds(185, 110, 100, 20);
		
		/* Adiciona no painel */
		panel.add(linesValue);
		panel.add(columnsValue);
		panel.add(lines);
		panel.add(columns);
		panel.add(bombQuantity);
		panel.add(bombQuantityValue);
		panel.add(difficultyEasy);
		panel.add(difficultyIntermediary);
		panel.add(difficultyHard);
		panel.add(startButton);
		
		/* Configura a janela */
		popupWindow.add(panel);
		popupWindow.setLocationRelativeTo(Window.window);
		popupWindow.setLocation(Window.WIDTH/4, Window.HEIGHT/4);
		popupWindow.setPreferredSize(new Dimension(300, 170));
		popupWindow.setResizable(false);
		popupWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		popupWindow.pack();
		popupWindow.setVisible(true);
	}

	public void actionPerformed(ActionEvent arg0) {
		
		if(arg0.getActionCommand().equals("start")) {
			try {
				int lines = Integer.parseInt(linesValue.getText());
				int columns = Integer.parseInt(columnsValue.getText());
				int bombs = Integer.parseInt(bombQuantityValue.getText());
				
				if(Field.validateUserInput(lines, columns, bombs)){
					/* Configura as linhas e colunas */
					Field.setLines(lines);
					Field.setColumns(columns);
					
					/* Configura a dificuldade */
					String difficultyType = difficultyTypes.getSelection().getActionCommand();
					
					if(difficultyType.equals("setEasy")){
						Field.setDifficulty(1);
					}else if(difficultyType.equals("setIntermediary")){
						Field.setDifficulty(2);
					}else if(difficultyType.equals("setHard")){
						Field.setDifficulty(3);
					}else{
						Field.setDifficulty(1);
					}
					
					/* Configura o número de bombas */
					Field.setBombQuantity(bombs);
					
					/* Gera o campo */
					Main.generateField();

					/* Configura as janelas */
					PopupWindow.popupWindow.dispose();
					Window.window.setEnabled(true);
					Window.gameMenu.setEnabled(true);
					new ReportWindow();
				}else{
					alertWindow = new AlertWindow();
				}
			}catch(Exception e){
			}
			
		}
		
	}

}
