import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		gameBoard board = new gameBoard();
		String currentTurn = "player";
		
		while((!board.isBoardFull()) && (board.isGameWon() == 0)) {
			switch(currentTurn) {
				case "player": 
					board.playerMove();
					currentTurn = "comp";
					break;
				case "comp":
					board.computerMove();
					currentTurn = "player";
					break;
			}
		}
		
		board.declareWinner();
	}

}
