package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

	final static int SERVER_PORT = 9154;
    
    private static boolean validateIp(String ip) {
        try {
            InetAddress.getByName(ip);
            return true;
        } catch(UnknownHostException e) {
            return false;
        }
    }
    
    public static void main(String[] args) {
        try (DatagramSocket clientSocket = new DatagramSocket(); Scanner input = new Scanner(System.in)) {
            String serverName;
            do {
                System.out.printf("Enter the ip address: ");
                serverName = input.next();
                // please let this be a valid ip address
            } while(!validateIp(serverName));
            System.out.println(serverName);
            
            InetAddress serverAddress = InetAddress.getByName(serverName);
            
            String message = "Ready to play!";
            byte[] sendData = message.getBytes();

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, SERVER_PORT);
            clientSocket.send(sendPacket);
            
            System.out.println("Sent to server: " + message);

            byte[] receiveData = new byte[1024];

            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            //DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length + 1);

            clientSocket.receive(receivePacket); // Blocks until a packet is received

            String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Received from server: " + response);
            
            input.close();
        } catch (SocketException e) {
            System.err.println("Socket error: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("I/O error: " + e.getMessage());
        } 
    }

}
