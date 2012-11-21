package TP02;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class chatServer {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		DatagramSocket socket = new DatagramSocket(7777);
		byte[] reciveData = new byte[1024];
		byte[] sendData = new byte[1024];

		while (true) {

			reciveData = new byte[1024];
			DatagramPacket recivePacket = new DatagramPacket(reciveData,
					reciveData.length);
			socket.receive(recivePacket);
			String chaine = new String(recivePacket.getData(), 0,
					recivePacket.getLength());
			System.out.println(" revive from client : " + chaine);
			
			sendData = (new String("Hello Client1!")).getBytes();
			InetAddress adr = recivePacket.getAddress();
			int port = recivePacket.getPort();
			DatagramPacket sendPacket;
			sendPacket = new DatagramPacket(sendData, sendData.length, adr,
					port);
			socket.send(sendPacket);

		}

	}

}
