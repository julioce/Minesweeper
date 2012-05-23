import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class View extends JPanel implements ActionListener {
	private static final long serialVersionUID = 8855087334693558727L;

	/* Cria tudo: Janela, menu, label, botão... */
	public static JFrame window = new JFrame("Trabalho 1 - IA");
	public static JFrame popupSizeWindow = new JFrame("Size of the game");
	
	public static JMenuBar menu = new JMenuBar();
	public static JMenu gameMenu = new JMenu("Game");
	public static JMenuItem gameExit = new JMenuItem("Exit");
	
	public static JMenu difficultyMenu = new JMenu("Difficulty");
	public static ButtonGroup difficultyTypes = new ButtonGroup();
	public static JRadioButtonMenuItem difficultyEasy = new JRadioButtonMenuItem("Easy");
	public static JRadioButtonMenuItem difficultyIntermediary = new JRadioButtonMenuItem("Intermediary");
	public static JRadioButtonMenuItem difficultyHard = new JRadioButtonMenuItem("Hard");
	
	public static JMenu sizeMenu = new JMenu("Size");
	public static JMenuItem setSize = new JMenuItem("Set size");
	public static JLabel lines = new JLabel("Lines", SwingConstants.LEFT);
	public static JLabel columns = new JLabel("Columns", SwingConstants.LEFT);
	public JTextField linesValue = new JTextField();
	public JTextField columnsValue = new JTextField();
	public static JButton sizeOk = new JButton("Ok");
	
	public static JLabel titleLabel = new JLabel();

	public View(){
		/* Label do trabalho */
		titleLabel.setText(Configurations.PROJECT_NAME);
		titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
		titleLabel.setBounds(10, 10, 515, 30);

		/* Adiciona menu */
		difficultyTypes.add(difficultyEasy);
		difficultyTypes.add(difficultyIntermediary);
		difficultyTypes.add(difficultyHard);
		difficultyEasy.setSelected(true);
		difficultyMenu.add(difficultyEasy);
		difficultyMenu.add(difficultyIntermediary);
		difficultyMenu.add(difficultyHard);
		sizeMenu.add(setSize);
		gameMenu.add(gameExit);
		menu.add(gameMenu);
		menu.add(difficultyMenu);
		menu.add(sizeMenu);
		gameExit.addActionListener(this);
		gameExit.setActionCommand("exit");
		setSize.addActionListener(this);
		setSize.setActionCommand("openPopupSizeWindow");
		
		/* Adiciona itens na janela principal */
		window.setJMenuBar(menu);
		window.add(titleLabel);
		window.add(this);
		window.add(new Canvas());

		/* Amarra tudo e exibe a janela */
		window.setPreferredSize(new Dimension(Configurations.WIDTH_SIZE, Configurations.HEIGHT));
		window.setFocusTraversalKeysEnabled(true);
		window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.pack();
		window.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getActionCommand().equals("openPopupSizeWindow")) {
			sizeOk.addActionListener(this);
			sizeOk.setActionCommand("recordSize");
			JPanel panel = new JPanel(null);
			
			lines.setBounds(10, 10, 80, 20);
			columns.setBounds(10, 30, 80, 20);
			linesValue.setBounds(90, 10, 80, 20);
			columnsValue.setBounds(90, 30, 80, 20);
			sizeOk.setBounds(190, 30, 60, 20);
			
			panel.add(linesValue);
			panel.add(columnsValue);
			panel.add(lines);
			panel.add(columns);
			panel.add(sizeOk);
			
			popupSizeWindow.add(panel);
			popupSizeWindow.setLocationRelativeTo(window);
			popupSizeWindow.setLocation(Configurations.WIDTH_SIZE/4, Configurations.HEIGHT/4);  
			popupSizeWindow.setPreferredSize(new Dimension(Configurations.WIDTH_SIZE/2, Configurations.HEIGHT/6));
			popupSizeWindow.setResizable(false);
			popupSizeWindow.pack();
			popupSizeWindow.setVisible(true);
			
		}
		
		if(arg0.getActionCommand().equals("exit")) {
			System.exit(0);
		}
		
		if(arg0.getActionCommand().equals("recordSize")) {
			Main.setSize(linesValue.getText(), columnsValue.getText());
			popupSizeWindow.dispose();
		}
	}
}

class Canvas extends JComponent {
	private static final long serialVersionUID = 6433243819251423039L;

	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	}
}
