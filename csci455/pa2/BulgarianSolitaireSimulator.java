
// Name: Yang Wei
// USC loginid: wei495
// CSCI455 PA2
// Spring 2016

import java.util.Scanner;

/**
 * <add main program comment here>
 */

public class BulgarianSolitaireSimulator {

	private static SolitaireBoard game;

	public static void main(String[] args) {
		boolean singleStep = false;
		boolean userConfig = false;

		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("-u")) {
				userConfig = true;
			} else if (args[i].equals("-s")) {
				singleStep = true;
			}
		}

		// <add code here>
		SolitaireBoard input = userInput(userConfig);
		configurationOutput(singleStep, input);

	}

	// <add private static methods here>
	/**
	 * Create a input string of piles from user, and check the input is valid or
	 * not, if not valid, retype of input is required
	 * 
	 * @param userConfig:
	 *            the configuration of user input, iff true initial the
	 *            configuration
	 */
	private static SolitaireBoard userInput(boolean userConfig) {
		// SolitaireBoard newGame;
		System.out.println("Number of total cards is " + SolitaireBoard.CARD_TOTAL);
		Scanner input = new Scanner(System.in);
		if (userConfig == true) {
			System.out.println(
					"You will be entering the initial configuration of the cards (i.e., how many in each pile).");

			System.out.println("Please enter a space-separated list of positive integers followed by newline: ");
			String numString = input.nextLine(); // read string that user typed
			/*
			 * This loop is to check the string is or not valid, if false user
			 * need to retype a string
			 */
			while (SolitaireBoard.isValidConfigString(numString) != true) {
				System.out.println("ERROR: Each pile must have at least one card and the total number of cards must be "
						+ SolitaireBoard.CARD_TOTAL);
				System.out.println("Please enter a space-separated list of positive integers followed by newline: ");
				numString = input.nextLine();
			}
			return game = new SolitaireBoard(numString);
		} else {
			return game = new SolitaireBoard();
		}

	}

	/**
	 * Create a output string of piles from user. Two mode in this function, iff
	 * get -s print one round each time. iff not print every round until done.
	 * 
	 * @param singleStep:
	 *            stop at each round iff true, wait for command to continue
	 * @param input:
	 *            the configuration initialized by SolitaireBoard input =
	 *            userInput(userConfig);
	 * 
	 */
	private static void configurationOutput(boolean singleStep, SolitaireBoard input) {

		System.out.println("Initial configuration: " + game.configString());
		Scanner input2 = new Scanner(System.in);
		int count = 1;
		while (game.isDone() == false) {
			game.playRound();
			System.out.println("[" + count + "] Current configuration: " + game.configString());

			if (singleStep == true) {
				System.out.print("<Type return to continue>");
				input2.nextLine();

			}
			count++;
		}

		System.out.println("Done!");
		input2.close();
	}
}
