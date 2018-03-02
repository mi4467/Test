package assignment2;

import java.util.Arrays;

public class GuessProcessing {
	private static int answerColorAmount=0;
	private static int inputColorAmount=0;
	private static int numberOfPegs=0;
	private static int blackPegs=0;
	private static int whitePegs=0;
	
	public static void processPegs() {
		answerColorAmount=0;
		inputColorAmount=0;
		numberOfPegs=0;
		blackPegs=0;
		whitePegs=0;	
		Game.setWhitePeg(0);
		Game.setBlackPeg(0);
		for(int colorIndex = 0; colorIndex< GameConfiguration.colors.length; colorIndex++) {
			for(int index = 0; index<Game.getUserInput().length(); index++) {							//find amount of color in string
				if(GameConfiguration.colors[colorIndex].equals(""+Game.getUserInput().charAt(index))) {
					inputColorAmount++;
				}
			}
			for(int index = 0; index<Game.getAnswer().length(); index++) {							//find amount of color in string
				if(GameConfiguration.colors[colorIndex].equals(""+Game.getAnswer().charAt(index))) {
					answerColorAmount++;
				}
			}
			if(inputColorAmount<answerColorAmount) {
				numberOfPegs= inputColorAmount;
			}
			else {
				numberOfPegs = answerColorAmount;
			}
			int count =0;
			for(int index =0; index< Game.getAnswer().length() && count<numberOfPegs; index++ ) {
			   if(GameConfiguration.colors[colorIndex].equals(""+Game.getUserInput().charAt(index)) && GameConfiguration.colors[colorIndex].equals( "" + Game.getAnswer().charAt(index))) {
				   Game.setBlackPeg(Game.getBlackPeg()+1);
				   count++;
			   }
		   }
		   Game.setWhitePeg(Game.getWhitePeg() + numberOfPegs - count);	
		   count=0;
		   inputColorAmount=0;
		   answerColorAmount=0;
		}				
	}

	public static boolean checkGuess() {
		if(Game.getUserInput().equals(Game.getAnswer())) {
			return false;
		}
		if(Game.getUserInput().equals("HISTORY")) {
			return false;
		}
		if(Game.getUserInput().length()!=GameConfiguration.pegNumber) {;
			return false;
		}
		for(int index = 0; index< Game.getUserInput().length(); index++) {
			if(!Arrays.asList(GameConfiguration.colors).contains("" + Game.getUserInput().charAt(index))) {
				return false;
			}
		}	
		return true;
	}

}
