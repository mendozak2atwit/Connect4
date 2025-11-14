package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.InputMismatchException ;
import java.util.Scanner;

import connectFour.Board;
import connectFour.Board.pieceColor;

public class Client {

    final static int SERVER_PORT = 9154;
    
    public static void main(String[] args) {
        // open a Scanner and DatagramSocket object
        try (Scanner input = new Scanner(System.in); DatagramSocket clientSocket = new DatagramSocket()) {
        
            // CONNECTION STATE
            
            // ip address of server
            String ip;
            InetAddress serverAddress = null;
            
            // set 5000ms (5s) connection timeout
            clientSocket.setSoTimeout(5000);
            
            // send code indicating success
            String send = "100 Ready to play!";
            byte[] sendData = send.getBytes();
            
            // packet to send
            DatagramPacket sendPacket;
            
            // receiving packet
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            
            // ip connection loop until success
            boolean checkIp = true;
            do {
                try {
                    // ask for ip of server
                    System.out.printf("Enter the ip address: ");
                    ip = input.next();
                    
                    // System.out.println(ip);
                    
                    // attempt to connect
                    serverAddress = InetAddress.getByName(ip);
                    
                    // send to server
                    sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, SERVER_PORT);
                    clientSocket.send(sendPacket);
                    System.out.println("Sent to server: " + send);
                    
                    // receive confirmation
                    clientSocket.receive(receivePacket);
        
                    String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
                    System.out.println("Received from server: " + response);
                    
                    // todo: code check just in case of access in other places like netcat
                    
                    checkIp = false;
                    // if error occurs, try again!
                } catch (SocketException e) {
                    System.out.println("This is a SocketException error: "+e);
                    checkIp = true;
                } catch (UnknownHostException e) {
                    // no such host
                    System.out.println("This is a UnknownHostException error: "+e);
                    checkIp = true;
                } catch (IOException e) {
                    // timeout
                    System.out.println("This is a IOException error: "+e);
                    checkIp = true;
                }
            } while (checkIp);
            
            // after saving the ip
            System.out.printf("Now we can play!\nRed will start first\n\n");
            
            // GAME STATE
            
            // initialize the game
            // color reference
            // server is red (starts first)
            String playerOneColor = pieceColor.RED.getPiece();
            // client is yellow
            String playerTwoColor = pieceColor.YELLOW.getPiece();
            
            // use int for input
            int column = 0;
            
            // some integrity check
            boolean columnIntegrity = false; //unused
            boolean valid;
            
            // initialize and print board
            Board.newBoard();
            Board.printBoard();
            
            // set 300000ms (5m) timeout
            clientSocket.setSoTimeout(300000);
            
            // game loop (until a winner, draw, or timeout? or error?)
            while (!Board.winner) {
                // wait until client receives response
                // in other words: wait until red makes a legal move
                // (should be a valid move, otherwise the server code is messed up ^u^)
                clientSocket.receive(receivePacket);
                String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
                // string to int
                column = Integer.parseInt(response);
                System.out.printf("Red placed on column %s\n", column+1);
                
                // reflect the moves made on your end
                Board.dropPiece(playerOneColor, column);
                Board.printBoard();
                // check win condition
                Board.checkWinner(playerOneColor);
                // if red wins, call the game off
                if (Board.winner) {
                    System.out.printf("Red wins the game!\n");
                    break;
                }
                // todo: discard extra inputs made during server's turn (eventually? i don't think it's a major deal (yet))
            
                // choose a column wisely
                valid = false;
                while (!valid) {
                    try {
                        System.out.printf("Choose a column (1 - 7):\n");
                        column = input.nextInt();
                        // check if it's in range (0 < column < 8)
                        if (column < 1 || column > 7) {
                            System.out.printf("Column out of range\n");
                        // check if the column's full
                        } else if (!Board.checkDropPiece(column - 1)) {
                            System.out.printf("Column is full\n");
                        // looks good enough
                        } else {
                            valid = true;
                        }
                    } catch (InputMismatchException e) {
                        // you've entered a non-int
                        System.out.printf("Non-integer number entered\n");
                        input.next();
                        valid = false;
                    }
                }
                
                // int to string
                send = Integer.toString(column-1);
                // send the position to server
                sendData = send.getBytes();
                
                sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, SERVER_PORT);
                clientSocket.send(sendPacket);
                
                // reflect the moves made on your end
                Board.dropPiece(playerTwoColor, column-1);
                Board.printBoard();
                // check win condition
                Board.checkWinner(playerTwoColor);
                // if yellow wins, print message
                // no need for break cause it's at the very end :p
                if (Board.winner) {
                    System.out.printf("Yellow wins the game!\n");
                }
            }
            System.out.printf("the game is over man :(");
            // end of game (unless we add a continue function)
            // close input, clientSocket
            input.close();
            clientSocket.close();
        } catch (SocketException e) {
            System.out.printf("\nI messed up the socket somehow: %s", e);
        } catch (IOException e) {
            System.out.printf("\nServer has probably been timed out and therefore, disconnected");
        }
    }

}
