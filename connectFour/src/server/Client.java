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
    
    public static void main(String[] args) {
        boolean help = false;
        String serverName;
        InetAddress serverAddress;
        Scanner input = new Scanner(System.in);
        do {
            try {
                // ask ip
                System.out.printf("Enter the ip address: ");
                serverName = input.next();
                
                System.out.println(serverName);
                
                // attempt to connect
                serverAddress = InetAddress.getByName(serverName);
                
                String message = "Ready to play!";
                byte[] sendData = message.getBytes();
                
                // send to server
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, SERVER_PORT);
                DatagramSocket clientSocket = new DatagramSocket();
                clientSocket.send(sendPacket);
                System.out.println("Sent to server: " + message);
                
                // receive confirmation
                byte[] receiveData = new byte[1024];
                
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
    
                clientSocket.receive(receivePacket); // Blocks until a packet is received
    
                String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Received from server: " + response);
                
                help = false;
            } catch (SocketException e) {
                help = true;
            } catch (UnknownHostException e) {
                help = true;
            } catch (IOException e) {
                help = true;
            }
        } while (help);
        input.close();
    }

}
