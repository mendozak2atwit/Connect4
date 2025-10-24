package connectFour;

public class Board {

	private int[][] board = new int[6][7];
	public int latestPiece;
	
	public Board() {
		
		
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
		
	
	//checks if a piece can be placed in the specfic part of the board (makes sure that the column is not full)
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
	
	//puts the piece into the desiered spot in the board
	public void dropPiece(int piece, int placePiece) {
		
		pieceColor empty = pieceColor.EMPTY;
		int emptyPlace = empty.getPiece();
		
		if(checkDropPiece(placePiece)) {
			for(int i = 0; i < this.board.length - 1 ; i++) {
				if(this.board[i][placePiece] != emptyPlace) {
					this.board[i][placePiece] = piece;
				}
			}
		}
		
	}
	
	//Initializes the state of the board by making it empty
	public void InitializeState() {
		pieceColor empty = pieceColor.EMPTY;
		int emptyPlace = empty.getPiece();
		
		for(int i = 0; i < this.board.length; i++) {
			for(int j = 0; j < this.board[i].length; j++) {
				this.board[i][j] = emptyPlace;
			}
		}
		
	}

}
