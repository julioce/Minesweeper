package View;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class Window extends JPanel implements ActionListener {
	private static final long serialVersionUID = 8855087334693558727L;
	
	/* Propriedades da Janela */
	static int WIDTH = 600;
	static int HEIGHT = 480;

	/* Cria tudo: Janela, menu, label, botão... */
	public static JFrame window = new JFrame("IA - Campo Minado");
	public static JPanel panel = new JPanel(null);
	
	public static JMenuBar menu = new JMenuBar();
	public static JMenu gameMenu = new JMenu("Game");
	public static JMenuItem gameExit = new JMenuItem("Exit");
	

	public Window(){
		/* Adiciona menu */
		gameMenu.add(gameExit);
		menu.add(gameMenu);
		
		/* Adiciona ouvintes do menu */
		gameExit.addActionListener(this);
		gameExit.setActionCommand("exit");
		
		/* Adiciona itens na janela principal */
		window.setJMenuBar(menu);
		window.add(this);
		window.add(new Canvas());

		/* Amarra tudo e exibe a janela */
		window.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		window.setFocusTraversalKeysEnabled(true);
		window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.pack();
		window.setVisible(true);
		window.setEnabled(false);
		
		@SuppressWarnings("unused")
		popupWindow popupWindow = new popupWindow();
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		/* saída do jogo */
		if(arg0.getActionCommand().equals("exit")) {
			System.exit(0);
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
