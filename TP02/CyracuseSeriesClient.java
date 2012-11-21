package TP02;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;


public class CyracuseSeriesClient {

	public static void main(String[] args) {
		String server = "localhost";
		int    port   = 8888;
		try {
			Socket s = new Socket(server, port);
			DataOutputStream dos = new DataOutputStream(s.getOutputStream());
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Input n : ");
			int n = Integer.parseInt(br.readLine());
			dos.writeInt(n);
			
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			ArrayList<Integer> res = (ArrayList<Integer>)ois.readObject();
			System.out.println("Cyracuse series of " + n + " is " + res);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
