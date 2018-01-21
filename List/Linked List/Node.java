public class Node<E>{

  E val;
  Node<E> next;

  public Node(E val){
      this.val = val;
      this.next = null;
  }

  // overloaded constructor
  public Node(E val, Node next){
      this.val = val;
      this.next = next;
  }

}
