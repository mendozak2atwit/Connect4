package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Client {

	public static void main(String[] args){
		String serverHostName = "localhost";
		int serverPort = 1738;
		
		try(DatagramSocket clientSocket = new DatagramSocket()){
			InetAddress serverAddress = InetAddress.getByName(serverHostName);
			
			String message = "hi!";
			byte[] sendData = message.getBytes();
			
			System.out.println("Attemting to connect to host");
			
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);
			clientSocket.send(sendPacket);
			System.out.println("Sent to server: " + message);

			byte[] receiveData = new byte[1024];
			
			
		}catch (UnknownHostException e) {
			System.err.println("Unknown host: " + e.getMessage());
		}catch (SocketException e) {
			System.err.println("Socket error: " + e.getMessage());
		}catch (IOException e) {
			System.err.println("I/O error: " + e.getMessage());
		}
	}

}
