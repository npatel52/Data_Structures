interface List<E> {         // Singly linked list node class

// add element to the tail
public void append(E e);

// add element to the head
public void prepend(E e);

// removes all element in the list
 public void clear();

 // returns the number of elements in the list
public int size();

 // remove a specific item's first occurrence from a List
 public void remove(E val);

// add item at a given index
public void add(E val, int index);

}
