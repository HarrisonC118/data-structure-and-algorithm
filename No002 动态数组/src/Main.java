public class Main {
  public static void main(String[] args) {
      MyArrayList<Integer> myArrayList = new MyArrayList<>();
      myArrayList.add(123);
      myArrayList.add(456);
      myArrayList.add(789);
      myArrayList.add(111);
      myArrayList.add(222);
      myArrayList.add(6,99999);
      System.out.println(myArrayList);
  }
}
