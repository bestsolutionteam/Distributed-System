package TP01;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPclient {
	public static void main(String[] args) {
		InetAddress adr = null,addc = null;
		DatagramPacket packet;
		DatagramSocket socket = null;
		// adr contient l'@IP de la partie serveur
		try {
			
			adr = InetAddress.getByName("localhost");
			// donn�es � envoyer : cha�ne de caract�res
			byte[] data = (new String("stop")).getBytes();
			// cr�ation du paquet avec les donn�es et en pr�cisant l'adresse du
			// serveur
			// (@IP et port sur lequel il �coute : 7777)
			
			packet = new DatagramPacket(data, data.length, adr, 7777);
			
			// cr�ation d'une socket, sans la lier � un port particulier

			socket = new DatagramSocket();
			
			socket.send(packet);
			
			data = new byte[15];
			// cr�ation d'un paquet en utilisant le tableau d'octets
			packet = new DatagramPacket(data, data.length);
			// attente de la r�ception d'un paquet. Le paquet re�u est plac�
			// dans
			// packet et ses donn�es dans data.
			socket.receive(packet);
			String chaine = new String(packet.getData(), 0,
					packet.getLength());
			System.out.println("Receive from server : "+ chaine);

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
