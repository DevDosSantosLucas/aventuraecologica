import GERENCIA.Controle;
import GUI.MenuUI;


public class InicioProg {

	public static void main(String[] args) {
		

		Splash splash = new Splash();
		
		try{
			
			MenuUI menu = new MenuUI();
			Controle sistema = new Controle() ;
			
			splash.janelaSplash.dispose();
			
			sistema.setInterface(menu);
				
			}catch(Exception e) {System.err.println(e.getMessage());
			 e.printStackTrace();}
		
		
		
		}
}