package structures;

import java.util.Iterator;

public class ScapegoatTree<T extends Comparable<T>> extends
		BinarySearchTree<T> {
	private int upperBound;


	@Override
	public void add(T t) {
		if(t == null){
			throw new NullPointerException();
		}
		upperBound++;
		super.add(t);
	}

	@Override
	public boolean remove(T element) {
		if(element == null){
			throw new NullPointerException();
		}
		if(this.contains(element)){
			super.remove(element);
		
			if(upperBound > (2*this.size())){
				this.balance();
				upperBound = this.height();
			}
			this.balance();
		}
		return this.contains(element);
	}
}
