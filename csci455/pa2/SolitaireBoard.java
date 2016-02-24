// Name:Yang Wei
// USC loginid: wei495
// CSCI455 PA2
// Spring 2016

/*
   class SolitaireBoard
   The board for Bulgarian Solitaire.  You can change what the total number of cards is for the game
   by changing NUM_FINAL_PILES, below.  Don't change CARD_TOTAL directly, because there are only some values
   for CARD_TOTAL that result in a game that terminates.
   (See comments below next to named constant declarations for more details on this.)
 */
import java.util.Scanner;
import java.util.Random;

public class SolitaireBoard {

	public static final int NUM_FINAL_PILES = 9;
	// number of piles in a final configuration
	// (note: if NUM_FINAL_PILES is 9, then CARD_TOTAL below will be 45)

	public static final int CARD_TOTAL = NUM_FINAL_PILES * (NUM_FINAL_PILES + 1) / 2;
	// bulgarian solitaire only terminates if CARD_TOTAL is a triangular number.
	// see: http://en.wikipedia.org/wiki/Bulgarian_solitaire for more details
	// the above formula is the closed form for 1 + 2 + 3 + . . . +
	// NUM_FINAL_PILES

	/**
	 * Representation invariant:
	 * 
	 * <put rep. invar. comment here>
	 * 
	 */
	private int[] piles;
	private int pilesNum;
	// private Random random;
	// <add instance variables here>
	/*
	 * int[] piles; array represent the card numbers, the value of each element
	 * means the numbers of card in this pile
	 * 
	 * int pilesNum; represent how many piles in the SolitaireBoard each element
	 * in the piles must less or equal than 45 and great than 0; The sum of the
	 * element in array piles = CARD_TOTAL 0 < pilesNum <= CARD_TOTAL
	 * 
	 * Random generator; avoiding creating new Random object every time a random
	 * number needed to be generated
	 */

	/**
	 * Creates a solitaire board with the given configuration. PRE:
	 * SolitaireBoard.isValidConfigString(numberString)
	 */
	public SolitaireBoard(String numberString) {
		piles = new int[CARD_TOTAL];
		pilesNum = validpilesNum(numberString, piles);
		assert isValidSolitaireBoard(); // sample assert statement (you will be
										// adding more of these calls)
	}

	/**
	 * Creates a solitaire board with a random initial configuration.
	 */
	public SolitaireBoard() {

		piles = new int[CARD_TOTAL];
		int cardLeft = CARD_TOTAL; // card that not in put in piles
		int Loc = 0; // the location in array piles
		while (cardLeft > 0 && Loc < CARD_TOTAL) {
			Random random = new Random();
			piles[Loc] = random.nextInt(cardLeft) + 1; // the number must >
														// 0, so add 1
			cardLeft -= piles[Loc];
			Loc++;
		}
		pilesNum = Loc;
		assert isValidSolitaireBoard();
	}

	/**
	 * Plays one round of Bulgarian solitaire. Updates the configuration
	 * according to the rules of Bulgarian solitaire: Takes one card from each
	 * pile, and puts them all together in a new pile. The old piles that are
	 * left will be in the same relative order as before, and the new pile will
	 * be at the end.
	 */
	public void playRound() {
		// This for loop is to minus 1 in each pile

		for (int i = 0; i < pilesNum; i++) {
			piles[i] -= 1;
		}
		// delete element == 0
		int newGroupNum = pilesNum; // the new pile will have newGroupNum cards
		int newPilesNum = 0;
		int[] pilesNew = new int[CARD_TOTAL];
		int j = 0;
		while (j < pilesNum) {
			if (piles[j] != 0) {
				pilesNew[newPilesNum] = piles[j];
				newPilesNum++;
			}
			j++;
		}
		pilesNew[newPilesNum] = newGroupNum;
		newPilesNum++;
		pilesNum = newPilesNum;
		piles = pilesNew;

		assert isValidSolitaireBoard();
	}

	/**
	 * Returns true iff the current board is at the end of the game. That is,
	 * there are NUM_FINAL_PILES piles that are of sizes 1, 2, 3, . . . ,
	 * NUM_FINAL_PILES, in any order.
	 */

	public boolean isDone() {
		int[] uniqueNumber = new int[NUM_FINAL_PILES]; // The array only have
														// element 0 or postive
														// interger, each
														// integer represent a
														// unique
														// number in array piles
		int count = 0; // count how many unique number in array uniqueNumber
		/*
		 * In this for loop, we pick up element from array piles and store them
		 * into new array uniqueNumber. The element with same value will be
		 * store in the same location in array uniqueNUmber
		 */
		for (int i = 0; i < NUM_FINAL_PILES; i++) {
			if (piles[i] > 0 && piles[i] <= NUM_FINAL_PILES) {
				uniqueNumber[piles[i] - 1] = piles[i];
			}
		}
		for (int i = 0; i < NUM_FINAL_PILES; i++) {
			if (uniqueNumber[i] != 0) {
				count++;
			}
		}
		if (count != NUM_FINAL_PILES) {
			return false; // some element in array is not unique, so is not done
		}
		assert isValidSolitaireBoard();
		return true;

	}
	// In order to achieve O(n), redo this part
	/*
	 * for (int i = 1; i <= NUM_FINAL_PILES; i++){ int j = 0; while(j <
	 * pilesNum){ if(piles[j] == i){ count++; j++; } j++; } } if(count !=
	 * NUM_FINAL_PILES){ return false; } assert isValidSolitaireBoard(); return
	 * true;
	 */

	/**
	 * Returns current board configuration as a string with the format of a
	 * space-separated list of numbers with no leading or trailing spaces. The
	 * numbers represent the number of cards in each non-empty pile.
	 */
	public String configString() {
		String cfigString = ""; // error fixed, not initialized
		for (int i = 0; i < pilesNum; i++) {
			cfigString = cfigString + piles[i] + " ";
		} // There is no space after the last number
			// cfigString = cfigString + piles[pilesNum - 1];
		cfigString = cfigString.trim();
		assert isValidSolitaireBoard();
		return cfigString;
	}

	/**
	 * Returns true iff configString is a space-separated list of numbers that
	 * is a valid Bulgarian solitaire board with card total
	 * SolitaireBoard.CARD_TOTAL
	 */
	public static boolean isValidConfigString(String configString) {
		int[] check = new int[CARD_TOTAL];
		int checkPilesNum = validpilesNum(configString, check);
		return isValidConfig(checkPilesNum, check);

	}

	/**
	 * Returns true iff the solitaire board data is in a valid state (See
	 * representation invariant comment for more details.)
	 */
	private boolean isValidSolitaireBoard() {

		return isValidConfig(pilesNum, piles);
	}

	// <add any additional private methods here>
	/**
	 * Find the valid number of piles in an user given string, convert the
	 * number in given string to an array and return the validpilesNum
	 * 
	 * @param input:
	 *            the input need to be converted
	 * @param piles:
	 *            converted array from string
	 * @param pilesnumber
	 *            the validpilesNum need to be return.
	 */
	private static int validpilesNum(String input, int[] piles) {
		int pilesNumber = 0;
		// Scanning a string: use code showed in textbook 11.2.5
		Scanner lineScanner = new Scanner(input);
		while (lineScanner.hasNextInt()) {
			if (pilesNumber < CARD_TOTAL) {
				piles[pilesNumber] = lineScanner.nextInt();
			} else {
				lineScanner.nextInt();
			}
			pilesNumber++;
		}
		lineScanner.close();
		return pilesNumber;
	}

	/**
	 * Return true iff give numbers and array is valid in required conditions
	 * 
	 * @param Numbers:
	 *            represent how many piles in the SolitaireBoard
	 * @param array:
	 *            array represent the card numbers, the value of each element
	 *            means the numbers of card in this pile
	 */
	private static boolean isValidConfig(int Numbers, int[] array) {
		// false condition 1 Numbers not in the range (1,CARD_TOTAL)
		// false condition 2 element in array not in the range (0,CARD_TOTAL)
		// false condition 3 the sum of element in array is not CARD_TOTAL
		boolean valid = true;
		int sum = 0;
		for (int i = 0; i < Numbers; i++) {
			if (Numbers < 0 || array[i] <= 0 || Numbers > CARD_TOTAL || array[i] > CARD_TOTAL) {
				return !valid;
			}
			sum += array[i];
		}
		if (sum != CARD_TOTAL) {
			return !valid;
		}
		return valid;
	}
}
