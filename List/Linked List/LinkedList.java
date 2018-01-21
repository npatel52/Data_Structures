public class LinkedList<E> implements List<E>{
  private Node<E> head;
  private Node<E> tail;

  public LinkedList(){
    this.head = null;
    this.tail = null;
  }

  // add element to the tail
  public void append(E val){

     if(this.head == null){
        this.init(val);
      }else{
        this.tail.next = new Node(val);
        this.tail = this.tail.next;   //update tail
      }

   }

  // add element to the head
  public void prepend(E val){

     if(this.head == null){
        this.init(val);
     }else{
        this.head = new Node(val, this.head); //update head
      }

   }

   // initial construct of List
   public void init(E val){
     this.head = new Node(val);
     this.tail = this.head;
   }


   public int size(){
      int size = 0;

      Node<E> current = this.head;

      while(current != null){
        current = current.next;
        ++size;
      }

      return size;
   }

   /* Removes first occurrence of an element if it exist in the list
      if an element is not present in the list, the list remain unchanged.
   */
   public void remove(E target){

      if(this.isEmpty())                 // check if list is not
          return;

      if(this.head.val == target){        // if head needs to be removed
          this.removeHead();
          return;
      }

      Node<E> prevNode = this.head;           // node before current node
      Node<E> current = this.head.next;      // current position in List

      // traverse to find the item in the list
      while(current != null){

        if(current.val == target){          // if match found
            prevNode.next = current.next;   // prevNode points to the node after the current node

            if(isTail(current)){           // if it is a tail
                this.tail = prevNode;      // update tail
            }

            // freeing memory
            current = null;
            prevNode = null;

        }else{
          prevNode = current;             // precede the current
          current = current.next;         // current moves to the next node
        }

      }
   }

   public void add(E val, int index){

      if(index < 0 )
          throw new RuntimeException("Index out of bound");

      if(index == 0){                                // insert at head
        this.prepend(val);
        return;
      }

      Node<E> prevNode = this.head;
      Node<E> current = prevNode.next;

      while(true){                                   // Explicit condition is redundant

        --index ;

        if(index == 0){                              // if correcct location found

            if(isTail(prevNode)){                     // adding an element to the tail
              append(val);
            }else{
              prevNode.next = new Node(val, current);  // inserting between prevNode and current
            }
            return;

        }else if(!isTail(prevNode)){
            prevNode = current;
            current = current.next;
        }else{
            throw new RuntimeException("Index out of bound"); // Index is out of bound
        }

      }

   }

   public boolean isHead(Node<E> node){
     return this.head == node;
   }

   public boolean isTail(Node<E> node){
     return this.tail == node;
   }

   private void removeHead(){

     if(this.head == this.tail){         // if list has only one element
        this.head = null;
        this.tail = null;
      }else{
        this.head = this.head.next;     // head points to next element after head
      }

   }






   public boolean isEmpty(){
     return this.head == null;
   }

   public void clear(){

      while(this.head != null){
        Node<E> current = this.head;
        this.head = this.head.next;
        current = null;             // freeing memory, gc will collect objects that are not referenced by anyone
      }

      this.tail = null;
   }

/*
   // remove a specific item's first occurrence from a List
   void remove(E e);

  // add item at a given index
  void add(E e, int index);

  // removes all element in the list
   void clear();

  // returns the number of elements in the list
   int size();
   */

   public String toString(){
     if(this.head == null)
        return "[]";

     StringBuilder result = new StringBuilder("[ ");
     String arrow = " -> ";
     Node<E> current = this.head;

     while(current.next != null){
       result.append(current.val);
       result.append(arrow);
       current = current.next;
     }

     result.append(current.val);
     result.append(" ]");

     return result.toString();
   }
}
