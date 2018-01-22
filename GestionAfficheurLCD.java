package SEVESO;

import java.io.IOException;

public class GestionAfficheurLCD
{
	private static Runtime rt;
	
	public static void Init()
	{
		rt = Runtime.getRuntime();
		String[] commande = {"gksudo", "python3", "/home/pi/workspace/ScriptPython/Init.py"};
		try{rt.exec(commande);} catch (IOException e) {e.printStackTrace();}
	}
	
	public static void AE()
	{
		rt = Runtime.getRuntime();
		String[] commande = {"gksudo", "python3", "/home/pi/workspace/ScriptPython/AutoriserModeEntree.py"};
		try{rt.exec(commande);} catch (IOException e) {e.printStackTrace();}
	}
	
	public static void AS()
	{
		rt = Runtime.getRuntime();
		String[] commande = {"gksudo", "python3", "/home/pi/workspace/ScriptPython/AutoriserModeSortie.py"};
		try{rt.exec(commande);} catch (IOException e) {e.printStackTrace();}
	}
	
	public static void RE()
	{
		rt = Runtime.getRuntime();
		String[] commande = {"gksudo", "python3", "/home/pi/workspace/ScriptPython/RefuserModeEntree.py"};
		try{rt.exec(commande);} catch (IOException e) {e.printStackTrace();}
	}

	
	public static void RS()
	{
		rt = Runtime.getRuntime();
		String[] commande = {"gksudo", "python3", "/home/pi/workspace/ScriptPython/RefuserModeSortie.py"};
		try{rt.exec(commande);} catch (IOException e) {e.printStackTrace();}
	}
	public static void DebutEvac()
	{
		rt = Runtime.getRuntime();
		String[] commande = {"gksudo", "python3", "/home/pi/workspace/ScriptPython/DebutEvac.py"};
		try{rt.exec(commande);} catch (IOException e) {e.printStackTrace();}
	}
	
	public static void FE()
	{
		rt = Runtime.getRuntime();
		String[] commande = {"gksudo", "python3", "/home/pi/workspace/ScriptPython/FinEvacEntree.py"};
		try{rt.exec(commande);} catch (IOException e) {e.printStackTrace();}
	}
	
	public static void FS()
	{
		rt = Runtime.getRuntime();
		String[] commande = {"gksudo", "python3", "/home/pi/workspace/ScriptPython/FinEvacSortie.py"};
		try{rt.exec(commande);} catch (IOException e) {e.printStackTrace();}
	}
	
	public static void ModeEntree()
	{
		rt = Runtime.getRuntime();
		String[] commande = {"gksudo", "python3", "/home/pi/workspace/ScriptPython/ModeEntree.py"};
		try{rt.exec(commande);} catch (IOException e) {e.printStackTrace();}
	}
	
	public static void ModeSortie()
	{
		rt = Runtime.getRuntime();
		String[] commande = {"gksudo", "python3", "/home/pi/workspace/ScriptPython/ModeSortie.py"};
		try{rt.exec(commande);} catch (IOException e) {e.printStackTrace();}
	}
	
	public static void Clear()
	{
		rt = Runtime.getRuntime();
		String[] commande = {"gksudo", "python3", "/home/pi/workspace/ScriptPython/Clear.py"};
		try{rt.exec(commande);} catch (IOException e) {e.printStackTrace();}
	}
	
	public static void IsConnected()
	{
		rt = Runtime.getRuntime();
		String[] commande = {"gksudo", "python3", "/home/pi/workspace/ScriptPython/IsConnected.py"};
		try{rt.exec(commande);} catch (IOException e) {e.printStackTrace();}
	}
	
	public static void AfficherEtat()
	{
		if(Main.EtatEntree == 0)
			ModeEntree();
		else
			ModeSortie();
	}
}
