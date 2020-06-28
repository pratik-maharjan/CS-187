package stack;

/**
 * A {@link LinkedStack} is a generic stack that is implemented using
 * a Linked List structure to allow for unbounded size.
 */
public class LinkedStack<T> {
	
	// TODO: define class variables here
	private LLNode<T> top;
	private int counter = 0;

	/**
	 * Remove and return the top element on this stack.
	 * If stack is empty, return null (instead of throw exception)
	 */
	public T pop() {
		// TODO
		if (isEmpty()) {
			 return null;
		}
		T element = top.info;
		top = top.link;
		counter--;
		return element;
	}

	/**
	 * Return the top element of this stack (do not remove the top element).
	 * If stack is empty, return null (instead of throw exception)
	 */
	public T top() {
		// TODO
		if (isEmpty()) {
			 return null;
		}
		return top.info;
	}

	/**
	 * Return true if the stack is empty and false otherwise.
	 */
	public boolean isEmpty() {
		// TODO
		return (top==null);
	}

	/**
	 * Return the number of elements in this stack.
	 */
	public int size() {
		// TODO
//		int counter = 0;
//		LLNode node = top;
//		while(node!=null) {
//			counter++;
//			node = node.link;
//		}
		return counter;
	}

	/**
	 * Pushes a new element to the top of this stack.
	 */
	public void push(T elem) {
		// TODO
		LLNode<T> newNode = new LLNode<T>(elem);
		newNode.link = top;
		counter++;
		top = newNode;
	}

}
