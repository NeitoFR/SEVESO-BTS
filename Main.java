package SEVESO;


public class Main 
{
	public static int EtatEntree;
	public static int EtatUrgence;
	
	public static void main(String[] args) 
	{
		EtatEntree = 0;
		EtatUrgence = 0;
		GestionAfficheurLCD.Init();
		new Utilitaires();
		ConnexionPortSerie ConLecteurRFID = new ConnexionPortSerie();
		new TraitementData(ConLecteurRFID.getPortSerie());
	}
	
	
}
