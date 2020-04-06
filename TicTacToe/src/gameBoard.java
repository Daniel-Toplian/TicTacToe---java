import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class gameBoard {
	// Data members
	private final int cellNum = 3;
	private String[][] board;
	public String currentTurn = "Player";
	Scanner scan = new Scanner(System.in);
	
	
	
	// Constarctor
	public gameBoard() {
		this.board = new String[this.cellNum][this.cellNum];
		this.restartBoard();
	}
	
	//functions
	private void setTurn(String currentTurn) {
		this.currentTurn = currentTurn;
	}
	
	public String getTurn() {
		return this.currentTurn;
	}
	
	private String[][] getBoard() {
		return this.board;
	}
	
	public void restartBoard() {
			
		for(int row = 0; row < this.cellNum; row++) {
			
			for(int col = 0; col < this.cellNum; col++) {
				this.getBoard()[row][col]	= " ";
			}
		}
	}
	
	public boolean isBoardFull() {
		
		for(int row = 0; row < 3; row++) {
			
			for(int col = 0; col < 3; col++) {
				
				if(this.getBoard()[row][col]	== " ") {
					return false;
				}
			}
		}
		
		return true;
	}
	
	
	public void draw() {
		
		for(int row = 0; row < 3; row++) {
			System.out.println((this.getBoard()[row][0] + '|' + this.getBoard()[row][1] + '|' + this.getBoard()[row][2]));
			if (row < 2) {
				System.out.println("-+-+-");
			}					
		}
		
		System.out.println();
	}
	
	private void placement(char character,int pos) {
		
		if(pos <= 3) {
			this.getBoard()[0][pos - 1] = String.valueOf(character);
		
		} else if(pos <= 6) {
			this.getBoard()[1][pos - 4] = String.valueOf(character);
			
		} else if(pos <= 9) {
			this.getBoard()[2][pos - 7] = String.valueOf(character);
		}
	}
	
	private boolean isCellClear(int cell) {
		boolean isClear = false;
		
		if(cell <= 3) {
			if(this.getBoard()[0][cell - 1] == " ") {
				isClear = true;
			}
		} else if(cell <= 6) {
			if(this.getBoard()[1][cell - 4] == " ") {
				isClear = true;
			}
		} else if(cell <= 9) {
			if(this.getBoard()[2][cell - 7] == " ") {
				isClear = true;
			}
		}
		
		return isClear;
	}
	
	public void playerMove() {
		
		boolean isTurnValid = false;
		char player = 'x';
		Integer pos;
				
			this.draw();
			System.out.println("Choose position between 1-9:");
			try {
				pos = scan.nextInt();
			} catch(Exception e) {
				System.out.println("Please enter a number..");
				pos = pos = scan.nextInt();
			}
			
			
				if((pos < 10) && (pos > 0)) {
					if(this.isCellClear(pos)) {
						this.placement(player,pos);
					} else {
						System.out.println("Cell is not empty! choose a different one.");
						playerMove();
					}
				} else {
					System.out.println("Please enter a number in range!");
					playerMove();
				}
	}
	
	public void computerMove() {
		char computer = 'o';
		ArrayList<Integer> avilableCells = new ArrayList<Integer>();
		int pos;
		Random random = new Random();
		
		for(int cell = 1; cell < 10; cell++) {
			if(this.isCellClear(cell)) {
				avilableCells.add(cell);
			}
		}
		
		pos = avilableCells.get(random.nextInt(avilableCells.size()));
		this.placement(computer,pos);
	}
	
	
	public boolean isGameWon() {
		
		boolean isWon = false;
		
		if(isRowWin()) {
			isWon = true;
				
		} else if(isColWin()) {
				isWon = true;
		} else if(isCrossWon()) {
				isWon = true;
		}
		
		return isWon;
	}
	
	
	private boolean isRowWin() {
		boolean result = false;
		
		for(int row = 0; row < cellNum; row++) {
			if((this.getBoard()[row][0] != " ")) {
				if((this.getBoard()[row][0].equals(this.getBoard()[row][1]))&&
				   (this.getBoard()[row][0].equals(this.getBoard()[row][2]))) {
					result = true;
					break;
				}
			}
		}
		
		return result;
	}
		
	private boolean isColWin() {
		boolean result = false;
		
		for(int col = 0; col < cellNum; col++) {
			if(this.getBoard()[0][col] != " ") {
				if((this.getBoard()[0][col].equals(this.getBoard()[1][col]))&&
				   (this.getBoard()[0][col].equals(this.getBoard()[2][col]))) {
					result = true;
					break;	
				}
			}
		}
		
		return result;
	}
	
	private boolean isCrossWon() {
		boolean result = false;
		if(!this.getBoard()[1][1].equals(" ")) {
			if ((((this.getBoard()[0][0].equals(this.getBoard()[1][1])) && 
					(this.getBoard()[0][0].equals(this.getBoard()[2][2]))) || 
					((this.getBoard()[0][2].equals(this.getBoard()[1][1])) && 
					(this.getBoard()[0][2].equals(this.getBoard()[2][0]))))) {
				result = true;
			}
		}
		
		return result;
	}
}
