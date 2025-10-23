package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class ConnectServer {

	public static void main(String[] args) {
		int port = 1738;
		
		try(DatagramSocket serverSocket = new DatagramSocket(port)){
			System.out.println("Attempting to connect to port: " + port);
			
			byte[] receiveData = new byte[1024];
			
			while(true) {
				 DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
	             serverSocket.receive(receivePacket);
	             
	             System.out.println("Successfully connected to port!");
	             System.out.print("Your color is red!");
	             
			}
			
			
			
		}catch (SocketException e) {
			System.err.println("Socket error: " + e.getMessage());
		}catch (IOException e) {
			System.out.println("I/O error: " + e.getMessage());
		}
	}

}
