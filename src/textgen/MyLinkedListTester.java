/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

private static final int LONG_LIST_LENGTH = 10000; 

MyLinkedList<String> shortList;
MyLinkedList<Integer> emptyList;
MyLinkedList<Integer> longerList;
MyLinkedList<Integer> list1;

/**
 * @throws java.lang.Exception
 */
@Before
public void setUp() throws Exception {
    // Feel free to use these lists, or add your own
    shortList = new MyLinkedList<String>();
    shortList.add("A");
    shortList.add("B");
    emptyList = new MyLinkedList<Integer>();
    longerList = new MyLinkedList<Integer>();
    for (int i = 0; i < LONG_LIST_LENGTH; i++)
    {
        longerList.add(i);
    }
    list1 = new MyLinkedList<Integer>();
    list1.add(65);
    list1.add(21);
    list1.add(42);
}

/** Test if the get method is working correctly.
 */
/*You should not need to add much to this method.
 * We provide it as an example of a thorough test. */
@Test
public void testGet()
{
    //test empty list, get should throw an exception
    try {
        emptyList.get(0);
        fail("Check out of bounds");
    }
    catch (IndexOutOfBoundsException e) {

    }

    // test short list, first contents, then out of bounds
    assertEquals("Check first", "A", shortList.get(0));
    assertEquals("Check second", "B", shortList.get(1));

    try {
        shortList.get(-1);
        fail("Check out of bounds");
    }
    catch (IndexOutOfBoundsException e) {
    }
    
    try {
        shortList.get(2);
        fail("Check out of bounds");
    }
    catch (IndexOutOfBoundsException e) {
    }
    
    // test longer list contents
    for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
        assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
    }

    // test off the end of the longer array
    try {
        longerList.get(-1);
        fail("Check out of bounds");
    }
    catch (IndexOutOfBoundsException e) {
    }
    
    try {
        longerList.get(LONG_LIST_LENGTH);
        fail("Check out of bounds");
    }
    catch (IndexOutOfBoundsException e) {
    }

}

/** Test removing an element from the list.
 * We've included the example from the concept challenge.
 * You will want to add more tests.  */
@Test
public void testRemove()
{
    int a = list1.remove(0);
    assertEquals("Remove: check a is correct ", 65, a);
    assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
    assertEquals("Remove: check size is correct ", 2, list1.size());
    list1.add(10);
    assertEquals("Remove: check element 2 is correct ", (Integer)10, list1.get(2));
    assertEquals("Remove: check size is correct ", 3, list1.size());
    list1.add(2, 15);
    assertEquals("Remove: check add at indx", 4, list1.size());
    assertEquals("Remove: check element 2 is correct ", (Integer)15, list1.get(2));
    
    try {
        emptyList.remove(0);
        fail("Check out of bounds");
    }
    catch (IndexOutOfBoundsException e) {

    }    
    
    emptyList.add(10);    
    assertEquals("Remove: check element 0 is correct ", (Integer)10, emptyList.get(0));
    assertEquals("Remove: check size is correct ", 1, emptyList.size());
    int b = emptyList.remove(0);
    assertEquals("Remove: check a is correct ", 10, b);
    assertEquals("Remove: check size is correct ", 0, emptyList.size());
    emptyList.add(20);    
    assertEquals("Remove: check element 0 is correct ", (Integer)20, emptyList.get(0));
    assertEquals("Remove: check size is correct ", 1, emptyList.size());    

    MyLinkedList<Integer> lst = new MyLinkedList<Integer>();
    lst.add(0, 1);
    lst.remove(0);
    lst.add(0, 1);    
}

/** Test adding an element into the end of the list, specifically
 *  public boolean add(E element)
 * */
@Test
public void testAddEnd()
{
    longerList.add(LONG_LIST_LENGTH, 666);
    assertEquals("Check end", (Integer)666, longerList.get(LONG_LIST_LENGTH));
    assertEquals("Check Size", (LONG_LIST_LENGTH + 1), longerList.size());    
}
/** Test the size of the list */
@Test
public void testSize()
{
    assertEquals("Check Size", 0, emptyList.size());
    assertEquals("Check Size", 2, shortList.size());
    assertEquals("Check Size", LONG_LIST_LENGTH, longerList.size());
    
    for (int i = 0; i < LONG_LIST_LENGTH; i++)
    {
        emptyList.add(i);
    }
    assertEquals("Check Size after add()", LONG_LIST_LENGTH, emptyList.size());
    
    for (int i = 0; i < LONG_LIST_LENGTH; i++)
    {
        emptyList.remove(0);
    }
    assertEquals("Check Size after remove()", 0, emptyList.size());
}

/** Test adding an element into the list at a specified index,
 * specifically:
 * public void add(int index, E element)
 * */
@Test
public void testAddAtIndex()
{
    try {
        emptyList.add(1, 10);
        fail("Check out of bounds");
    }
    catch (IndexOutOfBoundsException e) {
    }

    emptyList.add(0, 10);
    assertEquals("Check first", (Integer)10, emptyList.get(0));
    assertEquals("Check Size", 1, emptyList.size());

    longerList.add((LONG_LIST_LENGTH/2), 123);
    assertEquals("Check middle", (Integer)123, longerList.get(LONG_LIST_LENGTH/2));
    assertEquals("Check Size", (LONG_LIST_LENGTH + 1), longerList.size());    
    
    longerList.add(LONG_LIST_LENGTH, 666);
    assertEquals("Check end", (Integer)666, longerList.get(LONG_LIST_LENGTH));
    assertEquals("Check Size", (LONG_LIST_LENGTH + 2), longerList.size());   
}

/** Test setting an element in the list */
@Test
public void testSet()
{
    try {
        emptyList.set(0, null);
        fail("Check for null object");
    }
    catch (NullPointerException e) {
    }

    try {
        emptyList.set(0, 10);
        fail("Check for index out of bounds");
    }
    catch (IndexOutOfBoundsException e) {
    }    
    
    int number = longerList.get(0);
    assertEquals("Check first", (Integer)number, longerList.set(0, (number*2)));
    assertEquals("Check first", (Integer)(number*2), longerList.get(0));    
    
    number = longerList.get(LONG_LIST_LENGTH/2);
    assertEquals("Check middle", (Integer)number, longerList.set(LONG_LIST_LENGTH/2, (number*2)));
    assertEquals("Check middle", (Integer)(number*2), longerList.get(LONG_LIST_LENGTH/2));
    
    number = longerList.get(LONG_LIST_LENGTH-1);
    assertEquals("Check last", (Integer)number, longerList.set(LONG_LIST_LENGTH-1, (number)));
    assertEquals("Check last", (Integer)(number), longerList.get(LONG_LIST_LENGTH-1));
}

}
