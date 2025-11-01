package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class ConnectServer {

	public static void main(String[] args) {
	    final int SERVER_PORT = 9154;
		
		try(DatagramSocket serverSocket = new DatagramSocket(SERVER_PORT)){
			System.out.println("Attempting to connect to port: " + SERVER_PORT);
			
			byte[] receiveData = new byte[1024];
			
			while(true) {
				 DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
	             serverSocket.receive(receivePacket);
	             
	             // received
	             System.out.println("Successfully connected to port!");
	             System.out.print("Your color is red!");
	             
	             // send to client
	             String message = "Success! Your color is yellow!";
	             byte[] sendData = message.getBytes();
	             
	             DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,
	                                                            receivePacket.getAddress(), receivePacket.getPort());
	             serverSocket.send(sendPacket);
			}
			
			
			
		}catch (SocketException e) {
			System.err.println("Socket error: " + e.getMessage());
		}catch (IOException e) {
			System.out.println("I/O error: " + e.getMessage());
		}
	}

}
