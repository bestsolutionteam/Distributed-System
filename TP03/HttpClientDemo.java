package TP03;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;


public class HttpClientDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Socket s = new Socket("localhost", 8080);
			PrintStream out = new PrintStream(s.getOutputStream(), true);
//			out.println("GET /Fan.html HTTP/1.0");
			out.println();
			
			BufferedReader in = new BufferedReader(
									new InputStreamReader(s.getInputStream()));
			String line = null;
			while ((line = in.readLine()) != null) {
				System.out.println(line);
			}
			s.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
