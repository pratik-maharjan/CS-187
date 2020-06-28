package structures;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class PublicListInterfaceTest {

	private ListInterface<String> list;

	@Before
	public void setup(){
          list = new RecursiveList<String>();
	}
	
	@Test
	public void insertFirstRemoveFirstGetFirst() {
		list.insertFirst("Maharjan");
		list.insertFirst("Nothing");
		list.insertFirst("Pratik");
		assertEquals(3, list.size());
		list.removeFirst();
		assertEquals("Nothing", list.getFirst());
		list.insertFirst("Bahadur");
		list.insertFirst("Dan");
		assertEquals(4, list.size());
		assertEquals("Dan", list.getFirst());
		list.removeFirst();
		assertEquals("Bahadur", list.getFirst());
	}
	
	@Test
	public void insertLastRemoveLastGetLast() {
		list.insertLast("Pratik");
		list.insertLast("Nothing");
		list.insertLast("Maharjan");
		assertEquals(3, list.size());
		list.removeLast();
		assertEquals("Nothing", list.getLast());
		assertEquals(2, list.size());
		list.insertLast("Dangol");
		assertEquals("Dangol", list.getLast());
		assertEquals(3, list.size());
		list.insertLast("Jamuna");
		assertEquals(4, list.size());
		assertEquals("Jamuna", list.getLast());
	}
	
	@Test
	public void insertAtRemoveAtGetAt() {
		list.insertAt(0,"Maharjan");
		assertEquals("Maharjan", list.getLast());
		list.insertAt(0,"Pratik");
		assertEquals("Maharjan", list.getLast());
		list.insertAt(1,"Nothing");
		assertEquals(3, list.size());
		assertEquals("Maharjan", list.getLast());
		assertEquals("Pratik", list.getFirst());
		list.removeLast();
		assertEquals("Nothing", list.getLast());
		assertEquals("Pratik", list.getFirst());
		list.removeLast();
		assertEquals("Pratik", list.getLast());
		list.insertAt(1,"Maharjan");
		list.insertAt(1,"Nothing");
		assertEquals("Maharjan", list.getLast());
		assertEquals("Pratik", list.getFirst());
		assertEquals(3, list.size());
		list.removeAt(2);
		assertEquals(2, list.size());
		assertEquals("Nothing", list.getLast());
		list.removeAt(0);
		assertEquals("Nothing", list.getLast());
		assertEquals("Nothing", list.getFirst());
//		list.removeFirst();
//		assertEquals("Nothing", list.getFirst());
//		list.insertFirst("Bahadur");
//		list.insertFirst("Dan");
//		assertEquals(4, list.size());
//		assertEquals("Dan", list.getFirst());
//		list.removeFirst();
//		assertEquals("Bahadur", list.getFirst());
	}
	
	@Test
	public void removeAt() {
		list.insertLast("Alex");
		list.insertLast("Ben");
		list.insertLast("Dillon");
		list.insertLast("Keito");
		list.insertLast("Graham");
		list.insertLast("Jared");
		list.insertLast("Kanram");
		list.insertLast("Lincoln");
		list.insertLast("Luke");
		list.insertLast("Mike");
		list.insertLast("Peter");
		list.insertLast("Philson");
		list.insertLast("Pretty");
		list.insertLast("Sam");
		list.insertLast("Spencer");
		list.insertLast("Sterling");
		assertEquals(16, list.size());
		assertEquals("Luke", list.get(8));
		assertEquals("Spencer", list.get(14));
		assertEquals("Sterling", list.get(15));
		list.removeAt(8);
		assertEquals(15, list.size());
		list.removeAt(14);
		assertEquals(14, list.size());
		assertEquals("Spencer", list.getLast());
		assertEquals("Alex", list.get(0));
		assertEquals("Ben", list.get(1));
	}
	
	@Test
	public void insertLastRemoveLastSizeAndIsEmpty() {
		list.insertLast("Pratik");
		list.removeLast();
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
	}
	
	@Test
	public void insertAtIsEmptySizeAndGetAt() {
		list.insertAt(0, "Pratik");
		assertFalse(list.isEmpty());
		assertEquals(1, list.size());
		assertEquals("Pratik", list.get(0));
	}
	
	@Test
	public void insertsRemoveAndIndexOf() {
		list.insertFirst("Pratik");
		list.insertLast("Maharjan");
		list.insertAt(1, "Nothing");
		assertEquals(3, list.size());
		assertEquals("Pratik", list.getFirst());
		assertEquals("Maharjan", list.getLast());
		assertEquals(0, list.indexOf("Pratik"));
		assertEquals(2, list.indexOf("Maharjan"));
		assertEquals(1, list.indexOf("Nothing"));
		assertTrue(list.remove("Pratik"));
		assertEquals(2, list.size());
		assertEquals("Nothing", list.getFirst());
		list.insertFirst("Pratik");
		assertTrue(list.remove("Maharjan"));
		assertFalse(list.remove("Maharjan"));
		assertEquals(-1, list.indexOf("Maharjan"));
		list.remove("Pratik");
		list.remove("Nothing");
		assertEquals(0, list.size());
		//listassertEquals("Nothing", list.getLast());
	}

	@Test (timeout = 500)
	public void testInsertFirstIsEmptySizeAndGetFirst1() {
		assertTrue("Newly constructed list should be empty.", list.isEmpty());
		assertEquals("Newly constructed list should be size 0.", 0, list.size());
		assertEquals("Insert First should return instance of self", list, list.insertFirst("hello"));
		assertFalse("List should now have elements.", list.isEmpty());
		assertEquals("List should now have 1 element.", 1, list.size());
		assertEquals("First element should .equals \"hello\".", "hello", list.getFirst());
		list.insertFirst("world");
		assertEquals(2, list.size());
		list.insertFirst("foo");
		assertEquals(3, list.size());
		assertEquals("First element should .equals \"foo\".", "foo", list.getFirst());
	}
}
