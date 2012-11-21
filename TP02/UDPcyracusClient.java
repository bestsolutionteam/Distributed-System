package TP02;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPcyracusClient {
	public static void main(String[] args) throws Exception {
		InetAddress adr;
		adr = InetAddress.getByName("localhost");
		// DatagramPacket packet;
		// DatagramSocket socket;

		byte[] sendData = new byte[1024];
		byte[] receiveData = new byte[1024];
		DatagramSocket clientSocket = new DatagramSocket();
		sendData = (new String("7")).getBytes();
		DatagramPacket sendPacket = new DatagramPacket(sendData,
				sendData.length, adr, 7777);
		clientSocket.send(sendPacket);
		DatagramPacket receivePacket;
		String modifiedSentence;
		
		receivePacket = new DatagramPacket(receiveData,
				receiveData.length);
		clientSocket.receive(receivePacket);
		modifiedSentence = new String(receivePacket.getData(), 0,
				receivePacket.getLength());
		int num_serie = Integer.parseInt(modifiedSentence); 
		System.out.print("Cyracuse series of 7 is |");
		for(int i = 0 ; i < num_serie ; i++){
		receivePacket = new DatagramPacket(receiveData,
				receiveData.length);
		clientSocket.receive(receivePacket);
		modifiedSentence = new String(receivePacket.getData(), 0,
				receivePacket.getLength());
		System.out.print(modifiedSentence+" | ");
		}
		
		clientSocket.close();
	}

}
