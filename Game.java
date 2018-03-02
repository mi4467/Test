package assignment2;

import java.util.Arrays;
import java.util.Scanner;

public class Game {
	private static Scanner input = new Scanner(System.in);
	private static String userInput;
	private static String answer;
	private static boolean mode;
	private static int guesses;
	private static int blackPeg;
	private static int whitePeg;
	private static int historyIndex =0;
	
	public static void runGame() {
		MastermindUI.startPrompt();
		userInput = input.next();
		while(userInput.equals("Y")) {
			reinitializeGame();
			answer = SecretCodeGenerator.getInstance().getNewSecretCode();
			if(mode) {
				MastermindUI.secretCode(answer);
			}
			System.out.println();
			gameEngine();	
			userInput= input.next();
		}
	}
	
	private static void reinitializeGame() {
		answer=null;
		guesses= GameConfiguration.guessNumber;
		setBlackPeg(0);
		setWhitePeg(0);
		historyIndex=0;
		MastermindUI.setHistory(new String[GameConfiguration.guessNumber]);
		
	}

	private static void gameEngine() {
		String[] tempHistory;
		while(guesses!=0) {
			MastermindUI.guessPrompt();
			userInput = input.next();
			if(!GuessProcessing.checkGuess()) {			//checks to see if valid guess
				if(userInput.equals(answer)) {
					MastermindUI.victoryDisplay();					
					break;
					
				}
				else {
					if(userInput.equals("HISTORY")) {
						MastermindUI.showHistory();
					}
					else {
						MastermindUI.incorrectGuess();
					}
			   }
			}
			else {
				GuessProcessing.processPegs();
				tempHistory = MastermindUI.getHistory();	//keeptrack of history
				tempHistory[historyIndex]=userInput + " -> "+ getBlackPeg() + "b_" + getWhitePeg() + "w";
				MastermindUI.setHistory(tempHistory);
				historyIndex++;
				MastermindUI.displayGuess();
				guesses--;
			}
		}
		if(guesses==0) {
			MastermindUI.lossDisplay();
		}
	}
	
	public static String getUserInput() {
		return userInput;
	}
	
	public static String getAnswer() {
		return answer;
	}

	
	public static int getHistoryIndex() {
		return historyIndex;
	}

	public Game(boolean mode) {
		this.mode = mode;
		guesses = GameConfiguration.guessNumber;
	}

	public Scanner getInput() {
		return input;
	}

	public static int getGuesses() {
		return guesses;
	}

	public static int getWhitePeg() {
		return whitePeg;
	}

	public static void setWhitePeg(int whitePeg) {
		Game.whitePeg = whitePeg;
	}

	public static int getBlackPeg() {
		return blackPeg;
	}

	public static void setBlackPeg(int blackPeg) {
		Game.blackPeg = blackPeg;
	}
}
