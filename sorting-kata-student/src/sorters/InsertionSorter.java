package sorters;

import java.util.Comparator;

import structures.SwapList;

public class InsertionSorter<T> extends AbstractSorter<T> {

	public InsertionSorter(SwapList<T> list, Comparator<T> comparator) {
		super(list, comparator);
	}

	@Override
	public SwapList<T> sort() {
		int j;
		 for(int out = 1; out<list.size(); out++){
			 for(j = out; j>0 && list.compare(j-1, j, comparator) > 0; j--) {
				 list.swap(j-1, j);
			 }
		 }
		 return list;
	}
}