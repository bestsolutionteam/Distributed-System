package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class client2 {
	
	thread1 t1;
	receiveDataTh t2;
	
	public client2(DatagramSocket s) {
		t1 = new thread1(s);
		t2 = new receiveDataTh(s);
	}

	public void runThread1() {
		t1.start();
	}

	public void runreceiveDataTh() {
		t2.start();
	}

	public static void main(String[] args) throws Exception {
	//
		DatagramSocket socket = new DatagramSocket(2222);
		client2 st = new client2(socket);
		st.runThread1();
		st.runreceiveDataTh();
	}
	DatagramSocket clientSocket;
	public class thread1 extends Thread {
		public thread1(DatagramSocket s) {
			clientSocket = s;
		}

		public void run() {
			try {
				InetAddress adr = InetAddress.getByName("localhost");
				byte[] sendData = new byte[1024];

				while (true) {
//					System.out.println("client2 prepare send...");
					BufferedReader br = new BufferedReader(
							new InputStreamReader(System.in));
					System.out.print("Client2 : ");
					String msg = br.readLine();
					sendData = (msg).getBytes();
					DatagramPacket sendPacket = new DatagramPacket(sendData,sendData.length, adr, 1111);
					clientSocket.send(sendPacket);

//					clientSocket.close();
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("erro send");
			}

		}
	}
	
	public class receiveDataTh extends Thread {
		public receiveDataTh(DatagramSocket s) {
			clientSocket = s;
		}

		public void run() {
			try {
				byte[] receiveData = new byte[1024];

				while (true) {
//					System.out.println("client2 prepare receive...");
					DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
					clientSocket.receive(receivePacket);
					String modifiedSentence = new String(receivePacket.getData(),0,receivePacket.getLength());
					System.out.println("Client1 : " + modifiedSentence);
//					clientSocket.close();
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("erro receive");
			}
		}
	}
}
