package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
LLNode<E> head;
LLNode<E> tail;
int size;

/** Create a new empty LinkedList */
public MyLinkedList() {
    head = new LLNode<E>(null);
    tail = new LLNode<E>(null);
    head.next = tail;
    tail.prev = head;
    size = 0;
}

/**
 * Appends an element to the end of the list
 * @param element The element to add
 */
public boolean add(E element) 
{
    isElementIsNotNull(element);
    
    LLNode<E> newNode = new LLNode<E>(element);
    if(0 == this.size()) {
        head.next = newNode;
        newNode.prev = head;
    } else {
        newNode.prev = tail.prev;
        tail.prev.next = newNode;
    }
    
    newNode.next = tail;
    tail.prev = newNode;    
    ++size;
    return true;
}

/** Get the element at position index 
 * @throws IndexOutOfBoundsException if the index is out of bounds. */
public E get(int index) 
{   
    if(this.isIndexValidToRead(index)) {
        return this.getNode(index).data;
    } else {
        throw new IndexOutOfBoundsException("Requested index out of bounds!");
    }
}

/**
 * Add an element to the list at the specified index
 * @param The index where the element should be added
 * @param element The element to add
 */
public void add(int index, E element) 
{
    isElementIsNotNull(element);
    
    if(this.isIndexValidToWrite(index)) {
        LLNode<E> tmpNode = this.getNode(index);
        LLNode<E> newNode = new LLNode<E>(element);
        newNode.next = tmpNode;
        newNode.prev = tmpNode.prev;
        tmpNode.prev.next = newNode;
        tmpNode.prev = newNode;
        ++size;
    } else {
        throw new IndexOutOfBoundsException("Requested index out of bounds!");
    }
}

/** Return the size of the list */
public int size() 
{
    return this.size;
}

/** Remove a node at the specified index and return its data element.
 * @param index The index of the element to remove
 * @return The data element removed
 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
 * 
 */
public E remove(int index) 
{
    if(this.isIndexValidToRead(index)) {
        LLNode<E> tmpNode = this.getNode(index);
        tmpNode.prev.next = tmpNode.next;
        tmpNode.next.prev = tmpNode.prev;
        tmpNode.next = null;
        tmpNode.prev = null;
        --size;
        return tmpNode.data;
    } else {        
        throw new IndexOutOfBoundsException("Requested index out of bounds!");
    }
}

/**
 * Set an index position in the list to a new element
 * @param index The index of the element to change
 * @param element The new element
 * @return The element that was replaced
 * @throws IndexOutOfBoundsException if the index is out of bounds.
 */
public E set(int index, E element) 
{
    isElementIsNotNull(element);
    E oldElement = this.get(index);
    this.getNode(index).data = element;
    return oldElement;
}   

private boolean isIndexValidToRead(int index) {
    return !(index < 0 || index >= this.size());
}

private boolean isIndexValidToWrite(int index) {
    return !(index < 0 || index > this.size());
}

private LLNode<E> getNode(int index) {
    int currIdx = 0;
    LLNode<E> runner = this.head.next;
    while(currIdx < index) {
        runner = runner.next;
        ++currIdx;
    }
    return runner;
}

private void isElementIsNotNull(Object obj) {
    if(obj == null) {
        throw new NullPointerException();
    }    
}

} // class MyLinkedList

class LLNode<E> 
{
LLNode<E> prev;
LLNode<E> next;
E data;

// TODO: Add any other methods you think are useful here
// E.g. you might want to add another constructor

public LLNode(E e) 
{
    this.data = e;
    this.prev = null;
    this.next = null;
}

}
