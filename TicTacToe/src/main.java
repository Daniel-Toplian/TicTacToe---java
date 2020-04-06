import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		gameBoard board = new gameBoard();
		String currentTurn = "player";
		String lastTurn = currentTurn;
		
		while(!board.isBoardFull()) {
			switch(currentTurn) {
				case "player": 
					board.playerMove();
					lastTurn = currentTurn;
					currentTurn = "comp";
					break;
				case "comp":
					board.computerMove();
					lastTurn = currentTurn; 
					currentTurn = "player";
					break;
			}
			
			if(board.isGameWon()) {
				System.out.println("We have a winner - " + lastTurn);
				break;
			}
		}
	}

}
