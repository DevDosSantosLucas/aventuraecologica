package GERENCIA;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import GUI.JogoUI;
import GUI.MenuUI;
import GUI.OpcaoUI;
import MODELO.Jogo;


public class Controle  implements ActionListener,ItemListener,MouseListener{
	
	
	private boolean jogando = false ,
					facil = false,
					medio = false,
					dificil = false;
	public int imgRandom = 0;
	
	private MenuUI menu;
	private OpcaoUI opcao;
	private JogoUI jogoUI;
	private Jogo jogoModelo;
	private ControleNivelMedio ctrlMedio;
	public int porcentagem;
	public Timer timer;
	

	
	
	public void setInterface(MenuUI menu){
		this.menu = menu;
		abrirMenu();
	}
	
	public void abrirMenu() {	//dimencionando a JFrame que abre o menu do jogo. 
		menu = new MenuUI();
		menu.setListenerMenu(this);
		menu.setUndecorated(true);		//esconde ( - [] X)
		if(jogando)	jogoUI.setVisible(false);
		menu.setSize(600,510);
		menu.setDefaultCloseOperation(MenuUI.DISPOSE_ON_CLOSE);
		menu.setLocationRelativeTo(null);
		
		menu.setVisible(true);
		
	}
	public void abrirJogo() {	//dimencionando a JFrame queabre o jogo. 
		
		jogoUI.setListenerJogo(this);
		jogoUI.setUndecorated(true);		//esconde ( - [] X)
		opcao.setVisible(false);
		jogoUI.setSize(1000,700);		
		jogoUI.setLocationRelativeTo(null);
		jogoUI.setVisible(true);
		jogando = true;
		this.imagemRandom();
	}
	public void abrirOpcao() { //dimencionando a JFrame que do nivel do jogo ou fim do jogo. 
		opcao.setListenerOpcao(this);	
		opcao.setDefaultCloseOperation(OpcaoUI.DISPOSE_ON_CLOSE);
		opcao.setLocationRelativeTo(null);
		opcao.setVisible(true);
		
	}

//	movimenta os objetos dentro da JFrame 	
//	public void movendoImagem() {
//		new Thread() {
//		
//			public void run() {
//				
//			int x = 0;
//			int y =  jogo.lbObjeto.getLocation().y;
//			while(true) {
//				x++;
//				if(x > jogo.getWidth()){
//					x = 0;
//				}
//				
//				jogo.lbObjeto.setLocation(x,y);
//					try {
//					sleep(2);
//					}catch (Exception e) {}
//				}
//				
//				}
//			
//			}.start();
//	
//	}

	//movimenta a imagem do objeto quando clicado(seguindo o mouse).
	public void movimentarImagem() {
		new Thread() {
			public void run() {
			while(true) {
				
				try {sleep(10);}catch(Exception Erro){}
				if (jogoUI.mousePress){
					Point ponto =  jogoUI.getMousePosition();
					jogoUI.lbObjeto.setBounds(ponto.x-100,ponto.y-100,200,200);
				}
			}
		}
		}.start();
		
	}
	
	//deixa os objetos aleátorios.
	public void imagemRandom() {
		

		   //instância um objeto da classe Random usando o construtor padrão
     Random gerador = new Random();
      
     //imprime sequência de 10 números inteiros aleatórios
     for (int i = 0; i <= 20; i++) {
     	imgRandom = gerador.nextInt(20);
     }
     jogoUI.lbObjeto.setIcon(jogoUI.imgObjeto = new ImageIcon(getClass().getResource("../ASSETS/ImagensRandom/"+imgRandom+".png")));
	}
	
	// AÇÕES DOS BOTÕES:
	@SuppressWarnings("static-access")
	@Override
	public void actionPerformed(ActionEvent e) {
			//	COMEÇAR (abrir menu)\\
		if(e.getActionCommand().equals("btn")) {
				this.abrirMenu();
			}
		
			//	BOTÕES DO MENU	 \\
		else if(e.getActionCommand().equals("btnIniciar")) {
				
				opcao= new OpcaoUI(OpcaoUI.JOGO);
				this.abrirOpcao();
				menu.dispose();
			}
		
			//	BOTÕES DE OPÇÕES 	\\
		else if(e.getActionCommand().equals("btnComecar")) {
				//jogo = new JogoUI(0);
				this.setNivelDoJogo();
				this.abrirJogo();
				opcao.dispose();
					
			}
		else if(e.getActionCommand().equals("btnSair")) {
			System.exit(0);
		}
		
		
//		BOTÕES DA JANELA JOGO
		
		else if(e.getActionCommand().equals("mMenuVoltar")) {
				jogoUI.dispose()	;
				
				//jogo.setVisible(false);
						
				this.abrirMenu();
		}
				
		else if(e.getActionCommand().equals("mFechar")) {
				System.exit(0);
		}
		else if(e.getActionCommand().equals("mReiniciar")) {
			
			if(	facil = true) {
				jogoUI.dispose();
				jogoUI = new JogoUI(JogoUI.FACIL);
				this.abrirJogo();
	
				jogoUI.setListenerJogoF(this);
				facil = false;
				
			}
			else if(medio = true){
				jogoUI.dispose();
				jogoUI = new JogoUI(JogoUI.MEDIO);
				this.abrirJogo();
				
				jogoUI.setListenerJogoM(this);
				medio = false;
		}
			
				
		}
		else if(e.getActionCommand().equals("mPausar")){
				
		}
		
		
		else if(e.getActionCommand().equals("mLixo1")){
			//MANIPULANDO OS PONTOS E PORCENTAGEM DE VIDA SE ACERTAR OU ERRAR OS LIXOS:
			if((imgRandom>=0)&&(imgRandom<=10)){
			jogoUI.porcentagem = jogoUI.porcentagem +5;
//			jogoModelo.calcRecorde(1);
			}
			else { jogoUI.porcentagem = jogoUI.porcentagem -10;
//			jogoModelo.calcErros(1);
			}
			this.imagemRandom();
		}
		else if(e.getActionCommand().equals("mLixo2")){
			
			if((imgRandom>10)&&(imgRandom<=20)){
				jogoUI.porcentagem = jogoUI.porcentagem +5;
//				jogoModelo.calcRecorde(1);
				}
				else { jogoUI.porcentagem = jogoUI.porcentagem -10;
//				jogoModelo.calcErros(1);
				}
			this.imagemRandom();
				}
		else if(e.getActionCommand().equals("mLixo3")){
			if((imgRandom>20)&&(imgRandom<=30)){
				jogoUI.porcentagem = jogoUI.porcentagem +5;
				}
				else { jogoUI.porcentagem = jogoUI.porcentagem -10;}
			this.imagemRandom();
			
		}
		else if(e.getActionCommand().equals("mLixo4")){
			if((imgRandom>30)&&(imgRandom<=40)){
				jogoUI.porcentagem = jogoUI.porcentagem +5;
				
				}
				else { jogoUI.porcentagem = jogoUI.porcentagem -10;}
			this.imagemRandom();
			
		}
		else if(e.getActionCommand().equals("mLixo5")){
			if((imgRandom>40)&&(imgRandom<=50)){
				jogoUI.porcentagem = jogoUI.porcentagem +5;
				}
				else { jogoUI.porcentagem = jogoUI.porcentagem -10;}
			this.imagemRandom();
			
		}
		else if(e.getActionCommand().equals("mLixo6")){
			if((imgRandom>50)&&(imgRandom<=60)){
				jogoUI.porcentagem = jogoUI.porcentagem +5;
				}
				else { jogoUI.porcentagem = jogoUI.porcentagem -10;}
			this.imagemRandom();
			
		}
//		else if(e.getActionCommand().equals("mLixo7")){
//			if((imgRandom>60)&&(imgRandom<=70)){
//				jogoUI.porcentagem = jogoUI.porcentagem +5;
//				
//				}
//				else { jogoUI.porcentagem = jogoUI.porcentagem -10;}
//			this.imagemRandom();
//			
//		}
//		else if(e.getActionCommand().equals("mLixo8")){
//			if((imgRandom>70)&&(imgRandom<=80)){
//				jogoUI.porcentagem = jogoUI.porcentagem +5;
//				}
//				else { jogoUI.porcentagem = jogoUI.porcentagem -10;}
//			this.imagemRandom();
//			
//		}
//		else if(e.getActionCommand().equals("mLixo9")){
//			if((imgRandom>80)&&(imgRandom<=90)){
//				jogoUI.porcentagem = jogoUI.porcentagem +5;
//				}
//				else { jogoUI.porcentagem = jogoUI.porcentagem -10;}
//			this.imagemRandom();
//		}


		
	}
	//AÇÕES PARA ESCOLHER O NÍVEL DO JOGO
	public void setNivelDoJogo() {

		if(opcao.btnFacil.isSelected()) {
				facil = true;
				jogoUI = new JogoUI(JogoUI.FACIL);
				jogoUI.setListenerJogoF(this);
				facil =false;
				
			}
		else if(opcao.btnMedio.isSelected()) {
				medio = true;
				jogoUI = new JogoUI(JogoUI.MEDIO);
				jogoUI.setListenerJogoM(this);
				medio = false;
				
		}
		else if(opcao.btnDificil.isSelected())		
				jogoUI = new JogoUI(JogoUI.DIFICIL);
			
		}

//		String cad = "";
//		if(opcao.btnFacil.isSelected())	
//
//		cad+="radio1";
//		if(opcao.btnMedio.isSelected())	
//		 cad+="radio2";
//		
//		JOptionPane.showMessageDialog(null,cad);
//	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
		
	}

@Override
public void mouseClicked(MouseEvent e) {
	// TODO Auto-generated method stub
//	if(jogo.btnImgLixo1.isSelected()) {
//		jogo.btnImgLixo1.setIcon(lixoOrganicoA);
//	}
//	if(jogo.btnImgLixo2 .isSelected()) {
//		setIcon(lixoOrganicoA);
//	}
	
}


@Override
public void mouseEntered(MouseEvent e) {

//	jogo.btnImgLixo1.setIcon(lixoOrganicoA);
//	jogo.btnImgLixo2.setIcon(lixoPapelA);
//	jogo.btnImgLixo3.setIcon(lixoPlasticoA);
//	jogo.btnImgLixo4.setIcon(lixoVidroA);
	
//	jogo.btnImgLixo5.setIcon(lixoMetalA);
//	jogo.btnImgLixo6.setIcon(lixoNaoReciclaA);
//	jogo.btnImgLixo7.setIcon(lixoPerigososA);
//	jogo.btnImgLixo8.setIcon(lixoMadeiraA);
//	jogo.btnImgLixo9.setIcon(lixoSaudeA);
	
}

@Override
public void mouseExited(MouseEvent e) {
//	jogo.btnImgLixo1.setIcon(lixoOrganicoF);
//	jogo.btnImgLixo2.setIcon(lixoPapelF);
//	jogo.btnImgLixo3.setIcon(lixoPlasticoF);
//	jogo.btnImgLixo4.setIcon(lixoVidroF);
	
//	jogo.btnImgLixo5.setIcon(lixoMetalF);
//	jogo.btnImgLixo6.setIcon(lixoNaoReciclaF);
//	jogo.btnImgLixo7.setIcon(lixoPerigososF);
//	jogo.btnImgLixo8.setIcon(lixoMadeiraF);
//	jogo.btnImgLixo9.setIcon(lixoSaudeF);
}

//CASO O OBJETO FOR CLICADO ELE RECEBE AÇÃO PARA MOVIMENTAR:
@Override
public void mousePressed(MouseEvent e) {
//	if(jogoUI.mousePress = true) {
//		movimentarImagem();
//	}
//	
}

@Override
public void mouseReleased(MouseEvent e) {
//	jogoUI.mousePress = false;
//	//movendoImagem();
}

	

}
