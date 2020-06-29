package MODELO;

	public class Pontos {
	

		private int recorde;
		private int erro ;

		public Pontos() {
			this(0, 0);
		}

		public Pontos(int recorde, int erro) {
			super();
			this.recorde = recorde;
			this.erro = erro;
		}

		public int getRecorde() {
			return recorde;
		}

		public void setRecorde(int recorde) {
			this.recorde = recorde;
		}

		public int getErro() {
			return erro;
		}

		public void setErro(int erro) {
			this.erro = erro;
		}

	}


