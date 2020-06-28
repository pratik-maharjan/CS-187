package guessme;

/**
 * A LinkedList-based implementation of the Guess-A-Number game
 */
public class LinkedListGame {

	// TODO: declare data members as necessary
	private int guess;
	private int counter;
	private boolean over;
	LLIntegerNode priorGuess;
	LLIntegerNode eliminate;
	LLIntegerNode priorGuessNode;
	private int containMatch;
	static LLIntegerNode newNode;
	
	/********************************************************
	 * NOTE: for this project you must use linked lists
	 * implemented by yourself. You are NOT ALLOWED to use
	 * Java arrays of any type, or any class in the java.util
	 * package (such as ArrayList).
	 *******************************************************/	 
	
	/********************************************************
	 * NOTE: you are allowed to add new methods if necessary,
	 * but DO NOT remove any provided method, and do NOT add
	 * new files (as they will be ignored by the autograder).
	 *******************************************************/
	
	// LinkedListGame constructor method
	public LinkedListGame() {
		guess = 1000;
		eliminate = new LLIntegerNode(1000, null);
		LLIntegerNode temp = eliminate;
		for(int i = 1001; i<=9999; i++) {
			LLIntegerNode nextNode = new LLIntegerNode(i, null);
			temp.setLink(nextNode);
			temp = nextNode;
		}
		priorGuess = null;
		priorGuessNode = null;
		counter = 0;
		containMatch = 0;
		over = false;
		newNode = null;
	}
	
	// Resets data members and game state so we can play again
	public void reset() {
		guess = 1000;
		eliminate = new LLIntegerNode(1000, null);
		LLIntegerNode temp = eliminate;
		for(int i = 1001; i<=9999; i++) {
			LLIntegerNode nextNode = new LLIntegerNode(i, null);
			temp.setLink(nextNode);
			temp = nextNode;
		}
		priorGuess = null;
		priorGuessNode = null;
		counter = 0;
		containMatch = 0;
		over = false;
		newNode = null;
	}
	
	// Returns true if n is a prior guess; false otherwise.
	public boolean isPriorGuess(int n) {
		LLIntegerNode traverse = priorGuess;
		while(traverse != null) {
			if(n == traverse.getInfo()) {
				return true;
			}
			traverse = traverse.getLink();
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
	public static int numMatches(int a, int b) {
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
	 * or if no candidate is left.
	 */
	public boolean isOver() {
		return over;
	}
	
	/**
	 * Returns the guess number and adds it to the list of prior guesses.
	 * The insertion should occur at the end of the prior guesses list,
	 * so that the order of the nodes follow the order of prior guesses.
	 */	
	public int getGuess() {	
  		if(priorGuessNode != null) {
  			guess = eliminate.getInfo();
  			LLIntegerNode newNode = new LLIntegerNode(guess, null);
  			priorGuessNode.setLink(newNode);
  			priorGuessNode = newNode;
	   	    counter++;
  		}
  		else {
  			guess = eliminate.getInfo();
  			LLIntegerNode newNode2 = new LLIntegerNode(guess, null);
  			priorGuess = newNode2;
  			priorGuessNode = newNode2;
  			counter++;
	   	}
	  return guess;
	}
	
	/**
	 * Updates guess based on the number of matches of the previous guess.
	 * If nmatches is 4, the previous guess is correct and the game is over.
	 * Check project description for implementation details.
	 * 
	 * Returns true if the update has no error; false if no candidate 
	 * is left (indicating a state of error);
	 */
	public boolean updateGuess(int nmatches) {
		if(nmatches == 4) {
			over = true;
			return true;
		}
		LLIntegerNode possible = null;
		LLIntegerNode candy = eliminate;
		LLIntegerNode tail = possible;
		while(candy != null) {
			int matches = LinkedListGame.numMatches(guess, candy.getInfo());
			containMatch = candy.getInfo();
			if(matches == nmatches) {
				if(tail != null) {
					LinkedListGame.insert(containMatch);
					tail.setLink(newNode);
					tail = newNode;
				}
				else {
					LinkedListGame.insert(containMatch);
					possible = newNode;
					tail = possible;
				}
			}
			candy = candy.getLink();
		}
		eliminate = possible;
		if(eliminate == null) {
			return false;
		}
		return true;
	}
		
	// Returns the head of the prior guesses list.
	// Returns null if there hasn't been any prior guess
	public LLIntegerNode priorGuesses() {
		LLIntegerNode head = priorGuess;
		if( counter == 0) {
			return null;
		}
		return head;
		
	}
	
	/**
	 * Returns the list of prior guesses as a String. For example,
	 * if the prior guesses are 1000, 2111, 3222, in that order,
	 * the returned string should be "1000, 2111, 3222", in the same order,
	 * with every two numbers separated by a comma and space, except the
	 * last number (which should not be followed by either comma or space).
	 *
	 * Returns an empty string if here hasn't been any prior guess
	 */
	public String priorGuessesString() {
		
		if(counter == 0) {
			return "";
		}
		
		String priorG = "";
		LLIntegerNode last = priorGuess;
		while(last != null) {
			if(last.getLink() != null) {
			priorG = priorG + last.getInfo() + ", ";
			}
			else {
				priorG = priorG + last.getInfo();
			}
			last = last.getLink();
		}
		
		return priorG;
	}
	
	public static void insert(int inf) {
		newNode = new LLIntegerNode(inf, null);
	}
	
}
