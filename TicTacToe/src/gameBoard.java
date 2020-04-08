import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class gameBoard {
	// Data members
	private final int cellNum = 3;
	private final String blank = " ";
	private final String player = "x";
	private final String ai = "o";
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
				this.getBoard()[row][col] = blank;
			}
		}
	}
	
	public boolean isBoardFull() {
		
		for(int row = 0; row < 3; row++) {
			
			for(int col = 0; col < 3; col++) {
				
				if(this.getBoard()[row][col] == blank) {
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
	
	private void placement(String character,int pos) {
		
		if(pos <= 3) {
			this.getBoard()[0][pos - 1] = character;
		
		} else if(pos <= 6) {
			this.getBoard()[1][pos - 4] = character;
			
		} else if(pos <= 9) {
			this.getBoard()[2][pos - 7] = character;
		}
	}
	
	private boolean isCellClear(int cell) {
		boolean isClear = false;
		
		if(cell <= 3) {
			if(this.getBoard()[0][cell - 1] == blank) {
				isClear = true;
			}
		} else if(cell <= 6) {
			if(this.getBoard()[1][cell - 4] == blank) {
				isClear = true;
			}
		} else if(cell <= 9) {
			if(this.getBoard()[2][cell - 7] == blank) {
				isClear = true;
			}
		}
		
		return isClear;
	}
	
	public void playerMove() {
		
		boolean isTurnValid = false;
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
		int pos = -1;
		int bestScore = Integer.MIN_VALUE; 
		int score;
		ArrayList<Integer> avilableCells = new ArrayList<Integer>();
		Random random = new Random();
		
		for(int cell = 1; cell < 10; cell++) {
			if(isCellClear(cell)) {
				this.placement(ai, cell);
				score = this.miniMax(this.getBoard());
				this.placement(blank, cell);
				if(score > bestScore) {
					bestScore = score;
					pos = cell;
				}
			}
		}
		

		if(pos == -1) {
			for(int cell = 1; cell < 10; cell++) {
				if(this.isCellClear(cell)) {
					avilableCells.add(cell);
				}
			}
			
			pos = avilableCells.get(random.nextInt(avilableCells.size()));
			
		}
		
		this.placement(ai, pos);
	}
	
	
	
	
	private int miniMax(String[][] board) {
		return 1;
	}
	
	
	public int isGameWon() {
		
		int isWon = 0;
		
		if(checksIfCharacterWon(player)) {
			isWon = -1;
		} else if(checksIfCharacterWon(ai)) {
			isWon = 1;
		}
		
		return isWon;
	}
	
	
	private boolean checksIfCharacterWon(String tool) {
		boolean result = false;
		
		if(this.isRowWin(tool)) {
			result = true;
		} else if (this.isColWin(tool)) {
			result = true;
		} else if(this.isCrossWon(tool)) {
			result = true;
		}
		
		return result;
	}
	
	private boolean isRowWin(String tool) {
		boolean result = false;
		
		for(int row = 0; row < cellNum; row++) {
			if((this.getBoard()[row][0] == tool)) {
				if((this.getBoard()[row][0].equals(this.getBoard()[row][1]))&&
				   (this.getBoard()[row][0].equals(this.getBoard()[row][2]))) {
					result = true;
					break;
				}
			}
		}
		
		return result;
	}
		
	private boolean isColWin(String tool) {
		boolean result = false;
		
		for(int col = 0; col < cellNum; col++) {
			if(this.getBoard()[0][col] == tool) {
				if((this.getBoard()[0][col].equals(this.getBoard()[1][col]))&&
				   (this.getBoard()[0][col].equals(this.getBoard()[2][col]))) {
					result = true;
					break;	
				}
			}
		}
		
		return result;
	}
	
	private boolean isCrossWon(String tool) {
		boolean result = false;
		if(this.getBoard()[1][1].equals(tool)) {
			if ((((this.getBoard()[0][0].equals(this.getBoard()[1][1])) && 
					(this.getBoard()[0][0].equals(this.getBoard()[2][2]))) || 
					((this.getBoard()[0][2].equals(this.getBoard()[1][1])) && 
					(this.getBoard()[0][2].equals(this.getBoard()[2][0]))))) {
				result = true;
			}
		}
		
		return result;
	}
	
	public void declareWinner() {
		
		if(this.isGameWon() == -1) {
			System.out.println("Well done.. you win!");
		} else if(this.isGameWon() == 1) {
			System.out.println("Sorry, you have lost the game..");
		} else {
			System.out.println("It's a tie!");
		}
		
		this.draw();
	}
}
