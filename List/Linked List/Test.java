public class Test{

  public static void main(String [] args){

    List<Integer> list = new LinkedList<>();

    list.add(2,0);
    System.out.println(list);
    list.clear();

    list.add(2,0);
    list.add(3,1);
    list.add(5,2);
    System.out.println(list);

    list.add(1,0);
    list.add(4,3);
    list.add(6,5);
    System.out.println(list);
    list.clear();


  }
}
