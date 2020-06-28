package sorters;

import java.util.Comparator;

import structures.SwapList;

public class HeapSorter<T> extends AbstractSorter<T> {

	public HeapSorter(SwapList<T> list, Comparator<T> comparator) {
		super(list, comparator);
	}

	@Override
	public SwapList<T> sort() {
		int nElems = list.size();
		for(int i = nElems/2-1; i >= 0; i--) { //heapify
			bubbleDown(i, nElems-1);
		}
		for(int i=nElems-1; i>=1; i--) {
			list.swap(0, i);
			bubbleDown(0, i-1);
		}
        return list;
	}
		 
	public void bubbleDown(int index1, int index2) {
		int largerChild;
		int temp = index1;
		int right = 2 * index1 + 2;		
		for(int left = 2 * index1 + 1; left <= index2; left = (temp*2)+1) {
			largerChild = left;
			if (right <= index2 && list.compare(left, right, comparator) < 0) {
				largerChild = right;
			}
			if (list.compare(temp, largerChild, comparator) >= 0) {
				break;
			}
			list.swap(temp, largerChild);
			temp = largerChild;
			right = (temp*2)+2;
		}
	}

}
