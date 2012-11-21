package test;

/*************************************************************************
 *  Compilation:  javac ChatClient.java
 *  Execution:    java ChatClient name host
 *  Dependencies: In.java Out.java
 *
 *  Connects to host server on port 4444, enables an interactive
 *  chat client.
 *  
 *  % java ChatClient alice localhost
 *
 *  % java ChatClient bob localhost
 *  
 *************************************************************************/


import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import test.client2.receiveDataTh;
import test.client2.thread1;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ChatClient1 extends JFrame implements ActionListener {

    private String screenName;
    private String YourName,MyName;

    // GUI stuff
    private JTextArea  enteredText = new JTextArea(10, 32);
    private JTextField typedText   = new JTextField(32);

    // socket for connection to chat server

    // for writing to and reading from the server
    DatagramSocket socketC;
    public ChatClient1(DatagramSocket s,String m,String y) {
    	MyName = m;
    	YourName = y;
    	receiveDataTh r = new receiveDataTh(s);
    	r.start();
        socketC = s;
        // close output stream  - this will cause listen() to stop and exit
        addWindowListener(
            new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                }
            }
        );


        // create GUI stuff
        enteredText.setEditable(false);
        enteredText.setBackground(Color.LIGHT_GRAY);
        typedText.addActionListener(this);

        Container content = getContentPane();
        content.add(new JScrollPane(enteredText), BorderLayout.CENTER);
        content.add(typedText, BorderLayout.SOUTH);


        // display the window, with focus on typing box
        setTitle("chat with "+YourName);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        typedText.requestFocusInWindow();
        setVisible(true);

    }

    // process TextField after user hits Enter
    public void actionPerformed(ActionEvent e) {
    	try {
			InetAddress adr = InetAddress.getByName("localhost");
			byte[] sendData = new byte[1024];
			String msg = typedText.getText();
			sendData = (msg).getBytes();
			
			DatagramPacket sendPacket = new DatagramPacket(sendData,sendData.length, adr, 2222);
			socketC.send(sendPacket);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
    	enteredText.append(MyName+" : "+typedText.getText()+"\n");
    	typedText.setText("");
        typedText.requestFocusInWindow();
    }
    
    public class receiveDataTh extends Thread {
		public receiveDataTh(DatagramSocket s) {
			socketC = s;
		}

		public void run() {
			try {
				byte[] receiveData = new byte[1024];

				while (true) {
//					System.out.println("client2 prepare receive...");
					DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
					socketC.receive(receivePacket);
					String modifiedSentence = new String(receivePacket.getData(),0,receivePacket.getLength());
					enteredText.append(YourName+" : "+modifiedSentence+"\n");
//					clientSocket.close();
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("erro receive");
			}
		}
	}

    // listen to socket and print everything that server broadcasts

    public static void main(String[] args)  {
    	DatagramSocket socket = null;
    	try {
			socket = new DatagramSocket(1111);
		} catch (SocketException e) {
			e.printStackTrace();
		}
        ChatClient1 client = new ChatClient1(socket,"Fan","JJ");
        
        
    }
}