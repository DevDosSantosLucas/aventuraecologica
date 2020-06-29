
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JWindow;
import javax.swing.SwingConstants;

public class Splash {
	private  final int LARGURA_IMG = 420;
	private  final int ALTURA_IMG = 250;
	private  final int TEMPO_DE_SPLASH = 5000 ;
	private  final String CAMINHO_GIF = "/ASSETS/loading-gif-free.gif";
		
	JWindow janelaSplash = new JWindow();
	public Splash() {
		
		
		janelaSplash.getContentPane().add(
			 new JLabel(
						"AGUARDE",
						new ImageIcon(getClass().getResource(CAMINHO_GIF)),
							SwingConstants.CENTER
							)
				);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension dimension = toolkit.getScreenSize();
		
		janelaSplash.setBounds(
				(dimension.width  - LARGURA_IMG) /2,
				(dimension.height - ALTURA_IMG) /2,
				 LARGURA_IMG,
				 ALTURA_IMG
				);
		
		janelaSplash.setVisible(true);
		
		try {
			Thread.sleep(TEMPO_DE_SPLASH);
		}catch(InterruptedException e ) {System.err.println(e.getMessage());
		 e.printStackTrace();}
		
		
	}
}
