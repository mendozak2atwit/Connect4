package connectFour;

import java.util.Scanner;

import connectFour.Board.pieceColor;

public class Test {
	
	static String playerOneColor = pieceColor.RED.getPiece();
	static String playerTwoColor = pieceColor.YELLOW.getPiece();
	
	public static void main(String[] args) {
		
		Board.newBoard();
		Scanner placeInput = new Scanner(System.in);
		int place;
		
		System.out.println("Player one is Red!");
		System.out.println("Player two is Yellow!");
		
		System.out.println("This is the current board: ");
		Board.printBoard();
		
		System.out.print("Choose a column (0 - 6): ");
		place = placeInput.nextInt();
		
		if(Board.checkDropPiece(place) == true && Board.checkFullBoard() == false) {
			Board.dropPiece(playerOneColor, place);
			System.out.println("passes");
		}
		
		System.out.println("Current Board: ");
		Board.printBoard();
		
		System.out.print("Choose a column (0 - 6): ");
		place = placeInput.nextInt();
		
		if(Board.checkDropPiece(place) == true && Board.checkFullBoard() == false) {
			Board.dropPiece(playerOneColor, place);
			System.out.println("passes");
		}
		
		System.out.println("Current Board: ");
		Board.printBoard();
	}
	
	

}
