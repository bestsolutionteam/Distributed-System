package TP02;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {
public static void main(String[] args) throws Exception {
	InetAddress adr;
	adr = InetAddress.getByName("localhost");
//	DatagramPacket packet;
//	DatagramSocket socket;
	
	byte[] sendData = new byte[1024]; 
    byte[] receiveData = new byte[1024];
    DatagramSocket clientSocket = new DatagramSocket();
    sendData = (new String("from client1!")).getBytes();
    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, adr, 7777);
    clientSocket.send(sendPacket);
    
    DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
    clientSocket.receive(receivePacket);
    String modifiedSentence = new String(receivePacket.getData(),0,receivePacket.getLength());
    InetAddress returnIPAddress = receivePacket.getAddress();
    int port = receivePacket.getPort();
    System.out.println(returnIPAddress+" and "+port);
    System.out.println("Message: " + modifiedSentence);
    clientSocket.close();
	
	// adr contient l'@IP de la partie serveur
	// donn�es � envoyer : cha�ne de caract�res
//	byte[] data = (new String("Hi server...!")).getBytes();
//	// cr�ation du paquet avec les donn�es et en pr�cisant l'adresse du serveur
//	// (@IP et port sur lequel il �coute : 7777)
//	
//	packet = new DatagramPacket(data, data.length, adr, 7777);
//	
//	// cr�ation d'une socket, sans la lier � un port particulier
//	socket = new DatagramSocket();
//	
//	// envoi du paquet via la socket
//	socket.send(packet);
//	
//	socket.receive(packet);
//	String chaine = new String(packet.getData(),0,packet.getLength()); 
//	System.out.println(" recive from server : "+chaine);
}
}
