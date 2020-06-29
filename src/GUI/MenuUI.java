package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class MenuUI extends JFrame {
	
	
	private ImageIcon 	imgIniciar = new ImageIcon(getClass().getResource("../ASSETS/iconePlay.png")),
						imgRecorde = new ImageIcon(getClass().getResource("../ASSETS/iconeRecord.png")),
						imgSair = new ImageIcon(getClass().getResource("../ASSETS/iconeFechar.png")),
						imgAjuda = new ImageIcon(getClass().getResource("../ASSETS/iconeAjuda.png"));
	private JLabel lbTextoLogo;
	
	private JButton btnIniciar, btnRecorde, btnInstrucao, btnSair;
	public MenuUI() {
		getContentPane().setLayout(null);
		
		
		
		lbTextoLogo = new JLabel("<html>AVENTURA<br> ECOLÓGICA</html>");
		lbTextoLogo.setForeground(Color.DARK_GRAY);
		lbTextoLogo.setBounds(150,100,900,200);
		lbTextoLogo.setFont(new Font("Algerian", Font.BOLD,60));
		getContentPane().add(lbTextoLogo);
		
		btnIniciar = new JButton();
		btnIniciar.setBounds(50,440,50,50);
		btnIniciar.setIcon(imgIniciar);
		btnIniciar.setOpaque(false);
		btnIniciar.setContentAreaFilled(false);
		btnIniciar.setActionCommand("btnIniciar");
		getContentPane().add(btnIniciar);
		
		btnRecorde = new JButton();
		btnRecorde.setBounds(270, 440, 50, 50);
		btnRecorde.setIcon(imgRecorde);
		btnRecorde.setOpaque(false);
		btnRecorde.setContentAreaFilled(false);
		btnRecorde.setActionCommand("btnRecorde");
		getContentPane().add(btnRecorde);
		
		btnInstrucao = new JButton();
		btnInstrucao.setBounds(500, 440, 50, 50);
		btnInstrucao.setIcon(imgAjuda);
		btnInstrucao.setOpaque(false);
		btnInstrucao.setContentAreaFilled(false);
		btnInstrucao.setActionCommand("mInstrucao");
		getContentPane().add(btnInstrucao);
		
		btnSair = new JButton();
		btnSair.setBounds(540, 20, 50, 50);	
		btnSair.setIcon(imgSair);
		btnSair.setOpaque(false);
		btnSair.setContentAreaFilled(false);
		btnSair.setActionCommand("btnSair");
		getContentPane().add(btnSair);
		
		

		//IMAGEM DE FUNDO\\
	ImageIcon img = new ImageIcon(getClass().getResource("../ASSETS/iconRecicla.png"));
	JLabel lbImg = new JLabel(img);
	lbImg.setBounds(50,0,500,500);
	img.setImage(img.getImage().getScaledInstance(lbImg.getWidth(), lbImg.getHeight(),10));	// redimenciona a imagem
	getContentPane().add(lbImg);
	
	
	
	}
public void setListenerMenu(ActionListener listener) {
		
		
		btnIniciar.addActionListener(listener);
		btnRecorde.addActionListener(listener);
		btnInstrucao.addActionListener(listener);
		btnSair.addActionListener(listener);
	}

}
