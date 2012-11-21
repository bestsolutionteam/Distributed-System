package TP02;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class RemoteClient {

	public static void main(String[] args) {
		String server = "localhost";
		int port = 8888, remote = 0;

		ArrayList<Object> listObject = new ArrayList<Object>();
		try {
			Socket s = new Socket(server, port);
			DataOutputStream dos = new DataOutputStream(s.getOutputStream());
			DataInputStream din = new DataInputStream(s.getInputStream());
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			ObjectOutputStream oOut = new ObjectOutputStream(s.getOutputStream());
			do {
				BufferedReader br = new BufferedReader(new InputStreamReader(
						System.in));
				System.out.print("Remote : ");
				remote = Integer.parseInt(br.readLine());
				dos.writeInt(remote);
				if (remote == 0) {
					System.out.println("OK, Goodbye");
				} else if (remote == 1) {
					System.out.print("Enter a value : ");
					int value = Integer.parseInt(br.readLine());
					dos.writeInt(value);
					int result = din.readInt();
					System.out.print(value);
					if (result == 1) {
						System.out.println(" is premier number");
					} else {
						System.out.println(" is not premier number");
					}
				}
				else if(remote == 2){
					System.out.print("Enter a value : ");
					int value = Integer.parseInt(br.readLine());
					dos.writeInt(value);
					ArrayList<Integer> res = (ArrayList<Integer>) ois.readObject();
					System.out.println(res);
				}
				else if(remote==3){
					System.out.print("Enter a list of number : ");
					try{
					String stringNum = br.readLine();
					ArrayList<Integer> list = new ArrayList<Integer>();
					String tmp = "";
					for(int i = 0;i<stringNum.length();i++){
						if(stringNum.charAt(i)!=' '){
							tmp = tmp + stringNum.charAt(i);
						}
						else{
							list.add(Integer.parseInt(tmp));
							tmp="";
						}
					}
					list.add(Integer.parseInt(tmp));
					System.out.print(list);
					oOut.writeObject(list);
					int result = din.readInt();
					if(result == 1){
						System.out.println(" are coprime.");
					}
					else{
						System.out.println(" are not coprime");
					}
					}catch (Exception e) {
						System.out.println("Wrong Input");
					}
				}
				else{
					System.out.println("There are only 4 remote, 0 1 2 3");
				}
			} while (remote != 0);
			// ObjectInputStream ois = new ObjectInputStream(
			// s.getInputStream());
			// ArrayList<Integer> res = (ArrayList<Integer>) ois.readObject();
			// System.out.println("Cyracuse series of " + remote + " is " +
			// res);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
}
