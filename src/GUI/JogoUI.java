package GUI;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import GERENCIA.Controle;
//import modelo.Aluno;
import MODELO.Jogo;
import MODELO.Pontos;
import MODELO.Recorde;
import modelo.Aluno;



@SuppressWarnings("serial")
public class JogoUI extends JFrame  {
	
	
	public  static final int FACIL =0;
	public  static final int MEDIO = 1 ;
	public  static final int DIFICIL =2 ;
	
	public boolean mousePress = false,
					acertou = false,
					fimDoJogo = false;
	Timer timer;
    public int porcentagem;
    public JProgressBar progressBarVida;
	
	public JButton 	btnImgLixo1, btnImgLixo2, btnImgLixo3, btnImgLixo4,
					btnImgLixo5, btnImgLixo6, btnImgLixo7, btnImgLixo8, btnImgLixo9;
	


	public ImageIcon imgObjeto ;
	
	public JButton  lbObjeto = null ;
	private JLabel lbImg;
	
	private JPanel painelJogo = null;

	public JButton menuVoltar, mnReiniciar,mnPausar,
					mnSom,mnFechar;
	
	private JTextField tfRecorde,tfErrou;
	

	private OpcaoUI opcao;
	private JogoUI  jogo;
	private Controle ctrl= new Controle() ;
	public boolean play = false, function = false;
	@SuppressWarnings("unused")
	private int nivelDoJogo;
	private ImageIcon img = new ImageIcon(getClass().getResource("../ASSETS/fundoRua2.JPG"));
		//	ImageIcon imgFim = new ImageIcon(getClass().getResource("../ASSETS/fundoFim.JPG"));
//.....imagens do lixo aberto e fechado....\\
			private ImageIcon
			lixoOrganicoF= new ImageIcon(getClass().getResource
					("../ASSETS/Imagens-tipos-lixos/lixoFechadoOrganico.png")),
			lixoOrganicoA= new ImageIcon(getClass().getResource
					("../ASSETS/Imagens-tipos-lixos/lixoAbertoOrganico.png")),
			lixoPapelF= new ImageIcon(getClass().getResource
					("../ASSETS/Imagens-tipos-lixos/lixoFechadoPapel.png")),
			lixoPapelA= new ImageIcon(getClass().getResource
					("../ASSETS/Imagens-tipos-lixos/lixoAbertoPapel.png")),
			lixoPlasticoF= new ImageIcon(getClass().getResource
					("../ASSETS/Imagens-tipos-lixos/lixoFechadoPlastico.png")),
			lixoPlasticoA= new ImageIcon(getClass().getResource
					("../ASSETS/Imagens-tipos-lixos/lixoAbertoPlastico.png")),
			lixoVidroF= new ImageIcon(getClass().getResource
					("../ASSETS/Imagens-tipos-lixos/lixoFechadoVidro.png")),
			lixoVidroA= new ImageIcon(getClass().getResource
					("../ASSETS/Imagens-tipos-lixos/lixoAbertoVidro.png")),
			lixoMetalF= new ImageIcon(getClass().getResource
					("../ASSETS/Imagens-tipos-lixos/lixoFechadoMetal.png")),
			lixoMetalA= new ImageIcon(getClass().getResource
					("../ASSETS/Imagens-tipos-lixos/lixoAbertoMetal.png")),
			lixoNaoReciclaF= new ImageIcon(getClass().getResource
					("../ASSETS/Imagens-tipos-lixos/lixoFechadoNaoReciclaveis.png")),
			lixoNaoReciclaA= new ImageIcon(getClass().getResource
					("../ASSETS/Imagens-tipos-lixos/lixoAbertoNaoReciclaveis.png")),
			lixoPerigososF= new ImageIcon(getClass().getResource
					("../ASSETS/Imagens-tipos-lixos/lixoFechadoResiduosPerigosos.png")),
			lixoPerigososA= new ImageIcon(getClass().getResource
					("../ASSETS/Imagens-tipos-lixos/lixoAbertoResiduosPerigosos.png")),
			lixoMadeiraF= new ImageIcon(getClass().getResource
					("../ASSETS/Imagens-tipos-lixos/lixoFechadoMadeira.png")),
			lixoMadeiraA= new ImageIcon(getClass().getResource
					("../ASSETS/Imagens-tipos-lixos/lixoAbertoMadeira.png")),
			lixoSaudeF= new ImageIcon(getClass().getResource
					("../ASSETS/Imagens-tipos-lixos/lixoFechadoServiçosDeSaude.png")),
			lixoSaudeA= new ImageIcon(getClass().getResource
					("../ASSETS/Imagens-tipos-lixos/lixoAbertoServiçosDeSaude.png"));
			
	
	public JogoUI(int nivelDoJogo ) {
		this.nivelDoJogo = nivelDoJogo;

		
		switch (nivelDoJogo) {
		case FACIL:
			this.setContentPane(getPainelDoJogoFacil());
			getContentPane().setLayout(null);
			tempoVida();
			this.setContentPane(getJogo());		
			break;
			
		case MEDIO:
			this.setContentPane(getPainelDoJogoMedio());
			getContentPane().setLayout(null);
			tempoVida();
			this.setContentPane(getJogo());
			break;
			
		case DIFICIL:
			this.setContentPane(getPainelDoJogoDificil());
			getContentPane().setLayout(null);
			tempoVida();
			this.setContentPane(getJogo());
			break;
		}
	}
	
	public void tempoVida() {
		progressBarVida = new JProgressBar();
    	progressBarVida.setBounds(0,680,1000,20);
    	//ctrl.vidaDoJogo();
    	porcentagem = 100;
        timer = new Timer(100/*velocidade(tempo para acabar a vida)*/, new ActionListener() {
        	
            @Override											//levar para o controle.
            public void actionPerformed(ActionEvent e) {
                porcentagem--;
                if(porcentagem > 50)	progressBarVida.setForeground(Color.green);
                if(porcentagem <= 50)  	progressBarVida.setForeground(Color.yellow);
                if(porcentagem <= 25)	progressBarVida.setForeground(Color.red);

                progressBarVida.setValue(porcentagem);
                if (porcentagem <= 0) {
                    timer.stop();
                   // fimDoJogo();
                 // JOptionPane.showMessageDialog(null,"GAME OVER" );	//ESTA APARECENDO ERRADO
//	             jogo= new JogoUI(OpcaoUI.FIM_DE_JOGO);
                   
                    fimDoJogo();
                    
                 
                }
            }
        });   
       timer.start();
    	//timer.setActionCommand("mTimer");
       
//        if(porcentagem==0)
//        	JOptionPane.showMessageDialog(null,"GAME OVER" );
//        jogo= new JogoUI(OpcaoUI.FIM_DE_JOGO);
	    
        add(progressBarVida);
	}

	public void fimDoJogo() {
        dispose();
		 opcao= new OpcaoUI(OpcaoUI.FIM_DE_JOGO);
//		 ctrl.abrirOpcao();
//		FimDeJogo fimDeJogo = new FimDeJogo(); 
		
//		JFrame fimDeJogo = new JFrame();
//		fimDeJogo.setUndecorated(true);		//esconde ( - [] X)
        opcao.setSize(1000,700);		
        opcao.setLocationRelativeTo(null);
        opcao.setLayout(null);
        opcao.setVisible(true);
//        dispose();
//    	JLabel lbRecordeTotal= new JLabel("RECORDE:");
//		lbRecordeTotal.setBounds(50,200,200,50);
//		
//		JLabel lbRecordeAtual= new JLabel("SEU RECORDE:");
//		lbRecordeAtual.setBounds(50,400,200,50);
//		
//		JTextField tfRecordeTotal= new JTextField("0");
//		tfRecordeTotal.setBounds(350,200,200,50);
//		
//		JTextField tfRecordeAtual= new JTextField("0");
//		tfRecordeAtual.setBounds(350,400,200,50);
//		
//	
//		lbImg = new JLabel(imgFim);
//		lbImg.setBounds(0,0,1000,680);
//		img.setImage(img.getImage().getScaledInstance(lbImg.getWidth(), lbImg.getHeight(),1));	// redimenciona a imagem
//	
//		JButton btnIrPontuacao = new JButton("VER PONTOS");
//		btnIrPontuacao.setBounds(300,600,500,50);
//
//		fimDeJogo.add(lbRecordeTotal);
//		fimDeJogo.add(lbRecordeAtual);
//		fimDeJogo.add(tfRecordeTotal);
//		fimDeJogo.add(tfRecordeAtual);
//		fimDeJogo.add(btnIrPontuacao);
//		fimDeJogo.add(menuVoltar);
//		
//		fimDeJogo.add(mnFechar);
//		fimDeJogo.add(lbImg);
	
	}

		public JPanel  getJogo() {
			//MENU\\
			ImageIcon imgMenu = new ImageIcon(getClass().getResource("../ASSETS/iconeHome.png"));
			ImageIcon imgReiniciar = new ImageIcon(getClass().getResource("../ASSETS/iconeReiniciar.png"));
			ImageIcon imgPausar = new ImageIcon(getClass().getResource("../ASSETS/iconePause.png"));
			ImageIcon imgRecord = new ImageIcon(getClass().getResource("../ASSETS/iconeRecord.png"));
			ImageIcon imgErrou = new ImageIcon(getClass().getResource("../ASSETS/iconeDeslike.png"));
			ImageIcon imgFechar = new ImageIcon(getClass().getResource("../ASSETS/iconeFechar.png"));
			ImageIcon imgSom = new ImageIcon(getClass().getResource("../ASSETS/iconeSom.png"));
			
			

			
			menuVoltar = new JButton();
			menuVoltar.setIcon(imgMenu);
			menuVoltar.setBounds(0,0,50,50);
			menuVoltar.setBorderPainted(false);
			menuVoltar.setContentAreaFilled(false);
			menuVoltar.setActionCommand("mMenuVoltar");
			painelJogo.add(menuVoltar);
			
			
			mnReiniciar = new JButton();
			mnReiniciar.setIcon(imgReiniciar);
			mnReiniciar.setBounds(50,0,50,50);
			mnReiniciar.setBorderPainted(false);
			mnReiniciar.setContentAreaFilled(false);
			mnReiniciar.setActionCommand("mReiniciar");
			painelJogo.add(mnReiniciar);
			
			mnPausar = new JButton();
			mnPausar.setIcon(imgPausar);
			mnPausar.setBounds(100,0,50,50);
			mnPausar.setBorderPainted(false);
			mnPausar.setContentAreaFilled(false);
			mnPausar.setActionCommand("mPausar");
			painelJogo.add(mnPausar);
				
			JLabel lbRecord = new JLabel(imgRecord);
			lbRecord.setBounds(5, 50,40,40);
			painelJogo.add(lbRecord);
			
			tfRecorde = new JTextField ("0");
			tfRecorde.setBounds(50,50,150,40);
			tfRecorde.setForeground(Color.yellow);
			tfRecorde.setBackground(Color.darkGray);
			tfRecorde.setFont(new Font("Arial", Font.BOLD,30));
			tfRecorde.setEnabled(false);
			painelJogo.add(tfRecorde);

			JLabel lbErro = new JLabel(imgErrou);
			lbErro.setBounds(5,100,40,40);
			painelJogo.add(lbErro);

			tfErrou = new JTextField("0");	
			tfErrou.setBounds(50, 100,150, 40);
			tfErrou.setForeground(Color.red);
			tfErrou.setBackground(Color.darkGray);
			tfErrou.setFont(new Font("Arial", Font.BOLD,30));
			tfErrou.setEnabled(false);
			painelJogo.add(tfErrou);
					
			mnSom = new JButton();
			mnSom.setIcon(imgSom);
			mnSom.setBounds(150,0,50,50);
			mnSom.setBorderPainted(false);
			mnSom.setContentAreaFilled(false);
			painelJogo.add(mnSom);
			
			mnFechar = new JButton();
			mnFechar.setIcon(imgFechar);
			mnFechar.setActionCommand("mFechar");
			mnFechar.setBounds(950,0,50,50);
			mnFechar.setBorderPainted(false);
			mnFechar.setContentAreaFilled(false);
			painelJogo.add(mnFechar);
			
			//IMAGEM DE FUNDO\\
	
			lbImg = new JLabel(img);
			lbImg.setBounds(0,0,1000,680);
			img.setImage(img.getImage().getScaledInstance(lbImg.getWidth(), lbImg.getHeight(),1));	// redimenciona a imagem

			painelJogo.add(lbImg);

			return painelJogo;
		}

		

		/*
		 * 	NIVEIS DO JOGO
		 */
		public JPanel getPainelDoJogoFacil() {
				
//			if( painelJogo == null) {
				painelJogo = new JPanel();
				this.setContentPane(labelObjetos());
				painelJogo.setLayout(new BorderLayout());
				painelJogo.add(btnLixo1(), null);
				painelJogo.add(btnLixo2(), null);
				painelJogo.add(btnLixo3(), null);
				painelJogo.add(btnLixo4(), null);
				
				
				
//			}
			return painelJogo;
	
		}
		public JPanel getPainelDoJogoMedio() {
				
			if( painelJogo == null) {
				painelJogo = new JPanel();
				this.setContentPane(labelObjetos());
				painelJogo.setLayout(new BorderLayout());
				painelJogo.add(btnLixo1(), null);
				painelJogo.add(btnLixo2(), null);
				painelJogo.add(btnLixo3(), null);
				painelJogo.add(btnLixo4(), null);
				painelJogo.add(btnLixo5(), null);
				painelJogo.add(btnLixo6(), null);
			
			}
			return painelJogo;
	
		}
		
		public JPanel getPainelDoJogoDificil() {
			
		if( painelJogo == null) {
			painelJogo = new JPanel();
			this.setContentPane(labelObjetos());
			painelJogo.setLayout(new BorderLayout());
			painelJogo.add(btnLixo1(), null);
			painelJogo.add(btnLixo2(), null);
			painelJogo.add(btnLixo3(), null);
			painelJogo.add(btnLixo4(), null);
			painelJogo.add(btnLixo5(), null);
			painelJogo.add(btnLixo6(), null);
//			painelJogo.add(btnLixo7(), null);	/*
//			painelJogo.add(btnLixo8(), null);	*	FALTA 	ORGANIZAR OS Bounds dos lixos
//			painelJogo.add(btnLixo9(), null);	*/
			
		}
		return painelJogo;
	}

		/*
		 * 		//JLabels RANDOMICAS
		 */
		public JPanel labelObjetos()  {
		
	     
			lbObjeto = new JButton();	
			lbObjeto.setBorderPainted(false);
			lbObjeto.setContentAreaFilled(false);
			lbObjeto.setBounds(450,500,200,200);
			//imgObjeto.setImage(imgObjeto.getImage()	/*
			//.getScaledInstance(lbObjeto.getWidth(),	*	redimenciona a imagem
			//lbObjeto.getHeight(),10));				/* 	(dando erro).
			painelJogo.add(lbObjeto);
			
			return painelJogo;
	}
		
		/*
		 * 		 TODOS OS LIXOS:
		 */
	
	public JButton btnLixo1() {
//			//ORGANICO- MARROM\\
			btnImgLixo1 = new JButton();
			btnImgLixo1.setIcon(lixoOrganicoF);
			btnImgLixo1.setBorderPainted(false);
			btnImgLixo1.setContentAreaFilled(false);
			btnImgLixo1.setBounds(200,130,150,280);
			btnImgLixo1.setActionCommand("mLixo1");
			lixoOrganicoF.setImage(lixoOrganicoF.getImage()
					.getScaledInstance(btnImgLixo1.getWidth(), btnImgLixo1.getHeight(),1));

			btnImgLixo1.addMouseListener(new MouseListener() {
				// TROCA DE .\\
				@Override
				public void mouseEntered(MouseEvent arg0) {
					btnImgLixo1.setIcon(lixoOrganicoA);
				}
				@Override
				public void mouseExited(MouseEvent arg0) {
					btnImgLixo1.setIcon(lixoOrganicoF);
				}
				@Override
				public void mouseClicked(MouseEvent e) {
				}
				@Override
				public void mousePressed(MouseEvent e) {					
				}
				@Override
				public void mouseReleased(MouseEvent e) {
				}
			});
			return btnImgLixo1;
		
		}
	
	public JButton btnLixo2() {
//			//PAPEL- AZUL\\
			btnImgLixo2 = new JButton();
			btnImgLixo2.setIcon(lixoPapelF);
			btnImgLixo2.setBorderPainted(false);
			btnImgLixo2.setContentAreaFilled(false);
			btnImgLixo2.setBounds(350,130,150,280);
			btnImgLixo2.setActionCommand("mLixo2");
			lixoPapelF.setImage(lixoPapelF.getImage()
					.getScaledInstance(btnImgLixo2.getWidth(), btnImgLixo2.getHeight(),1));
			btnImgLixo2.addMouseListener(new MouseListener() {
				// TROCA DE IMAGENS.\\
				@Override
				public void mouseEntered(MouseEvent arg0) {
					btnImgLixo2.setIcon(lixoPapelA);
				}
				public void mouseExited(MouseEvent arg0) {
					btnImgLixo2.setIcon(lixoPapelF);
				}
				@Override
				public void mouseClicked(MouseEvent e) {
				}
				@Override
				public void mousePressed(MouseEvent e) {
				}
				@Override
				public void mouseReleased(MouseEvent e) {
				}
			});
			return btnImgLixo2;
		}
	
	public JButton btnLixo3() {
//			//PLASTICO - VERMELHO\\
			btnImgLixo3 = new JButton();
			btnImgLixo3.setIcon(lixoPlasticoF);
			btnImgLixo3.setBorderPainted(false);
			btnImgLixo3.setContentAreaFilled(false);
			btnImgLixo3.setBounds(500,130,150,280);
			btnImgLixo3.setActionCommand("mLixo3");
			lixoPlasticoF.setImage(lixoPlasticoF.getImage()
					.getScaledInstance(btnImgLixo3.getWidth(), btnImgLixo3.getHeight(),1));
			btnImgLixo3.addMouseListener(new MouseListener() {
				// TROCA DE IMAGENS.\\
				@Override
				public void mouseEntered(MouseEvent arg0) {
					btnImgLixo3.setIcon(lixoPlasticoA);
				}
				public void mouseExited(MouseEvent arg0) {
					btnImgLixo3.setIcon(lixoPlasticoF);
				}
				@Override
				public void mouseClicked(MouseEvent e) {
				}
				@Override
				public void mousePressed(MouseEvent e) {
				}
				@Override
				public void mouseReleased(MouseEvent e) {
				}
			});
			
			return btnImgLixo3;
		}
	
	public JButton btnLixo4() {
//			//VIDRO-VERDE\\
			btnImgLixo4 = new JButton();
			btnImgLixo4.setIcon(lixoVidroF);
			btnImgLixo4.setBorderPainted(false);
			btnImgLixo4.setContentAreaFilled(false);
			btnImgLixo4.setBounds(650,130,150,280);
			btnImgLixo4.setActionCommand("mLixo4");
			lixoVidroF.setImage(lixoVidroF.getImage()
					.getScaledInstance(btnImgLixo4.getWidth(), btnImgLixo4.getHeight(),1));
			btnImgLixo4.addMouseListener(new MouseListener() {
				@Override
				public void mouseEntered(MouseEvent arg0) {
					btnImgLixo4.setIcon(lixoVidroA);
				}
				public void mouseExited(MouseEvent arg0) {
					btnImgLixo4.setIcon(lixoVidroF);
				}
				@Override
				public void mouseClicked(MouseEvent e) {
				}
				@Override
				public void mousePressed(MouseEvent e) {
				}
				@Override
				public void mouseReleased(MouseEvent e) {	
				}
			});
			return btnImgLixo4;
		}
	
	public JButton btnLixo5() {
//			//METAL - AMARELO\\
			btnImgLixo5 = new JButton();
			btnImgLixo5.setIcon(lixoMetalF);
			btnImgLixo5.setBorderPainted(false);
			btnImgLixo5.setContentAreaFilled(false);
			btnImgLixo5.setBounds(50,130,150,280);
			btnImgLixo5.setActionCommand("mLixo5");
			lixoMetalF.setImage(lixoMetalF.getImage()
					.getScaledInstance(btnImgLixo5.getWidth(), btnImgLixo5.getHeight(),1));
			btnImgLixo5.addMouseListener(new MouseListener() {
				@Override
				public void mouseEntered(MouseEvent arg0) {
					btnImgLixo5.setIcon(lixoMetalA);
				}
				public void mouseExited(MouseEvent arg0) {
					btnImgLixo5.setIcon(lixoMetalF);
				}
				@Override
				public void mouseClicked(MouseEvent e) {
				}
				@Override
				public void mousePressed(MouseEvent e) {
				}
				@Override
				public void mouseReleased(MouseEvent e) {
				}
			});
			return btnImgLixo5;
		}
	public JButton btnLixo6() {
//			//NÃO RECICLAVEL - CINZA\\
			btnImgLixo6 = new JButton();
			btnImgLixo6.setIcon(lixoNaoReciclaF);
			btnImgLixo6.setBorderPainted(false);
			btnImgLixo6.setContentAreaFilled(false);
			btnImgLixo6.setBounds(800,130,150,280);
			btnImgLixo6.setActionCommand("mLixo6");
			lixoNaoReciclaF.setImage(lixoNaoReciclaF.getImage()
					.getScaledInstance(btnImgLixo6.getWidth(), btnImgLixo6.getHeight(),1));
			btnImgLixo6.addMouseListener(new MouseListener() {
				@Override
				public void mouseEntered(MouseEvent arg0) {
					btnImgLixo6.setIcon(lixoNaoReciclaA);
				}
				public void mouseExited(MouseEvent arg0) {
					btnImgLixo6.setIcon(lixoNaoReciclaF);
				}
				@Override
				public void mouseClicked(MouseEvent e) {
				}
				@Override
				public void mousePressed(MouseEvent e) {
				}
				@Override
				public void mouseReleased(MouseEvent e) {	
				}
			});
			return btnImgLixo6;
		}
	public JButton btnLixo7() {
//		//RESIDUOS PERIGOSOS-LARANJA\\
		btnImgLixo7 = new JButton();
		btnImgLixo7.setIcon(lixoPerigososF);
		btnImgLixo7.setBorderPainted(false);
		btnImgLixo7.setContentAreaFilled(false);
		btnImgLixo7.setBounds(650,130,150,280);
		lixoPerigososF.setImage(lixoPerigososF.getImage()
				.getScaledInstance(btnImgLixo7.getWidth(), btnImgLixo7.getHeight(),1));
		btnImgLixo7.addMouseListener(new MouseListener() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnImgLixo7.setIcon(lixoPerigososA);
			}
			public void mouseExited(MouseEvent arg0) {
				btnImgLixo7.setIcon(lixoPerigososF);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
			}
			@Override
			public void mousePressed(MouseEvent e) {	
			}
			@Override
			public void mouseReleased(MouseEvent e) {
			}		
		});
		return btnImgLixo7;
	}
public JButton btnLixo8() {
//		//PRETO - MADEIRA\\
		btnImgLixo8 = new JButton();
		btnImgLixo8.setIcon(lixoMadeiraF);
		btnImgLixo8.setBorderPainted(false);
		btnImgLixo8.setContentAreaFilled(false);
		btnImgLixo8.setBounds(50,130,150,280);
		lixoMadeiraF.setImage(lixoMadeiraF.getImage()
				.getScaledInstance(btnImgLixo8.getWidth(), btnImgLixo8.getHeight(),1));
		btnImgLixo8.addMouseListener(new MouseListener() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnImgLixo8.setIcon(lixoMadeiraA);
			}
			public void mouseExited(MouseEvent arg0) {
				btnImgLixo8.setIcon(lixoMadeiraF);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
			}
			@Override
			public void mousePressed(MouseEvent e) {
			}
			@Override
			public void mouseReleased(MouseEvent e) {	
			}
		});
		return btnImgLixo8;
	}
public JButton btnLixo9() {
//		//SERVIÇOS DE SAÚDE - BRANCO\\
		btnImgLixo9 = new JButton(lixoSaudeF);
		btnImgLixo9.setBorderPainted(false);
		btnImgLixo9.setContentAreaFilled(false);
		btnImgLixo9.setBounds(800,130,150,280);
		lixoSaudeF.setImage(lixoSaudeF.getImage()
				.getScaledInstance(btnImgLixo9.getWidth(), btnImgLixo9.getHeight(),1));
		btnImgLixo8.addMouseListener(new MouseListener() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				btnImgLixo9.setIcon(lixoSaudeA);
			}
			public void mouseExited(MouseEvent arg0) {
				btnImgLixo9.setIcon(lixoSaudeF);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
			}
			@Override
			public void mousePressed(MouseEvent e) {
			}
			@Override
			public void mouseReleased(MouseEvent e) {
			}	
		});
		return btnImgLixo9;
	}

public void setListenerJogo(ActionListener listener) {
		//CHAMA OS BOTÕES PARA A CLASSE CONTROLE
	menuVoltar.addActionListener(listener);
	mnReiniciar.addActionListener(listener);
	mnPausar.addActionListener(listener);
	mnFechar.addActionListener(listener);
}
	public void setListenerJogoF(ActionListener listener) {
		//CHAMA OS BOTÕES (LIXOS) DO NÍVEL FÁCIL PARA A CLASSE CONTROLE
		btnImgLixo1.addActionListener(listener);
		btnImgLixo2.addActionListener(listener);
		btnImgLixo3.addActionListener(listener);
		btnImgLixo4.addActionListener(listener);
		
		
		
	
	}
	public void setListenerJogoM(ActionListener listener) {
		//CHAMA OS BOTÕES (LIXOS) DO NÍVEL MÉDIO PARA A CLASSE CONTROLE
		btnImgLixo1.addActionListener(listener);
		btnImgLixo2.addActionListener(listener);
		btnImgLixo3.addActionListener(listener);
		btnImgLixo4.addActionListener(listener);
		btnImgLixo5.addActionListener(listener);
		btnImgLixo6.addActionListener(listener);
	}


		
//		// MANIPULAR AS PONTUAÇÕES NA TextField COM A CLASSE MODELO. 
//	public void setDadosErrou(Jogo jogo) {
//		tfErrou.setText (String.valueOf(jogo) );
//	}
//	public void setDadosRecorde(Jogo jogo) {
//		tfRecorde.setText(String.valueOf(jogo) );
//	}
//	
//	public int getDadosErrou(Jogo jogo) {
//		//return new Jogo(tfErrou.getText(), tfRecord.getText());
//		return Integer.parseInt( tfErrou.getText()) ;
//		}
//	
//	public int getDadosRecorde(Jogo jogo) {
//		return Integer.parseInt( tfRecorde.getText()) ;
//		}
//	
//	public void setDados(Pontos pontos) {
//		tfRecorde.setText( pontos.getRecorde() );
//		int valor = Integer.parseInt(textField.getText());
//		tfErrou.setText( pontos.getErro() );
//	}
//	
//	public Pontos getDados(Pontos pontos) {
//		int pontos = Integer.parseInt( tfRecorde.getText(), tfErrou.getText());
//	}
//	
//	
//	public void setDados(Recorde recorde) {
//		tfRecorde.setText( recorde.getRecorde() ); 		
//			
//	public Pontos getDados(int recorde) {
//		 recorde = Integer.parseInt( tfRecorde.getText());
//	}
}
	


