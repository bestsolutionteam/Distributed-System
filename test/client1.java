package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class client1 {
	sendDataTh t1;
	thread2 t2;
	public client1(DatagramSocket s) {
		t1 = new sendDataTh(s);
		t2 = new thread2(s);
	}
	
	

	public void runsendDataTh() {
		t1.start();
	}

	public void runThread2() {
		t2.start();
	}

	public static void main(String[] args) throws Exception {
		DatagramSocket socket = new DatagramSocket(1111);
		client1 st = new client1(socket);
		st.runsendDataTh();
		st.runThread2();
	}

	DatagramSocket clientSocket;
	
	public class sendDataTh extends Thread {
		
		public sendDataTh(DatagramSocket s) {
			clientSocket = s;
		}

		public void run() {
			try {
				InetAddress adr = InetAddress.getByName("localhost");
				byte[] sendData = new byte[1024];

				while (true) {
//					System.out.println("client1 prepare send...");
					BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
					System.out.print("Client1 : ");
					String msg = br.readLine();
					sendData = (msg).getBytes();
					//====
					DatagramPacket sendPacket = new DatagramPacket(sendData,sendData.length, adr, 2222);
					//====
					clientSocket.send(sendPacket);
//					clientSocket.close();
				}
			} catch (Exception e) {
				// TODO: handle exception
//				System.out.println("erro send from client1");
			}

		}
	}

	public class thread2 extends Thread {
		
		public thread2(DatagramSocket s) {
			clientSocket = s;
		}

		public void run() {
			try {
				byte[] receiveData = new byte[1024];

				while (true) {
//					System.out.println("client1 prepare receive...");
					DatagramPacket receivePacket = new DatagramPacket(
							receiveData, receiveData.length);
					clientSocket.receive(receivePacket);
					String modifiedSentence = new String(
							receivePacket.getData(), 0,
							receivePacket.getLength());
					System.out.println("Client2 : " + modifiedSentence);
//					clientSocket.close();
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("erro receive");
			}
		}
	}
}
