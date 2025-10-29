package connectFour;

public class Board {

	private int[][] board = new int[6][7];
	private int boardHeight = 6;
	private int boardLength = 7;
	public int latestPiece;
	
	public Board() {
		
		InitializeState();
		
	}
	
	//Gives the states that a place in the board can have: a red piece, yellow piece, or the place on the board is empty
	public enum pieceColor {
		EMPTY(0),
		RED(1), 
		YELLOW(2);
		
		private final int pieceValue;
		
		private pieceColor(int piece){
			this.pieceValue = piece;
		}
		
		public int getPiece() {
			return pieceValue;
		}
	}
	
	//Checks to see if there's four of the same color in a row, vertically, horizontally, and 
	public boolean checkWinner(int[][] board) {
		
		return true;
	}
		
	
	//checks if a piece can be placed in the specific part of the board (makes sure that the column is not full)
	
	public boolean checkDropPiece(int placePiece) {
		
	pieceColor empty = pieceColor.EMPTY;
	int emptyPlace = empty.getPiece();
		
	for(int i = 0; i < this.board.length; i++) {
		
		if(this.board[i][placePiece] == emptyPlace) {
			return true;
			
		}
	}
		
		return false;
		
	}
	
	//puts the piece into the desired spot on the board
	
	public void dropPiece(int piece, int placePiece) {
		
		pieceColor empty = pieceColor.EMPTY;
		int emptyPlace = empty.getPiece();
		
		if(checkDropPiece(placePiece)) {
			
			for(int i = 0; i < this.board.length; i++) {
				
				if(this.board[i][placePiece] != emptyPlace) {
					
					this.board[i][placePiece] = piece;
				}
			}
		}else {
			
			System.out.print("Column is full!");
			
		}
		
	}
	
	//Initializes the state of the board by making all spaces EMPTY(0) (could also make the spaces null to make things simpler, will come back to later)
	public void InitializeState() {
		pieceColor empty = pieceColor.EMPTY;
		int emptyPlace = empty.getPiece();
		
		for(int i = 0; i < this.board.length; i++) {
			for(int j = 0; j < this.board[i].length; j++) {
				this.board[i][j] = emptyPlace;
			}
		}
		
	}
	
	//helper methods
	
	//These methods help check for the winner depending on the way the chips are placed on the board
	
	private boolean checkWinnerVertically() {
		for(int i = 0; i < boardLength; i++) {
			
		}
		
		return true;
	}
	
	private boolean checkWinnerHorizontally() {
		for(int i = 0; i < boardHeight; i++) {
			
		}
		
		return true;
	}
	
	//Have to figure out an algorithm to figure this out ( this one will be hard )
	//idea 1: when a colored piece is found on the board, check across to see if that same color piece appears 4 in a row
	
	private boolean checkWinnerAcross() {
		
		return true;
	}

}
