package structures;

import java.util.Iterator;
public class RecursiveList<T> implements ListInterface<T> {

	private int counter;
	private LLNode<T> head = null;
	private int tempI;

	@Override
	public Iterator<T> iterator() {
		return new ListInterfaceIterator<T>(this.head);
	}

	@Override
	public int size() {
		return counter;
	}

	@Override
	public ListInterface<T> insertFirst(T elem) {
		if(elem == null) {
			throw new NullPointerException();
		}
		LLNode<T> newNode = new LLNode<T>(elem, head);
		head = newNode;
		counter++;
		return this;
	}

	@Override
	public ListInterface<T> insertLast(T elem) {
		if(elem == null) {
			throw new NullPointerException();
		}
		if(this.isEmpty()) {
			LLNode<T> newNode = new LLNode<T>(elem, head);
			head = newNode;
		}
		else {
			insertLast(elem, head);
		}
		counter++;
		return this;
	}
	public ListInterface<T> insertLast(T elem, LLNode<T> node) { // helper
		if(node.next == null) {
			LLNode<T> newNode = new LLNode<T>(elem, null);
			node.next = newNode;
			return this;
		}
		return insertLast(elem, node.next);
	}

	@Override
	public ListInterface<T> insertAt(int index, T elem) {
		if(elem == null) {
			throw new NullPointerException();
		}
		if(index < 0 || index > this.size()) {
			throw new IndexOutOfBoundsException();
		}
		if(this.isEmpty()) {
			LLNode<T> newNode = new LLNode<T>(elem, head);
			head = newNode;
			counter++;
			return this;
		}
		if(index == 0) {
			insertFirst(elem);
			return this;
		}
		else {
			tempI = 0;
			insertAt(index, elem, head);
		}
		return this;
	}
	
	public ListInterface<T> insertAt(int index, T elem, LLNode<T> node){ //helper
		if(tempI == index-1) {
			LLNode<T> newNode = new LLNode<T>(elem, node.next);
			node.next = newNode;
			counter++;
			return this;
		}
		tempI++;
		return insertAt(index, elem, node.next);
	}

	@Override
	public T removeFirst() {
		if(this.isEmpty()) {
			throw new IllegalStateException();
		}
		LLNode<T> newNode = head;
		head = head.next;
		counter--;
		return newNode.data;
	}

	@Override
	public T removeLast() {
		if(this.isEmpty()) {
			throw new IllegalStateException();
		}
		if(this.size() == 1) {
			LLNode<T> newNode = head;
			head = null;
			counter--;
			return newNode.data;
		}
		return removeLast(head);
	}
	
	public T removeLast(LLNode<T> node) { //helper
		if(node.next.next == null) {
			LLNode<T> newNode = node.next;
			node.next = null;
			counter--;
			return newNode.data;
		}
		return removeLast(node.next);
	}

	@Override
	public T removeAt(int i) {
		if(i<0 || i >= this.size()) {
			throw new IndexOutOfBoundsException();
		}
		if(i == 0) {
			LLNode<T> newNode = head;
			head = head.next;
			counter--;
			return newNode.data;
		}
		tempI = 0;
		return removeAt(i, head);
	}
	
	public T removeAt(int i, LLNode<T> node) {//helper
		if(tempI == i-1) {
			LLNode<T> newNode = node.next;
			node.next = newNode.next;
			counter--;
			return newNode.data;
		}
		tempI++;
		return removeAt(i, node.next);
	}

	@Override
	public T getFirst() {
		if(this.isEmpty()) {
			throw new IllegalStateException();
		}
		return head.data;
	}

	@Override
	public T getLast() {
		if(this.isEmpty()) {
			throw new IllegalStateException();
		}
		return getLast(head);
	}
	
	public T getLast(LLNode<T> node) { //helper
		if(node.next == null) {
			return node.data;
		}
		return getLast(node.next);
	}

	@Override
	public T get(int i) {
		// TODO Auto-generated method stub
		if(i<0 || i >= this.size()) {
			throw new IndexOutOfBoundsException();
		}
		if(i == 0) {
			return head.data;
		}
		tempI = 0;
		return get(i, head);
	}
	
	public T get(int i, LLNode<T> node) { //helper
		if(tempI == i-1) {
			LLNode<T> newNode = node.next;
			return newNode.data;
		}
		tempI++;
		return get(i, node.next);
	}

	@Override
	public boolean remove(T elem) {
		// TODO Auto-generated method stub
		if(elem == null) {
			throw new NullPointerException();
		}
		if(head.data == elem) {
			LLNode<T> newNode = head;
			head = newNode.next;
			counter--;
			return true;
		}
		return remove(elem, head);
	}
	
	public boolean remove(T elem, LLNode<T> node) { //helper
		if(node.next == null) {
			return false;
		}
		if(node.next.data == elem) {
			LLNode<T> newNode = node.next;
			node.next = newNode.next;
			counter--;
			return true;
		}
		return remove(elem, node.next);
		
	}

	@Override
	public int indexOf(T elem) {
		// TODO Auto-generated method stub
		if(elem == null) {
			throw new NullPointerException();
		}
		if(head.data == elem) {
			return 0;
		}
		tempI = 0;
		return indexOf(elem, head);
	}
	
	public int indexOf(T elem, LLNode <T> node) { //helper
		if(node.next == null) {
			return -1;
		}
		if(node.next.data == elem) {
			return tempI+1;
		}
		
		tempI++;
		return indexOf(elem, node.next);
	}

	@Override
	public boolean isEmpty() {
		if(this.head == null) {
			return true;
		}
		return false;
	}
}