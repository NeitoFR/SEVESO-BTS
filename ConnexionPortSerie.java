package SEVESO;

import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;

public class ConnexionPortSerie 
{
	private static String nomPortSerie = null;
	private static SerialPort portSerie = null;
	private static CommPortIdentifier IDportSerie = null;
	
	protected ConnexionPortSerie()
	{
		quelPortCOM();
		CandCPortSerie();
	}
	
	private void quelPortCOM()//Cible le port sur le quel est branch� le lecteur RFID
	{
		@SuppressWarnings("unchecked")
		java.util.Enumeration<CommPortIdentifier> portEnum = CommPortIdentifier.getPortIdentifiers();
		
		while(portEnum.hasMoreElements())
		{
			IDportSerie = portEnum.nextElement();
			nomPortSerie = IDportSerie.getName();
		}
		System.out.println("Le lecteur RFID se trouve sur le port : "+nomPortSerie);
	}
	
	private void CandCPortSerie()//Connexion et configuration du port
	{
		try 
		{
			portSerie = (SerialPort) IDportSerie.open(nomPortSerie, 0);
		}catch(PortInUseException e){e.printStackTrace();}
		System.out.println("Connexion au port "+nomPortSerie+" effectu�e");
		
		//Configuration
		try 
		{
			portSerie.setSerialPortParams(
					19200, // Vitesse en bauds (octets/s)
					SerialPort.DATABITS_8, //8 bits par donn�es
					SerialPort.STOPBITS_1, //Un bit pour signal� la fin d'une donn�es
					SerialPort.PARITY_NONE);//Signal qu'il n'y pas de parit� donc le bit de parit� est � 0
			portSerie.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);
		}catch(UnsupportedCommOperationException e){e.printStackTrace();}
		System.out.println("Configuration du port "+nomPortSerie+" effectu�e");
	}
	
	public SerialPort getPortSerie(){return portSerie;};//Assesseur retournant l'objet portSerie pret a l'empoi
}
