package TP01;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPserver {
	public static void main(String[] args) {
		DatagramSocket socket = null;
		DatagramPacket packet = null;
		// création d'une socket liée au port 7777
		System.out.println("Server is running...");
		try {
			socket = new DatagramSocket(7777);
			while (true) {
				// tableau de 15 octets qui contiendra les données reçues
				byte[] data = new byte[15];
				// création d'un paquet en utilisant le tableau d'octets
				packet = new DatagramPacket(data, data.length);
				// attente de la réception d'un paquet. Le paquet reçu est placé
				// dans
				// packet et ses données dans data.
				socket.receive(packet);
				String chaine = new String(packet.getData(), 0,
						packet.getLength());
				System.out.println("Receive from client address "
						+ packet.getAddress() + " : " + chaine);
				// (@IP et port sur lequel il écoute : 7777)
				data = (new String("Server received.")).getBytes();
				packet = new DatagramPacket(data, data.length, packet.getAddress(), packet.getPort());
				
				// création d'une socket, sans la lier à un port particulier

				//socket = new DatagramSocket();
				
				socket.send(packet);
				if(chaine.compareTo("stop")==0){
					break;
				}
			}
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// récupération et affichage des données (une chaîne de caractères)
	}
}
