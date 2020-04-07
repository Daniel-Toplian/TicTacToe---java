import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		gameBoard board = new gameBoard();
		String currentTurn = "player";
		String lastTurn = currentTurn;
		
		while((!board.isBoardFull()) && !(board.isGameWon())) {
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
		}
		
		if(board.isGameWon()) {
			board.draw();
			if(lastTurn == "player") {
				System.out.println("You have won the game!");
			} else {
				System.out.println("The computer has won..");
			}
		} else {
			System.out.println("It's a tie!");
		}
	}

}
