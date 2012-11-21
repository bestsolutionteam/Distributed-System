package TP03;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServerDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int port = 8088;
		try {
			ServerSocket ss = new ServerSocket(port);
			while (true) {
				Socket s = ss.accept();
				BufferedReader br = new BufferedReader(new InputStreamReader(
						s.getInputStream()));
				String line = null;
				while (true) {
					line = br.readLine();
					if (line == null || line.equals(""))
						break;
					System.out.println(line);
				}

				PrintStream ps = new PrintStream(s.getOutputStream());
				ps.println("HTTP/1.0 200");
				ps.println();
				ps.println("<html>");
				ps.println("<h2>HHHH</h2>");
				ps.println("</html>");

				s.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
