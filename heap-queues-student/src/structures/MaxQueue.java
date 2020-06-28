package structures;

import comparators.IntegerComparator;

import java.util.Comparator;
import java.util.Iterator;

public class MaxQueue<V> implements PriorityQueue<Integer, V> {
	
	private StudentArrayHeap<Integer, V> maxHeap = new StudentArrayHeap<>(new IntegerComparator());

	@Override
	public PriorityQueue<Integer, V> enqueue(Integer priority, V value) {
		// TODO Auto-generated method stub
		if(priority == null || value == null) {
			throw new NullPointerException();
		}
		maxHeap.add(priority, value);
		return this;
	}

	@Override
	public V dequeue() {
		// TODO Auto-generated method stub
		if(this.isEmpty()) {
			throw new IllegalStateException();
		}
		V val = maxHeap.remove();
		return val;
	}

	@Override
	public V peek() {
		// TODO Auto-generated method stub
		return maxHeap.peek();
	}

	@Override
	public Iterator<Entry<Integer, V>> iterator() {
		// TODO Auto-generated method stub
		return maxHeap.heap.iterator();
	}

	@Override
	public Comparator<Integer> getComparator() {
		// TODO Auto-generated method stub
		return maxHeap.getComparator();
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return maxHeap.size();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return maxHeap.isEmpty();
	}
}
