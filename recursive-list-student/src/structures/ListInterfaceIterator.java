package structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListInterfaceIterator<T> implements Iterator<T> {
	private LLNode<T> node;
	  
	  // Constructors
	  public ListInterfaceIterator(LLNode<T> head) {
		  node = head;
	  }

	  @Override
	  public boolean hasNext() {
		  if(node != null) {
			  return true;
		  }
	    return false;
	  }

	  @Override
	  public T next() {
		  if(!hasNext()) {
			  throw new NoSuchElementException();
		  }
		  T returnValue = node.data;
		  node = node.next;
		  return returnValue;
	  }

	  @Override
	  public void remove() {
	    // Nothing to change for this method
	    throw new UnsupportedOperationException();
	  }

}
