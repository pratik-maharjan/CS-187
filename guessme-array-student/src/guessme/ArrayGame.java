package guessme;

/**
 * An Array-based implementation of the Guess-A-Number game
 */
public class ArrayGame {

	// stores the next number to guess
	private int guess;
	private int counter;
	private boolean over;
	private int[] priorGuess;
	private boolean[] eliminate;
	
	
	
	// TODO: declare additional data members, such as arrays that store
	// prior guesses, eliminated candidates etc.

	// NOTE: only primitive type arrays are allowed, such as int[], boolean[] etc.
	// You MAY NOT use any Collection type (such as ArrayList) provided by Java.
	
	/********************************************************
	 * NOTE: you are allowed to add new methods if necessary,
	 * but DO NOT remove any provided method, otherwise your
	 * code will fail the JUnit tests!
	 * Also DO NOT create any new Java files, as they will
	 * be ignored by the autograder!
	 *******************************************************/
	
	// ArrayGame constructor method
	public ArrayGame() {
		guess = 1000;
		this.counter = 0;
		this.over = false;
		this.priorGuess = new int[9999];
		this.eliminate = new boolean[9000];
			for(int i = 0; i<eliminate.length; i++) {
				eliminate[i] = false;
			}
		}
	
	// Resets data members and game state so we can play again
	public void reset() {
		guess = 1000;
		this.counter = 0;
		this.over = false;
		this.priorGuess = new int[9999];
		this.eliminate = new boolean[9000];
		for(int i = 0; i<eliminate.length; i++) {
			eliminate[i] = false;
		}
	}
	
	// Returns true if n is a prior guess; false otherwise.
	public boolean isPriorGuess(int n) {
		for(int i = 0; i < priorGuess.length; i++) {
			if(n == priorGuess[i]) {
				return true;
			}
		}
		return false;
	}
	
	// Returns the number of guesses so far.
	public int numGuesses() {
		return counter;
	}
	
	/**
	 * Returns the number of matches between integers a and b.
	 * You can assume that both are 4-digits long (i.e. between 1000 and 9999).
	 * The return value must be between 0 and 4.
	 * 
	 * A match is the same digit at the same location. For example:
	 *   1234 and 4321 have 0 match;
	 *   1234 and 1114 have 2 matches (1 and 4);
	 *   1000 and 9000 have 3 matches (three 0's).
	 */
	public static int numMatches(int a, int b) { // DO NOT remove the static qualifier
		int count = 0;
		for(int i = 0; i < 4; i++) {
			if(a % 10 == b % 10) {
				count = count +1;
			}
			a = a/10;
			b = b/10;
		}
		return count;
	}
	
	/**
	 * Returns true if the game is over; false otherwise.
	 * The game is over if the number has been correctly guessed
	 * or if all candidates have been eliminated.
	 */
	public boolean isOver() {
		return over;
	}
	
	// Returns the guess number and adds it to the list of prior guesses.
	public int getGuess() {
		
		for(int i = 0; i<eliminate.length; i++) {
			if(eliminate[i] == false) {
				guess = i+1000;
				priorGuess[counter]=guess;
				counter++;
				break;
			}
		}
		return guess;
	}
	
	/**
	 * Updates guess based on the number of matches of the previous guess.
	 * If nmatches is 4, the previous guess is correct and the game is over.
	 * Check project description for implementation details.
	 * 
	 * Returns true if the update has no error; false if all candidates
	 * have been eliminated (indicating a state of error);
	 */
	public boolean updateGuess(int nmatches) {
		if(nmatches == 4) {
			over = true;
			return true;
		}
		
		for(int i = 1000; i<9999; i++) {
			if(ArrayGame.numMatches(i,guess) != nmatches){
				eliminate[i-1000] = true;
			}
		}

		for(int i = 1000; i<9999; i++) {
			if(ArrayGame.numMatches(i,guess) == nmatches){
				if(eliminate[i-1000] != true) {
					guess = i+1000;
					break;
				}
			}
		}
		if(nmatches == 1) {
			return false;
		}
		return true;
	}
	
	// Returns the list of guesses so far as an integer array.
	// The size of the array must be the number of prior guesses.
	// Returns null if there has been no prior guess
	public int[] priorGuesses() {
		if(counter == 0) {
			return null;
		}
		int[] tempG = new int[counter];
		for(int i = 0; i<counter;i++) {
			tempG[i] = priorGuess[i];
		}
		return tempG;
	}
}
