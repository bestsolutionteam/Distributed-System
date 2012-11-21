package TP01;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;


public class FactorialClient {

	public static void main(String[] args) {
		String server = "localhost";
		int    port   = 2000;
		try {
			Socket s = new Socket(server, port);
			DataOutputStream dos = new DataOutputStream(s.getOutputStream());
			int n = 6;
			dos.writeInt(n);
			DataInputStream dis = new DataInputStream(s.getInputStream());
			int res = dis.readInt();
			System.out.println("Factorial of " + n + " is " + res);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
