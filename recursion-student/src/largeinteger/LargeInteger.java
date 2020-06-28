package largeinteger;

import largeinteger.LLNode;

/** The LargeInteger class
 *  This class represents a large, non-negative integer using a linked list.
 *  Each node stores a single digit. The nodes represent all digits in *reverse* order:
 *  the least significant digit is the first node, and the most significant the last node.
 *  For example, 135642 is represented as 2->4->6->5->3->1 in that order.
 */
public class LargeInteger {
	private LLNode<Integer> head;	// head of the list
	private int size;				// size (i.e. number of digits)
	
	
	// Returns size
	public int size() { return size; }
	// Returns the linked list (used only for JUnit test purpose)
	public LLNode<Integer> getList() { return head; }
	
	public LargeInteger() {
		head = null; size = 0;
	}
	
	/** Constructor that takes a String as input and constructs the linked list.
	 *  You can assume that the input is guaranteed to be valid: i.e. every character
	 *  in the string is between '0' and '9', and the first character is never '0'
	 *  (unless '0' is the only character in the string). You can use input.charAt(i)-'0'
	 *  to convert the character at index i to the integer value of that digit.
	 *  Remember: the list nodes must be in reverse order as the characters in the string.
	 */
	public LargeInteger(String input) {
		// TODO
		LLNode<Integer> curr = null;
		for(int i = 0; i<input.length(); i++) {
			int a = input.charAt(i)-'0';
			curr = new LLNode<Integer>(a, head);
			head = curr;
		}
		size = input.length();
	}
	
	public LargeInteger reverse(LargeInteger curr) {
		LargeInteger newlist = new LargeInteger();
		for(LLNode<Integer> node = curr.head; node!=null; node = node.link) {
			newlist.head = new LLNode<Integer>(node.data, newlist.head);
			size++;
			
		}
		return newlist;
	}
	
	/** Divide *this* large integer by 10 and return this.
	 *  Assume integer division: for example, 23/10 = 2, 8/10 = 0 and so on.
	 */
	public LargeInteger divide10() {
		// TODO
		if(size == 1) {
			head.data = 0;
			return this;
		}
		else{
			head = head.link;
			size--;
			return this;
		}
		
	}
	
	/** Multiply *this* large integer by 10 and return this.
	 *  For example, 23*10 = 230, 0*10 = 0 etc.
	 */
	public LargeInteger multiply10() {
		// TODO
		if(size == 1) {
			if(head.data == 0) {
				this.head.data = 0;
			}
		}
		else {
				LLNode<Integer> newNode = new LLNode<Integer>(0, this.head);
				head = newNode;
				size++;
		}
		return this;
	}
	
	/** Returns a *new* LargeInteger object representing the sum of this large integer
	 *  and another one (given by that). Your code must correctly handle cases such as
	 *  the two input integers have different sizes (e.g. 2+1000=1002), or there is a
	 *  carry over at the highest digit (e.g. 9999+2=10001).
	 */
	public LargeInteger add(LargeInteger that) {
		
		LargeInteger returnValue = new LargeInteger();
		
		int sum = this.head.data + that.head.data;
		int carryOver = sum/10;
		int remainder = sum % 10;
		returnValue.head = new LLNode<Integer>(remainder, null);
		returnValue.size++;
		LLNode<Integer> temp = this.head;
		LLNode<Integer> temp2 = that.head;
		LLNode<Integer> returnNode = returnValue.head;
		
		while(temp.link != null) {
			sum = carryOver;
				if(this.head.link != null) {
					sum = sum + temp.link.data;
					temp = temp.link;
				}
				if(temp2.link != null) {
					sum = sum + temp2.link.data;
					temp2 = temp2.link;
				}
				carryOver = sum/10;
				remainder = sum % 10;
				
				returnNode.link = new LLNode<Integer>(remainder, null);
				returnValue.size++;
				returnNode = returnNode.link;
		}
		while(temp2.link != null) {
			sum = carryOver;
				if(temp.link != null) {
					sum = sum + temp.link.data;
					temp = temp.link;
				}
				if(temp2.link != null) {
					sum = sum + temp2.link.data;
					temp2 = temp2.link;
				}
				carryOver = sum/10;
				remainder = sum % 10;
				
				returnNode.link = new LLNode<Integer>(remainder, null);
				returnValue.size++;
				returnNode = returnNode.link;
		}
		while(carryOver!=0) {
			sum = carryOver;
				if((temp.link != null) || (temp2.link != null)) {
					sum = sum + temp.link.data + temp2.link.data;
					temp = temp.link;
					temp2 = temp2.link;
				}
				carryOver = sum/10;
				remainder = sum % 10;
				
				returnNode.link = new LLNode<Integer>(remainder, null);
				returnValue.size++;
				returnNode = returnNode.link;
		}
		return returnValue;
	}
	
	/** Returns a new LargeInteger object representing the result of multiplying
	 *  this large integer with a non-negative integer x. You can assume x is either
	 *  a positive integer or 0. Hint: you can use a loop and call the 'add' method
	 *  above to accomplish the 'multiply'.
	 */
	public LargeInteger multiply(int x) {
		
		LargeInteger returnValue = new LargeInteger();
		returnValue.head = new LLNode<Integer>(0,null);
		returnValue.size = 1;
		
		for(int i = 1; i<=x ; i++) {
			returnValue = returnValue.add(this);
		}
		return returnValue;
	}

	/** Recursive method that converts the list referenced by curr_node back to
	 *  a string representing the integer. Think about what's the base case and
	 *  what it should return. Then think about what it should return in non-base case.
	 *  Hint: refer to the 'printing a list backwards' example we covered in lectures.
	 */
	private String toString(LLNode<Integer> curr_node) {
		// TODO
		String returnValue = "";
		if(curr_node != null) {
			returnValue = toString(curr_node.link) + Integer.toString(curr_node.data);
		}
		return returnValue;
	}
	
	/** Convert this list back to a string representing the large integer.
	 *  This is a public method that jump-starts the call to the recursive method above.
	 */
	public String toString() {
		return toString(head);
	}
	
	// Recursive method to compute factorial
	public static LargeInteger factorial(int n) {
		if(n==0) return new LargeInteger("1");
		return factorial(n-1).multiply(n);
	}
	
	// Recursive method to compute power
	public static LargeInteger pow(int x, int y) {
		if(y==0) return new LargeInteger("1");
		return pow(x, y-1).multiply(x);
	}
}
