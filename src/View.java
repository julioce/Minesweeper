import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class View extends JPanel implements ActionListener {
	private static final long serialVersionUID = 8855087334693558727L;

	/* Cria tudo: Janela, entidades, label, botão... */
	public static JFrame window = new JFrame("Trabalho 1 - IA");
	
	public static JLabel titleLabel = new JLabel();

	public View(){
		/* Label do trabalho */
		titleLabel.setText(Configurations.PROJECT_NAME);
		titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
		titleLabel.setBounds(10, 10, 515, 30);

		/* Adiciona na janela principal */
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
		if(arg0.getActionCommand().equals("iniciar")) {
			/* Cria ou reinicia as entidades */
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
