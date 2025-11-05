package connectFour;

import java.util.Arrays;

public class Board {
	
	static String[][] board = new String[6][7];
	static int boardHeight = 6;
	static int boardLength = 7;
	static char latestPiece;
	
	public Board() {
		
		//board = new char[6][7];
		
	} //end Board()
	
	//Gives the states that a place in the board can have: a red piece, yellow piece, or the place on the board is empty
	
	
	public static void newBoard() {
		String empty = pieceColor.EMPTY.getPiece();
		
		for(int h = 0; h < boardHeight; h++) {
			for(int l = 0; l < boardLength; l++) {
				board[h][l] = empty;
			}
		}
	}
	
	public static void printBoard() {
	
		for(int h = 0; h < boardHeight; h++) {
			for(int l = 0; l < boardLength; l++) {
				System.out.print(board[h][l]);
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public enum pieceColor {
		EMPTY(" - "),
		RED(" R "), 
		YELLOW(" Y ");
		
		private final String pieceValue;
		
		private pieceColor(String piece){
			this.pieceValue = piece;
		} //end pieceColor
		
		public String getPiece() {
			return pieceValue;
			
		} //end getPiece
		
	} //end enum pieceColor
	
	//Checks to see if there's four of the same color in a row, vertically, horizontally, and 
	public static boolean checkWinner(pieceColor color) {
		
		if(checkWinnerVertically(color) || checkWinnerAcross(color) || checkWinnerHorizontally(color)){
			return true;
		}
		
		return false;
	} //end checkWinner
	
	
	//Checks a full board to see if there's a tie (assume board is already completely full)
	public static boolean checkFullBoard() {
		
		String empty = pieceColor.EMPTY.getPiece();
		
		for(int h = 0; h < boardHeight; h++) {
			for(int l = 0; l < boardLength; l++) {
				if(board[h][l] == empty) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	//checks if a piece can be placed in the specific part of the board (makes sure that the column is not full)
	
	public static boolean checkDropPiece(int placePiece) {
		
	String empty = pieceColor.EMPTY.getPiece();
		
	for(int i = 0; i < board.length; i++) {
		
		if(board[i][placePiece] == empty) {
			return true;
			
		} //end if
	} //end for
		
		return false;
		
	} //end checkDropPiece
	
	//puts the piece into the desired spot on the board
	
	public static void dropPiece(String piece, int placePiece) {
		
		String empty = pieceColor.EMPTY.getPiece();
		
		if(checkDropPiece(placePiece) == true) {
			
			for(int i = 5; i >= 0; i--) {
				
				if(board[i][placePiece] == empty) {
					
					board[i][placePiece] = piece;
					return;
				} //end if
			} //end for
		} //end if
	} //end dropPiece
	
	//Initializes the state of the board by making all spaces EMPTY(0) (could also make the spaces null to make things simpler, will come back to later)
	
	//helper methods
	
	//These methods help check for the winner depending on the way the chips are placed on the board
	
	private static boolean checkWinnerVertically(pieceColor color) {
		
		String findColor = color.getPiece();
		int counter = 0;
		
		for(int h = 0; h < boardLength; h++) {
			for(int l = 0; l < boardHeight; l++) {
				if(board[h][l] == findColor) {
					counter++;
					
				}else {
					counter = 0;
					
				}
				
				if(counter == 4) {
					return true;
					
				}
			}
			
		} //end for
		
		return false;
		
	} //end checkWinnerVertically
	
	
	private static boolean checkWinnerHorizontally(pieceColor color) {
		String findColor = color.getPiece();
		int counter = 0;
		
		for(int l = 0; l < boardHeight; l++) {
			for(int h = 0; h < boardLength; h++) {
				if(board[h][l] == findColor) {
					counter++;
					
				}else {
					counter = 0;
					
				}
				
				if(counter == 4) {
					return true;
					
				}
			}
		
	} //end for
	
	return false;
		
	} //end checkWinnerHorizontally
	
	//Have to figure out an algorithm to figure this out ( this one will be hard )
	//idea 1: when a colored piece is found on the board, check across to see if that same color piece appears 4 in a row
	
	private static boolean checkWinnerAcross(pieceColor color) {
		String findColor = color.getPiece();
		int counter = 0;
		
		for(int h = 0; h < boardHeight; h++) {
			for(int l = 0; l < boardLength; l++) {
				if(board[h][l] == findColor) {
					
					counter = 0;
					int consecuativeLength = l ;
					int consecuativeHeight = h ;
					boolean isConsecuative = true;
					
					while (isConsecuative) {
						
						if(board[consecuativeLength+1][consecuativeHeight+1] == findColor || board[consecuativeLength+1][consecuativeHeight-1] == findColor) {
							counter++;
						}
						
						else {
							counter = 0;
							isConsecuative = false;	
						}
						
						consecuativeHeight++;;

						consecuativeLength++;
					
					}
			
				}
				
				else {
					counter = 0;
					
				}
				
				if(counter == 4) {
					return true;
					
				}
			}
		
		return false;

	}
		return true;
	} //end checkWinnerAcross

	//use for debugging
} //end
