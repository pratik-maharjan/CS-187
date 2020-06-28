package structures;

import java.util.NoSuchElementException;

/**************************************************************************************
 * NOTE: before starting to code, check support/structures/UnboundedQueueInterface.java
 * for detailed explanation of each interface method, including the parameters, return
 * values, assumptions, and requirements
 ***************************************************************************************/
public class Queue<T> implements UnboundedQueueInterface<T> {
	protected Node<T> front;
	protected Node<T> rear;
	protected int size;

	public Queue() {		
		this.front = null;
		this.rear = null;
		this.size = 0;
    }
	
	public Queue(Queue<T> other) {
		front = other.front;
		rear = other.rear;
		size = other.size;
	}
	
	@Override
	public boolean isEmpty() {
		if(this.size == 0) {
			  return true;
		  }
	    return false;
	}

	@Override
	public int size() {
            return size;
	}

	@Override
	public void enqueue(T element) {
		Node<T> newNode = new Node<T>(element);
		if(front == null) {
			front = newNode;
		}
		else {
			rear.next = newNode;
		}
		rear = newNode;
		size++;
	}

	@Override
	public T dequeue() throws NoSuchElementException {
		if(this.isEmpty()) {
			throw new NoSuchElementException();
		}
		else {
			T element = front.data;
			front = front.next;
			if(front == null) {
				rear = null;
			}
			size--;
			return element;
		}
	}

	@Override
	public T peek() throws NoSuchElementException {
		if(this.isEmpty()) {
			throw new NoSuchElementException();
		}
		else {
			return front.data;
		}
	}
	
	public T pop() throws NoSuchElementException {
		if(this.isEmpty()) {
			throw new NoSuchElementException();
		}
		else {
			T returnValue = front.data;
			dequeue();
			return returnValue;
		}
	}

	
	@Override
	public UnboundedQueueInterface<T> reversed() {
		Queue<T> newlist = new Queue<T>();
		for(Node<T> node=front; node!=null; node = node.next) {
		newlist.front = new Node<T>(node.data, newlist.front);
		newlist.size++;
		}
		return newlist;
	}
}

class Node<T> {
	public T data;
	public Node<T> next;
	public Node(T data) { this.data=data;}
	public Node(T data, Node<T> next) {
		this.data = data; this.next=next;
	}
}

