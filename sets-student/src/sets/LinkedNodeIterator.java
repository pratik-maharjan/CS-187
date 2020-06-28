package sets;

import java.util.Iterator;
import java.util.NoSuchElementException;

class LinkedNodeIterator<E> implements Iterator<E> {
    // TODO (1) define data variables
	private LinkedNode<E> node;
  
  // Constructors
  public LinkedNodeIterator(LinkedNode<E> head) {
      // TODO (2) choose appropriate parameters and do the initialization
	  node = head;
  }

  @Override
  public boolean hasNext() {
    // TODO (3)
	  if(node != null) {
		  return true;
	  }
    return false;
  }

  @Override
  public E next() {
    // TODO (4)
	  if(!hasNext()) {
		  throw new NoSuchElementException();
	  }
	  E returnValue = node.getData();
	  node = node.getNext();
	  return returnValue;
  }

  @Override
  public void remove() {
    // Nothing to change for this method
    throw new UnsupportedOperationException();
  }
}
