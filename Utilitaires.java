package SEVESO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Utilitaires 
{
	private static String IP = "192.168.12.245";
	private static int port = 5001;
	
	Utilitaires()
	{
		ThreadU();
	}
	
	private static void ThreadU()
	{
		Thread ecoute = new Thread(new Runnable()
		{
			public void run()
			{
				byte octetLu;
				Socket socket;
				try 
				{	
					//do{
						socket = new Socket(IP, port);
					//}while(socket.isConnected() != true);
						
					BufferedReader Recepteur = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					
					while(true)
					{
						octetLu = (byte) Recepteur.read();
						
						if(octetLu == 0x3F)// '?'
						{
							octetLu = (byte) Recepteur.read();
							if(octetLu == 0x45)// 'E' pour Entree
							{
								System.out.println("Passage en mode entrée");
								Main.EtatEntree = 0;
								
								if(Main.EtatUrgence == 0)
									GestionAfficheurLCD.ModeEntree();
							}
							
							if(octetLu == 0x53)// 'S' pour Sortie
							{
								System.out.println("Passage en mode sortie");
								Main.EtatEntree = 1;
								
								if(Main.EtatUrgence == 0)
									GestionAfficheurLCD.ModeSortie();
							}
							
							if(octetLu == 0x44)// 'D' pour D�but
							{
								octetLu = (byte) Recepteur.read();
								
								if(octetLu == 0x55)// 'U' pour urgence
								{
									Main.EtatUrgence = 1;
									GestionAfficheurLCD.DebutEvac();
									System.out.println("Début evacuation");
								}
							}
							
							if(octetLu == 0x46)//'F' pour fin
							{
								octetLu = (byte) Recepteur.read();
								if(octetLu == 0x55)
								{		
									GestionAfficheurLCD.AfficherEtat();
									Main.EtatUrgence = 0;
									System.out.println("Fin évacuation");
								}
							}
						}
					}	
				}catch(IOException e){e.printStackTrace();}
			}
		});
		ecoute.start();
	}
}