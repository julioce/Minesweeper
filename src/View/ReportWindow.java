package View;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ReportWindow {
	private static final long serialVersionUID = 8855087334693558727L;
	
	/* Propriedades da Janela */
	static int WIDTH = 500;
	static int HEIGHT = 500;
	
	/* Cria tudo: Janela de texarea... */
	public static JFrame reportWindow = new JFrame("IA - Campo Minado - Relatório de Iterações");
	public static JPanel panel = new JPanel(null);
	public static JTextArea reportText = new JTextArea();
	
	public ReportWindow(){
		JScrollPane scroll = new JScrollPane(reportText, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		reportText.setEditable(false);
		scroll.setBounds(5, 5, WIDTH-10, HEIGHT-10);
		
		panel.add(scroll);
		
		/* Amarra tudo e exibe a janela */
		reportWindow.add(panel);
		reportWindow.setLocationRelativeTo(PopupWindow.popupWindow);
		reportWindow.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		reportWindow.setFocusTraversalKeysEnabled(true);
		reportWindow.setResizable(false);
		reportWindow.pack();
		reportWindow.setVisible(true);
	}
	
	public static void addText(String text){
		reportText.append(text);
	}
}
