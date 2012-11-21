package TP01;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class FactorialServer {

	public static void main(String[] args) {
		int port = 2000;
		try {
			ServerSocket ss = new ServerSocket(port);
			System.out.println("Factorial server is running...");
			while (true) {
				Socket s = ss.accept();
				DataInputStream dis = new DataInputStream(s.getInputStream());
				int n = dis.readInt();
				int res = factorial(n);
				DataOutputStream dos = new DataOutputStream(s.getOutputStream());
				dos.writeInt(res);
				s.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static int factorial(int n) {
		int res = 1;
		for (int i=1; i<=n; i++) {
			res *= i;
		}
		return res;
	}

}
