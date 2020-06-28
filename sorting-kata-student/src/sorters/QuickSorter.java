package sorters;

import java.util.Comparator;

import structures.SwapList;

public class QuickSorter<T> extends AbstractSorter<T> {

	public QuickSorter(SwapList<T> list, Comparator<T> comparator) {
		super(list, comparator);
	}

	@Override
	public SwapList<T> sort() {
		return quickSort();
	}
	
	public SwapList<T> quickSort() {
		recQuickSort(0, list.size()-1);
		return list;
	}

	protected void recQuickSort(int low, int high) {
		if(low < high) {
			int p = partition(low, high);
			recQuickSort(low, p-1);
			recQuickSort(p+1, high);
		}
	}

	public int partition(int low, int high) {
		int pivot = (high+low)/2;
		list.swap(high, pivot);
		int storeIndex = low;
		int j;
		for(j=low; j<=high-1; j++) {
			if(list.compare(j, high, comparator) <= 0)  {
				list.swap(j, storeIndex);
				storeIndex++;
			}
		}
		list.swap(storeIndex, high);
		return storeIndex;
	}
}
  