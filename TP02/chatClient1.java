package TP02;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class chatClient1 {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		InetAddress adr = InetAddress.getByName("localhost");
		byte[] sendData = new byte[1024];
		byte[] receiveData = new byte[1024];
		
		while (true) {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					System.in));
			System.out.print("Client1 : ");
			String msg = br.readLine();
			DatagramSocket clientSocket = new DatagramSocket(6666);
			sendData = (msg).getBytes();
			DatagramPacket sendPacket = new DatagramPacket(sendData,
					sendData.length, adr, 7777);
			clientSocket.send(sendPacket);

			DatagramPacket receivePacket = new DatagramPacket(receiveData,
					receiveData.length);
			clientSocket.receive(receivePacket);
			String modifiedSentence = new String(receivePacket.getData(), 0,
					receivePacket.getLength());
			System.out.println("Client2 : " + modifiedSentence);
			clientSocket.close();
		}

	}
}
