package structures;

import comparators.ReverseIntegerComparator;

import java.util.Comparator;
import java.util.Iterator;

public class MinQueue<V> implements PriorityQueue<Integer, V> {

	private StudentArrayHeap<Integer, V> minHeap = new StudentArrayHeap<>(new ReverseIntegerComparator());

	@Override
	public PriorityQueue<Integer, V> enqueue(Integer priority, V value) {
		// TODO Auto-generated method stub
		if(priority == null || value == null) {
			throw new NullPointerException();
		}
		minHeap.add(priority, value);
		return this;
	}

	@Override
	public V dequeue() {
		// TODO Auto-generated method stub
		if(this.isEmpty()) {
			throw new IllegalStateException();
		}
		V val = minHeap.remove();
		return val;
	}

	@Override
	public V peek() {
		// TODO Auto-generated method stub
		return minHeap.peek();
	}

	@Override
	public Iterator<Entry<Integer, V>> iterator() {
		// TODO Auto-generated method stub
		return minHeap.heap.iterator();
	}

	@Override
	public Comparator<Integer> getComparator() {
		// TODO Auto-generated method stub
		return minHeap.getComparator();
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return minHeap.size();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return minHeap.isEmpty();
	}
}
