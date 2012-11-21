package TP02;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class CyracuseNextClient {
	public static void main(String[] args) {
		String server = "localhost";
		int    port   = 615;
		try {
			Socket s = new Socket(server, port);
			DataOutputStream dos = new DataOutputStream(s.getOutputStream());
			int n = 6;
			dos.writeInt(n);
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			int res = (Integer) ois.readObject();
			System.out.println("Next value of " + n + " is " + res);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
