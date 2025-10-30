
package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * 
 * @author kmp
 *
 * @version 1.0 2025-10-29 Initial implementation
 *
 *
 * @since 1.0
 */
public class Client
    {
        final int SERVER_PORT = 9154;
    
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
                    // message can be changed if you want this is just temp stuff until
                    System.out.printf("Enter the turi ip ip ip ip ip address: ");
                    serverName = input.next();
                    // please let this be a valid ip address
                } while(!validateIp(serverName));
                System.out.println(serverName);
                input.close();
            } catch (SocketException e) {
                System.err.println("Socket error: " + e.getMessage());
            } catch (IOException e) {
                System.err.println("I/O error: " + e.getMessage());
            } 
        }
    }
   // end class Client