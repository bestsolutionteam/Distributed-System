package TP02;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class CyracusNextServer {
	public static void main(String[] args) throws ClassNotFoundException {
		try {
			// serveur positionne sa socket d'écoute sur le port local 7777
			ServerSocket ss = new ServerSocket(615);
			System.out.println("Cyracuse series server is running...");
			while  (true) {
				Socket s = ss.accept();
				DataInputStream dis = new DataInputStream(s.getInputStream());
				int n = dis.readInt();
				System.out.println("from Client : "+n);
				int res = nextCyracuse(n);
				ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
				oos.writeObject(res);
				s.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static int nextCyracuse(int n) {
		if (n % 2 == 0){
			return n / 2;
		} else {
			return 3*n + 1;
		}
	}
}
