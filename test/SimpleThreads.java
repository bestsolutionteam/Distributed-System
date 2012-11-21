package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

public class SimpleThreads {
	
	thread1 t1 = new thread1();
	thread2 t2 = new thread2();

	public void runThread1() {
		t1.start();
	}

	public void runThread2() {
		t2.start();
	}

	public static void main(String[] args){
		BufferedReader br = new BufferedReader(new InputStreamReader(
				System.in));
		System.out.print("Enter a list of number : ");
		try{
		String s = br.readLine();
		ArrayList<Integer> list = new ArrayList<Integer>();
		String tmp = "";
		for(int i = 0;i<s.length();i++){
			if(s.charAt(i)!=' '){
				tmp = tmp + s.charAt(i);
			}
			else{
				list.add(Integer.parseInt(tmp));
				tmp="";
			}
		}
		list.add(Integer.parseInt(tmp));
		System.out.println(list);
		}catch (Exception e) {
			System.out.println("Wrong Input");
		}
	}

	public class thread1 extends Thread {
		public void run() {
			try {
				InetAddress adr = InetAddress.getByName("localhost");
				byte[] sendData = new byte[1024];

				while (true) {
					BufferedReader br = new BufferedReader(
							new InputStreamReader(System.in));
					System.out.print("Client2 : ");
					String msg = br.readLine();
					DatagramSocket clientSocket = new DatagramSocket(7777);
					sendData = (msg).getBytes();
					DatagramPacket sendPacket = new DatagramPacket(sendData,
							sendData.length, adr, 6666);
					clientSocket.send(sendPacket);

					clientSocket.close();
				}
			} catch (Exception e) {
				// TODO: handle exception
			}

		}
	}

	public class thread2 extends Thread {
		
		
	}
}
