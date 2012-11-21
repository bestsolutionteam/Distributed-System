package TP02;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class RemoteServer {

	processClient pc;
	Socket s;

	public class processClient extends Thread {
		public processClient(Socket s_) {
			s = s_;
		}

		public boolean isPremier(int n) {
			boolean isPremier = true;
			if (n < 0) {
				isPremier = false;
			} else if (n != 0 && n != 1) {
				for (int i = 2; i <= n / 2; i++) {
					if (n != i && n % i == 0) {
						isPremier = false;
						break;
					}
				}
			}
			return isPremier;
		}

		public int greatestCommonDivisor(int m, int n) {
			int x, y;
			while (m % n != 0) {
				x = n;
				y = m % n;
				m = x;
				n = y;
			}
			return n;
		}

		public void run() {
			int remote;
			try {
				DataInputStream dis = new DataInputStream(s.getInputStream());
				DataOutputStream dout = new DataOutputStream(
						s.getOutputStream());
				ObjectOutputStream oOut = new ObjectOutputStream(
						s.getOutputStream());
				ObjectInputStream oIn = new ObjectInputStream(
						s.getInputStream());
				ArrayList<Integer> list = new ArrayList<Integer>();
				do {
					remote = dis.readInt();
					if (remote == 1) {
						int value = dis.readInt();
						if (isPremier(value)) {
							dout.writeInt(1);
						} else {
							dout.writeInt(0);
						}
					} else if (remote == 2) {
						int value = dis.readInt();
						for (int i = 0; i < value; i++) {
							if (isPremier(i)) {
								list.add(i);
							}
						}
						oOut.writeObject(list);
					} else if (remote == 3) {

						list = (ArrayList<Integer>) oIn.readObject();

						int gcd = greatestCommonDivisor(list.get(0),
								list.get(1));
						if (list.size() > 2) {
							for (int y = 2; y < list.size(); y++) {
								gcd = greatestCommonDivisor(gcd, list.get(y));
							}
						}
						dout.writeInt(gcd);
					}
					System.out.println("Get from port " + s.getPort() + " = "
							+ remote);
				} while (remote != 0);
				s.close();
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public void createProcess(Socket s) {
		pc = new processClient(s);
		pc.start();
		// pc.stop();
	}

	public static void main(String[] args) {
		int port = 8888;
		try {
			ServerSocket ss = new ServerSocket(port);
			System.out.println("Cyracuse series server is running...");
			// ArrayList<Integer> li = new ArrayList<Integer>();
			// li.add(1);li.add(2);li.add(3);li.add(4);
			// ArrayList<Object> listObject = new ArrayList<Object>();
			// listObject.add(11);
			// listObject.add(li);
			// ArrayList<Integer> list = (ArrayList<Integer>) listObject.get(0);
			// System.out.println(list);

			while (true) {

				Socket s = ss.accept();
				RemoteServer rs = new RemoteServer();
				rs.createProcess(s);
				// System.out.println("Port : "+s.getPort());

				// ArrayList<Integer> res = cyracuseSeries(n);
				//
				//
				// ObjectOutputStream oos = new
				// ObjectOutputStream(s.getOutputStream());
				// oos.writeObject(res);
				// s.close();

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static ArrayList<Integer> cyracuseSeries(int n) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		while (n != 1) {
			n = nextCyracuse(n);
			res.add(new Integer(n));
		}
		return res;
	}

	private static int nextCyracuse(int n) {
		if (n % 2 == 0) {
			return n / 2;
		} else {
			return 3 * n + 1;
		}
	}

}
