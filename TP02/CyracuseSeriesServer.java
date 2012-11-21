package TP02;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class CyracuseSeriesServer {

	public static void main(String[] args) {
		int port = 8888;
		try {
			ServerSocket ss = new ServerSocket(port);
			System.out.println("Cyracuse series server is running...");
			while (true) {
				Socket s = ss.accept();
				DataInputStream dis = new DataInputStream(s.getInputStream());
				int n = dis.readInt();
				ArrayList<Integer> res = cyracuseSeries(n);
				ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
				oos.writeObject(res);
				s.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
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
