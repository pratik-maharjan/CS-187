package sets;

import java.util.Iterator;

/**
 * A LinkedList-based implementation of Set
 */

  /********************************************************
   * NOTE: Before you start, check the Set interface in
   * Set.java for detailed description of each method.
   *******************************************************/
  
  /********************************************************
   * NOTE: for this project you must use linked lists
   * implemented by yourself. You are NOT ALLOWED to use
   * Java arrays of any type, or any Collection-based class 
   * such as ArrayList, Vector etc. You will receive a 0
   * if you use any of them.
   *******************************************************/ 

  /********************************************************
   * NOTE: you are allowed to add new methods if necessary,
   * but do NOT add new files (as they will be ignored).
   *******************************************************/

public class LinkedSet<E> implements Set<E> {
  private LinkedNode<E> head = null;
  private int counter;

  // Constructors
  public LinkedSet() {
  }

  public LinkedSet(E e) {
    this.head = new LinkedNode<E>(e, null);
  }

  private LinkedSet(LinkedNode<E> head) {
    this.head = head;
    this.counter = 0;
  }

  @Override
  public int size() {
	  for(E a : this) {
		  counter++;
	  }
    return counter;
  }

  @Override
  public boolean isEmpty() {
	  if(this.head == null) {
		  return true;
	  }
    return false;
  }

  @Override
  public Iterator<E> iterator() {
    return new LinkedNodeIterator<E>(this.head);
  }

  @Override
  public boolean contains(Object o) {
	  for(E a : this) {
		  if(o.equals(a)) {
		  return true;
		 }
	  }
    return false;
  }

  @Override
  public boolean isSubset(Set<E> that) {
	  for(E a: this) {
		  if(!that.contains(a)) {
		  return false;
		  }
	  }
    return true;
  }

  @Override
  public boolean isSuperset(Set<E> that) {
	  for(E a: that) {
		  if(!this.contains(a)) {
		  return false;
		  }
	  }
    return true;
  }

  @Override
  public Set<E> adjoin(E e) {
	  if(this.contains(e)) {
		  return this;
	  }
	  LinkedSet<E> that = new LinkedSet<E> ();
	  LinkedNode<E> node = null;
	  for(E a : this) {
		  node = new LinkedNode(a, node);
	  }
	  node = new LinkedNode(e, node);
	  that.head = node;
    return that;
  }

  @Override
  public Set<E> union(Set<E> that) {
    // TODO (7)
	  
	  LinkedSet<E> inverse = new LinkedSet<E> ();
	  LinkedNode<E> node2 = null;
	  for(E a: that) {
		  if(!this.contains(a)) {
			  node2 = new LinkedNode(a, node2);
		  }
	  }
	  inverse.head = node2;
	  
	  LinkedSet<E> union = new LinkedSet<E> ();
	  LinkedNode<E> node = null;
	  for(E a : this) {
		  node = new LinkedNode(a, node);
	  }
	  for(E b : inverse) {
		  node = new LinkedNode(b, node);
	  }
	  union.head = node;
    return union;
  }

  @Override
  public Set<E> intersect(Set<E> that) {
    // TODO (8)
	  LinkedSet<E> intersect = new LinkedSet<E> ();
	  LinkedNode<E> node = null;
	  for(E a: that) {
		  if(this.contains(a)) {
			  node = new LinkedNode(a, node);
		  }
	  }
	  intersect.head = node;
    return intersect;
  }

  @Override
  public Set<E> subtract(Set<E> that) {
    // TODO (9)
	  LinkedSet<E> subtract = new LinkedSet<E> ();
	  LinkedNode<E> node = null;
	  for(E a: this) {
		  if(!that.contains(a)) {
			  node = new LinkedNode(a, node);
		  }
	  }
	  subtract.head = node;
    return subtract;
  }

  @Override
  public Set<E> remove(E e) {
    // TODO (10)
	  LinkedSet<E> remove = new LinkedSet<E> ();
	  LinkedNode<E> node = null;
	  for(E a : this) {
		  if(!e.equals(a)) {
			  node = new LinkedNode(a, node);
		 }
	  }
	  remove.head = node;
    return remove;
  }

  @Override
  @SuppressWarnings("unchecked")
  public boolean equals(Object o) {
    if (! (o instanceof Set)) {
      return false;
    }
    Set<E> that = (Set<E>)o;
    return this.isSubset(that) && that.isSubset(this);
  }

  @Override
    public int hashCode() {
    int result = 0;
    for (E e : this) {
      result += e.hashCode();
    }
    return result;
  }
}
