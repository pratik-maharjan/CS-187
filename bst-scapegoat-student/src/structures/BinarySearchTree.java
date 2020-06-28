package structures;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;

public class BinarySearchTree<T extends Comparable<T>> implements
		BSTInterface<T> {
	protected BSTNode<T> root;

	public boolean isEmpty() {
		return root == null;
	}

	public int size() {
		return subtreeSize(root);
	}

	protected int subtreeSize(BSTNode<T> node) {
		if (node == null) {
			return 0;
		} else {
			return 1 + subtreeSize(node.getLeft())
					+ subtreeSize(node.getRight());
		}
	}

	public boolean contains(T t) {
		if(t == null) {
			throw new NullPointerException();
		}
		return recContains(t, root);
	}
	
	private boolean recContains(T t, BSTNode<T> node) {
		if(node == null) {
			return false;
		}
		else if(t.compareTo(node.getData()) == 0) {
			return true;
		}
		else if(t.compareTo(node.getData()) < 0) {
			return recContains(t, node.getLeft());
		}
		else if(t.compareTo(node.getData()) > 0) {
			return recContains(t, node.getRight());
		}
		else {
			return false;
		}
	}

	public boolean remove(T t) {
		if (t == null) {
			throw new NullPointerException();
		}
		boolean result = contains(t);
		if (result) {
			root = removeFromSubtree(root, t);
		}
		return result;
	}

	private BSTNode<T> removeFromSubtree(BSTNode<T> node, T t) {
		// node must not be null
		int result = t.compareTo(node.getData());
		if (result < 0) {
			node.setLeft(removeFromSubtree(node.getLeft(), t));
			return node;
		} else if (result > 0) {
			node.setRight(removeFromSubtree(node.getRight(), t));
			return node;
		} else { // result == 0
			if (node.getLeft() == null) {
				return node.getRight();
			} else if (node.getRight() == null) {
				return node.getLeft();
			} else { // neither child is null
				T predecessorValue = getHighestValue(node.getLeft());
				node.setLeft(removeRightmost(node.getLeft()));
				node.setData(predecessorValue);
				return node;
			}
		}
	}

	private T getHighestValue(BSTNode<T> node) {
		// node must not be null
		if (node.getRight() == null) {
			return node.getData();
		} else {
			return getHighestValue(node.getRight());
		}
	}

	private BSTNode<T> removeRightmost(BSTNode<T> node) {
		// node must not be null
		if (node.getRight() == null) {
			return node.getLeft();
		} else {
			node.setRight(removeRightmost(node.getRight()));
			return node;
		}
	}

	public T get(T t) {
		if(t == null) {
			throw new NullPointerException();
		}
		return recGet(t,root);
	}
	
	private T recGet(T t, BSTNode<T> node) {
		if(node == null) {
			return null;
		}
		else if(t.compareTo(node.getData()) < 0) {
			return recGet(t, node.getLeft());
		}
		else if(t.compareTo(node.getData()) > 0) {
			return recGet(t, node.getRight());
		}
		else {
			return node.getData();
		}
	}


	public void add(T t) {
		if (t == null) {
			throw new NullPointerException();
		}
		root = addToSubtree(root, new BSTNode<T>(t, null, null));
	}

	protected BSTNode<T> addToSubtree(BSTNode<T> node, BSTNode<T> toAdd) {
		if (node == null) {
			return toAdd;
		}
		int result = toAdd.getData().compareTo(node.getData());
		if (result <= 0) {
			node.setLeft(addToSubtree(node.getLeft(), toAdd));
		} else {
			node.setRight(addToSubtree(node.getRight(), toAdd));
		}
		return node;
	}

	@Override
	public T getMinimum() {
		if(root == null) {
			return null;
		}
		return getLowestValue(root);
	}
	
	private T getLowestValue(BSTNode<T> node) {
		// node must not be null
		if (node.getLeft() == null) {
			return node.getData();
		} else {
			return getLowestValue(node.getLeft());
		}
	}


	@Override
	public T getMaximum() {
		if(root == null) {
			return null;
		}
		return getHighestValue(root);
	}


	@Override
	public int height() {
	return heightRec(root);
		
	}
	
	private int heightRec(BSTNode<T> node) {
		if(node == null) {
			return -1;
		}
		int leftHeight; int rightHeight;
		leftHeight = heightRec(node.getLeft());
		rightHeight = heightRec(node.getRight());
		
		if(rightHeight >= leftHeight) {
			return rightHeight+1;
		}
		else {
			return leftHeight+1;
		}
	}


	public Iterator<T> preorderIterator() {
		Queue<T> queue = new LinkedList<T>();
		preorderTraverse(queue, root);
		return queue.iterator();	
	}
	
	private void preorderTraverse(Queue<T> queue, BSTNode<T> node) {
		if (node != null) {
			queue.add(node.getData());
			preorderTraverse(queue, node.getLeft());
			preorderTraverse(queue, node.getRight());
		}
	}

	public Iterator<T> inorderIterator() {
		Queue<T> queue = new LinkedList<T>();
		inorderTraverse(queue, root);
		return queue.iterator();
	}


	private void inorderTraverse(Queue<T> queue, BSTNode<T> node) {
		if (node != null) {
			inorderTraverse(queue, node.getLeft());
			queue.add(node.getData());
			inorderTraverse(queue, node.getRight());
		}
	}

	public Iterator<T> postorderIterator() {
		Queue<T> queue = new LinkedList<T>();
		postorderTraverse(queue, root);
		return queue.iterator();
	}
	
	private void postorderTraverse(Queue<T> queue, BSTNode<T> node) {
		if (node != null) {
			postorderTraverse(queue, node.getLeft());
			postorderTraverse(queue, node.getRight());
			queue.add(node.getData());
		}
	}

	@Override
	public boolean equals(BSTInterface<T> other) {
		if(other == null) {
			throw new NullPointerException();
		}
		if(root == null && other.getRoot() == null) {
			return true;
		}
		return recEquals(root, other.getRoot());
	}
	
	private boolean recEquals(BSTNode<T> node, BSTNode<T> node2){
		if(node == null && node2 == null){
			return true;
		}
		if(node == null && node2 != null){
			return false;
		}
		if(node != null && node2 == null){
			return false;
		}
		if(!node.getData().equals(node2.getData())){
			return false;
		}
		
		return (recEquals(node.getLeft(), node2.getLeft()) && recEquals(node.getRight(), node2.getRight()));
	}


	@Override
	public boolean sameValues(BSTInterface<T> other) {
		if(other == null) {
			throw new NullPointerException();
		}
		if(root != null && other.getRoot() == null) {
			return false;
		}
		if(root == null && other.getRoot() != null) {
			return false;
		}
		if(root == null && other.getRoot() == null) {
			return true;
		}
		if(this.size() != other.size()) {
			return false;
		}
		Iterator<T> otherIterator = other.inorderIterator();
		Iterator<T> iterator = this.inorderIterator();
		for(int i = 0; i < this.size(); i++){
			if(otherIterator.next().equals(iterator.next())){
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isBalanced() {
		if(this.isEmpty()) {
			return true;
		}
		else if(Math.pow(2,this.height()) <= this.size() && this.size() < Math.pow(2,this.height()+1)) {
			return true;
		}
		return false;
	}

	@Override
    @SuppressWarnings("unchecked")

	public void balance() {
		if(root == null) {
			return;
		}
		if(isBalanced() == true) {
			return;
		}
		ArrayList<T> array = new ArrayList<T>(this.size());
		Iterator<T> iterator = this.inorderIterator();
		for(int i = this.size(); i > 0 ; i--) {
			array.add(iterator.next());
		}
		root = sortedArray2BST(0, this.size(), array);
		
	}
	
	BSTNode<T> sortedArray2BST(int lower, int upper, ArrayList<T> array) {
		if (lower > upper) {
			 return null;
		}
		int mid = (lower + upper) / 2;
		
		if(mid > array.size()-1) {
			return null;
		}
		
		BSTNode<T> node = new BSTNode<T>(array.get(mid),null,null);
		
		node.setLeft(sortedArray2BST(lower, mid - 1,array));
		node.setRight(sortedArray2BST(mid + 1, upper,array));
		return node;
		}


	@Override
	public BSTNode<T> getRoot() {
        // DO NOT MODIFY
		return root;
	}

	public static <T extends Comparable<T>> String toDotFormat(BSTNode<T> root) {
		// header
		int count = 0;
		String dot = "digraph G { \n";
		dot += "graph [ordering=\"out\"]; \n";
		// iterative traversal
		Queue<BSTNode<T>> queue = new LinkedList<BSTNode<T>>();
		queue.add(root);
		BSTNode<T> cursor;
		while (!queue.isEmpty()) {
			cursor = queue.remove();
			if (cursor.getLeft() != null) {
				// add edge from cursor to left child
				dot += cursor.getData().toString() + " -> "
						+ cursor.getLeft().getData().toString() + ";\n";
				queue.add(cursor.getLeft());
			} else {
				// add dummy node
				dot += "node" + count + " [shape=point];\n";
				dot += cursor.getData().toString() + " -> " + "node" + count
						+ ";\n";
				count++;
			}
			if (cursor.getRight() != null) {
				// add edge from cursor to right child
				dot += cursor.getData().toString() + " -> "
						+ cursor.getRight().getData().toString() + ";\n";
				queue.add(cursor.getRight());
			} else {
				// add dummy node
				dot += "node" + count + " [shape=point];\n";
				dot += cursor.getData().toString() + " -> " + "node" + count
						+ ";\n";
				count++;
			}

		}
		dot += "};";
		return dot;
	}

	public static void main(String[] args) {
		for (String r : new String[] { "a", "b", "c", "d", "e", "f", "g" }) {
			BSTInterface<String> tree = new BinarySearchTree<String>();
			for (String s : new String[] { "d", "b", "a", "c", "f", "e", "g" }) {
				tree.add(s);
			}
			Iterator<String> iterator = tree.inorderIterator();
			while (iterator.hasNext()) {
				System.out.print(iterator.next());
			}
			System.out.println();
			iterator = tree.preorderIterator();
			while (iterator.hasNext()) {
				System.out.print(iterator.next());
			}
			System.out.println();
			iterator = tree.postorderIterator();
			while (iterator.hasNext()) {
				System.out.print(iterator.next());
			}
			System.out.println();

			System.out.println(tree.remove(r));

			iterator = tree.inorderIterator();
			while (iterator.hasNext()) {
				System.out.print(iterator.next());
			}
			System.out.println();
		}

		BSTInterface<String> tree = new BinarySearchTree<String>();
		for (String r : new String[] { "a", "b", "c", "d", "e", "f", "g" }) {
			tree.add(r);
		}
		System.out.println(tree.size());
		System.out.println(tree.height());
		System.out.println(tree.isBalanced());
		tree.balance();
		System.out.println(tree.size());
		System.out.println(tree.height());
		System.out.println(tree.isBalanced());
	}
}