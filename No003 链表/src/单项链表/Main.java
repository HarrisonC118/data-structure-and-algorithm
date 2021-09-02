package 单项链表;

public class Main {
  public static void main(String[] args) {
    MyList<Integer> list = new MyLinkedList<>();
    list.add(20);
    list.add(0, 10);
    list.add(30);
    list.add(list.size(), 40);
    list.remove(1);
    System.out.println(list);
  }
}
