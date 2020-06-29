package MODELO;

@SuppressWarnings("serial")
public class Jogo {
	
	public static final int SOMA_RECORDE = 0;
	public static final int SOMA_ERROS   = 1;
	public static final int SUBTRAI      = 2;
	
	private int somar, somaRecorde=0,somaErros=0, subtrai;
	
	private int recorde;
	private int errou;
	
	public Jogo() {
		this(0,0);
	}

	public Jogo(int recorde, int errou) {
		super();
		this.recorde = recorde;
		this.errou = errou;
	}

	public int getRecorde() {
		return recorde;
	}

	public void setRecorde(int recorde) {
		this.recorde = recorde;
	}

	public int getErrou() {
		return errou;
	}

	public void setErrou(int errou) {
		this.errou =errou;
	}

//	public int getSubtrai() {
//		return subtrai;
//	}
//
//	public void setSubtrai(int subtrai) {
//		this.subtrai = subtrai;
//	}
//	public int calcular(int somando)  {
//		switch (somando) {
//		case SOMA_RECORDE:
//			somaRecorde = somaRecorde+1;
//			break;
//		case SOMA_ERROS:
//			somaErros = somaErros +1;
//			break;
//		case SUBTRAI:
//			subtrai =somaRecorde - somaErros;
//			break;
//			}
//		return somando;
//	}
	public int calcRecorde(int somaRecorde) {
		return this.somaRecorde = somaRecorde++;
	}
	public int  calcErros(int somaErros) {
		return this.somaErros = somaErros ++;
	}
	public void calcDiferenca() {
		subtrai =somaRecorde - somaErros;
	}
	
}
