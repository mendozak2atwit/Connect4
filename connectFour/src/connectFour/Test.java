package connectFour;

import java.util.InputMismatchException;
import java.util.Scanner;
import connectFour.Board.pieceColor;

public class Test {
	
	static String playerOneColor = pieceColor.RED.getPiece();
	static String playerTwoColor = pieceColor.YELLOW.getPiece();
	static boolean columnIntegrity = false;
	static boolean valid = false;
	static String playerTurn = playerOneColor;
	
	public static void main(String[] args) {
		Board.newBoard();
		System.out.println("Player one is Red!");
		System.out.println("Player two is Yellow!");
		
		while(Board.winner == false) {
		
		Scanner placeInput = new Scanner(System.in);
		int place = 0;
		
		System.out.println();
		System.out.println("This is the current board: ");
		System.out.printf("Current turn: %s%n", playerTurn);
		Board.printBoard();
			
		while (!valid) {
		
			try {
		
		System.out.print("Choose a column (1 - 7): ");
		place = placeInput.nextInt();
		valid = true;
		
		if(place - 1 > 6 || place - 1 < 0) {
			System.out.println("Please choose a column between (1 - 7)");
			valid = false;
		}
		
		}catch (InputMismatchException e) {
			System.out.println("Please use a viable input (1 - 7)");
			System.out.println();
			placeInput.next();
			
		}
		
		}
		
		while(columnIntegrity == false) {
		
		try {
			Board.checkDropPiece(place - 1);
		}catch (IndexOutOfBoundsException e) {
			System.out.println("The number is too large!");
		}
		
		
		if(Board.checkDropPiece(place - 1) == true && Board.checkFullBoard() == false) {
			Board.dropPiece(playerTurn, place - 1);
			System.out.println("passes");
			columnIntegrity = true;
			
		}else {
			System.out.println("Current column is full! Please choose another one.");
			//columnIntegrity = true;
			}
		
		}
		
		Board.checkWinner(playerTurn);
		
		if(Board.winner == true) {
			if(Board.currentTurn == 0) {
			System.out.println("Red wins!");
			System.out.println("Winning Board: ");
			Board.printBoard();
			System.exit(0);
			
		}else {
			System.out.println("Yellow wins!");
			System.out.println("Winning Board: ");
			Board.printBoard();
			System.exit(0);
			}
			
		}
		
		if(Board.checkFullBoard() == true) {
			System.out.println("The game is a tie!");
			System.exit(0);
		}
		
		valid = false;
		columnIntegrity = false;
		Board.swapTurns();
		
		if(Board.currentTurn == 0){
		playerTurn = playerOneColor;	
		}else {
			playerTurn = playerTwoColor;
		}
		
		}
	}
}
