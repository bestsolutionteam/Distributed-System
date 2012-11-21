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
			// données à envoyer : chaîne de caractères
			byte[] data = (new String("stop")).getBytes();
			// création du paquet avec les données et en précisant l'adresse du
			// serveur
			// (@IP et port sur lequel il écoute : 7777)
			
			packet = new DatagramPacket(data, data.length, adr, 7777);
			
			// création d'une socket, sans la lier à un port particulier

			socket = new DatagramSocket();
			
			socket.send(packet);
			
			data = new byte[15];
			// création d'un paquet en utilisant le tableau d'octets
			packet = new DatagramPacket(data, data.length);
			// attente de la réception d'un paquet. Le paquet reçu est placé
			// dans
			// packet et ses données dans data.
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
