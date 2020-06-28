package structures;

import java.util.Comparator;
import java.util.Iterator;

public class StudentArrayHeap<P, V> extends AbstractArrayHeap<P, V> {

	protected StudentArrayHeap(Comparator<P> comparator) {
		super(comparator);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected int getLeftChildOf(int index) {
		// TODO Auto-generated method stub
		if(index < 0) {
			throw new IndexOutOfBoundsException();
		}
		int leftChild = 2*index+1;
		return leftChild;
	}

	@Override
	protected int getRightChildOf(int index) {
		// TODO Auto-generated method stub
		if(index < 0) {
			throw new IndexOutOfBoundsException();
		}
		int rightChild = 2*index+2;
		return rightChild;
	}

	@Override
	protected int getParentOf(int index) {
		// TODO Auto-generated method stub
		if(index < 1) {
			throw new IndexOutOfBoundsException();
		}
		int parent = (index-1)/2;
		return parent;
	}

	@Override
	protected void bubbleUp(int index) {
		// TODO Auto-generated method stub
		int parent = (index-1)/2;
		Entry<P, V> elem = new Entry<P, V>(heap.get(index).getPriority(), heap.get(index).getValue());
		
		 while ((index > 0) &&
		 this.comparator.compare(heap.get(index).getPriority(), heap.get(parent).getPriority()) > 0 ) {
		 swap(index, parent);
			 index = parent;
			 parent = (parent-1)/2;
		 }
		 heap.set(index, elem);	
	}

	@Override
	protected void bubbleDown(int index) {
		// TODO Auto-generated method stub
		int largerChild;
		Entry<P, V> elem = new Entry<P, V>(heap.get(index).getPriority(), heap.get(index).getValue());
		
		while (index < size()/2) { // has at least 1 child
			 int left = 2*index+1;
			 int right = 2*index+2;
			 if (right < size() && this.comparator.compare(heap.get(left).getPriority(), heap.get(right).getPriority()) < 0) {
				 	largerChild = right;
			 }
			 else {
			 largerChild = left;
			 }
			 if (this.comparator.compare(heap.get(index).getPriority(), heap.get(largerChild).getPriority()) >= 0) {
				 break;
			 }
			 swap(index, largerChild);
			 index = largerChild;
		 }
		heap.set(index, elem);
	}
}

