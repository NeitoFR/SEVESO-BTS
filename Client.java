package SEVESO;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client 
{
	private final static String IP = "192.168.12.245";
	private final static int port = 5000;
	private static Socket socket;
	private static byte[] ID;
	private static String ordre;
	private static DataOutputStream dOut;
	private static BufferedReader Recepteur;
	
	Client(byte[] TrameTraitee) throws UnknownHostException, IOException
	{
		ID = TrameTraitee;
		Connect();
		sendID();
		waitOrdre();
		Disconnect();
	}
	
	private static void Connect() throws UnknownHostException, IOException
	{
		socket = new Socket(IP, port);
		Recepteur = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		dOut = new DataOutputStream(socket.getOutputStream());
	}
	
	private static void sendID() throws IOException
	{
		dOut.write(ID, 0, 7);
	}
	
	private static void waitOrdre() throws IOException
	{
		ordre = Recepteur.readLine();
		if(ordre.charAt(0) == 0x3F && ordre.charAt(1) == 0x41)//3F = ? 41 = A
		{
			if(Main.EtatEntree == 0)
				GestionAfficheurLCD.AE();
			if(Main.EtatEntree == 1)
				GestionAfficheurLCD.AS();

			System.out.println("Autoriser");
		}
		
		if(ordre.charAt(0) == 0x3F && ordre.charAt(1) == 0x52)//3F = ? 53 = R
		{
			if(Main.EtatEntree == 0)
				GestionAfficheurLCD.RE();
			if(Main.EtatEntree == 1)
				GestionAfficheurLCD.RS();
			
			System.out.println("Refuser");
		}
	}
	private static void Disconnect() throws IOException
	{
		socket.close();
	}
}
