package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.InputMismatchException;
import java.util.Scanner;

import connectFour.Board;
import connectFour.Board.pieceColor;

public class ConnectServer {
	
	static String playerOneColor = pieceColor.RED.getPiece();
	static String playerTwoColor = pieceColor.YELLOW.getPiece();
	static boolean columnIntegrity = false;
	static boolean valid = false;
	static String playerTurn = playerOneColor;
	
	public static void main(String[] args) {
	    
		final int SERVER_PORT = 9154;
		
		try(DatagramSocket serverSocket = new DatagramSocket(SERVER_PORT)){
			System.out.println("Attempting to connect to port: " + SERVER_PORT);
			
			byte[] receiveData = new byte[1024];
			
			 DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
             serverSocket.receive(receivePacket);
             
             String response1 = new String(receivePacket.getData(), 0, receivePacket.getLength());
             System.out.print(response1);
             
             // received
             System.out.println("Successfully connected to port!");
             System.out.print("Your color is red!");
             
             // send to client
             String message = "Success! Your color is yellow!";
             byte[] sendData = message.getBytes();
             
             DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,
                                                            receivePacket.getAddress(), receivePacket.getPort());
             serverSocket.send(sendPacket);
			
             Board.newBoard();
             
			// receive
			while(Board.winner == false) {
				if(playerTurn  == playerTwoColor) {
					System.out.println("Yellow's Turn!");
					System.out.println("Current Board: ");
					Board.printBoard();
					
					System.out.println("Waiting for Yellow to choose a column. . . ");
					
					byte[] receiveColumn = new byte[1024];
					DatagramPacket receiveColumnData = new DatagramPacket(receiveColumn, receiveColumn.length);
					serverSocket.receive(receiveColumnData);
					
					String response = new String(receiveColumnData.getData(), 0, receiveColumnData.getLength());
					Integer numResponse = Integer.valueOf(response);
					int playerTwoPlace = numResponse;
					
					Board.dropPiece(playerTurn, playerTwoPlace);
					
					System.out.print("Current Board: ");
					Board.printBoard();
					
					Board.checkWinner(playerTurn);
					if(Board.winner == false) {
						playerTurn = playerOneColor;
					}
					
					valid = false;
					
				}else {
				Scanner placeInput = new Scanner(System.in); 
				int place = 0;
				
				System.out.println();
				System.out.println("Red's Turn!");
				Board.printBoard();
				
				while(!valid) {
				try {
				System.out.print("Choose a column (0 - 6): ");
				place = placeInput.nextInt();
				valid  = true;
				
				if(place > 6 || place < 0) {
					System.out.println("Please choose a column between (0 - 6): ");
					valid = false;
				}
				
				if(Board.checkDropPiece(place) == false) {
					System.out.println("Column is full, please choose a different column");
					valid = false;
				}
				
				}catch(InputMismatchException e) {
					System.out.println("Please use a viable input");
					System.out.println();
					placeInput.next();
				}
				
				String playerColumn = Integer.toString(place);
				byte[] sendColumn = playerColumn.getBytes();
				
				DatagramPacket sendPlayerColumn = new DatagramPacket(sendColumn, sendColumn.length, receivePacket.getAddress(), receivePacket.getPort());
				 
				serverSocket.send(sendPlayerColumn);
				
				Board.dropPiece(playerTurn, place);
				System.out.println("New Board: ");
				Board.printBoard();
				
				Board.checkWinner(playerTurn);
				
				if(Board.winner == false) {
					playerTurn = playerTwoColor;
				}
				
				}
			}
			
		}
		
			
		if(playerTurn == playerOneColor) {
			System.out.println("Red wins!");
			
		}else {
			System.out.print("Yellow wins!");

		}
		
		System.out.println("Winning Board:");
		Board.printBoard();
			
		}catch (SocketException e) {
			System.err.println("Socket error: " + e.getMessage());
		}catch (IOException e) {
			System.out.println("I/O error: " + e.getMessage());
		}
	}
}
