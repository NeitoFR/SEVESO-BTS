package SEVESO;

import java.io.IOException;
import java.io.InputStream;

import gnu.io.SerialPort;

public class TraitementData 
{
	private static SerialPort conLecteurRFID;
	private static InputStream Recepteur;
	private static byte OctetLu;
	private static byte[] trameBrute, trameTraitee;
	protected static String IDS;
	
	TraitementData(SerialPort portSerie)
	{
		TraitementData.conLecteurRFID = portSerie;
			TLectureData();
	}
	
	public void TLectureData()
	{
		Thread LectureContinue = new Thread(new Runnable()
		{
			public void run()
			{
				trameBrute = new byte[9];
				
				try 
				{
					Recepteur = conLecteurRFID.getInputStream();
				}catch(IOException e){e.printStackTrace();}
				
				
				while(true)
				{
					try {
						
						OctetLu = (byte) Recepteur.read();
						
						//System.out.println(OctetLu);
						if(OctetLu == 0x42)
						{
							trameBrute[0] = OctetLu;
							for(int i = 1; i < 9; i++)
							{
								OctetLu = (byte) Recepteur.read();
								trameBrute[i] = OctetLu;
								
							}
							
							trameTraitee = convID(trameBrute);
							//System.out.println("Trame traitée : "+trameTraitee);
							IDS = IDBtoIDS(trameTraitee);
							System.out.println("Trame envoyée : "+IDS);
							
							if(Main.EtatUrgence == 0)
								new Client(trameTraitee);
						}
						//octetLu = (byte) Recepteur.read();
						//Thread.sleep(25);
					}catch(IOException e){e.printStackTrace();}
				}
			}
		});
		LectureContinue.start();
	}
	
	private byte[] convID(byte[] trameF)
	{
		byte[] trameTraitee = new byte[7];
		//Gestion entrée sortie
		trameTraitee[0] = 0x3F;
		if(Main.EtatEntree == 0)
			trameTraitee[1] = 0x45;
		
		if(Main.EtatEntree == 1)
			trameTraitee[1] = 0x53;
		
		for(int i = 2; i < 7; i++)
		{
			trameTraitee[i] = trameF[2+i];
		}
		return trameTraitee;
	}
	
	private String IDBtoIDS(byte[] b)
	{
		IDS = "";
		for(int i = 0; i < b.length; i++)
		{
			IDS += (Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1)+" ");
		}
		
		return IDS;
	}
}
