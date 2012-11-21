package TP02;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

public class UDPcyracusServer {

	public static void main(String[] args) throws Exception {
		System.out.println("Server is running...");
		DatagramSocket socket = new DatagramSocket(7777);
		byte[] reciveData = new byte[1024];
		byte[] sendData = new byte[1024];
		
		reciveData = new byte[1024];
		DatagramPacket recivePacket = new DatagramPacket(reciveData, reciveData.length);
		socket.receive(recivePacket);
		String chaine = new String(recivePacket.getData(),0,recivePacket.getLength()); 
		int number = Integer.parseInt(chaine);
		ArrayList<Integer> result = new ArrayList<Integer>();
		result = cyracuseSeries(number);
		
		DatagramPacket sendPacket;
		
		sendData = (new String(result.size()+"")).getBytes();
		InetAddress adr = recivePacket.getAddress();
		int port = recivePacket.getPort();
		sendPacket = new DatagramPacket(sendData, sendData.length, adr, port);
		socket.send(sendPacket);
		
		for(int i = 0 ; i <result.size() ; i++){
			System.out.print(result.get(i)+" | ");
			sendData = (new String(result.get(i)+"")).getBytes();
			sendPacket = new DatagramPacket(sendData, sendData.length, adr, port);
			socket.send(sendPacket);
		}
		
	}
	
	private static ArrayList<Integer> cyracuseSeries(int n) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		while (n != 1) {
			n = nextCyracuse(n) ;
			res.add(new Integer(n));
		}
		return res;
	}
	
	private static int nextCyracuse(int n) {
		if (n % 2 == 0){
			return n / 2;
		} else {
			return 3*n + 1;
		}
	}

}
