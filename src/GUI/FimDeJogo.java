package GUI;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class FimDeJogo  extends JFrame {
	ImageIcon imgFim = new ImageIcon(getClass().getResource("../imagens/fundoFim.JPG"));
	
	public FimDeJogo() {
	
	

	JLabel lbRecordeTotal = new JLabel("RECORDE:");lbRecordeTotal.setBounds(50,200,200,50);

	JLabel lbRecordeAtual = new JLabel("SEU RECORDE:");lbRecordeAtual.setBounds(50,400,200,50);

	JTextField tfRecordeTotal = new JTextField("0");tfRecordeTotal.setBounds(350,200,200,50);

	JTextField tfRecordeAtual = new JTextField("0");tfRecordeAtual.setBounds(350,400,200,50);

	JLabel lbImg=new JLabel(imgFim);
	lbImg.setBounds(0,0,1000,680);
	imgFim.setImage(imgFim.getImage().getScaledInstance(lbImg.getWidth(),lbImg.getHeight(),1)); // redimenciona
																																					// a
																																					// imagem

	JButton btnIrPontuacao = new JButton("VER PONTOS");btnIrPontuacao.setBounds(300,600,500,50);

	add(lbRecordeTotal);

	
	add(lbRecordeAtual);
	add(tfRecordeTotal);
	add(tfRecordeAtual);
	add(btnIrPontuacao);
	add(lbImg);
	}
	}

