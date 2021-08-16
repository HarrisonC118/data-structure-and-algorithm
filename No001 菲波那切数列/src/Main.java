public class Main {
  //n0 1 2 3 4 5 6 7...
  // 0 1 1 2 3 5 8 13...
  //     1 = 0 + 1
  //       2 = 1 + 1
  //       3 = 1 + 2
  //          5 = 2 + 3

    // 求第n位的斐波那契数列
  public static void main(String[] args) {
    int n = 40; // 要求第n位的值
      TimeUtil.test(
        "递归方法",
              () -> System.out.println(fib(n)));
      TimeUtil.test(
              "循环方法",
              () -> System.out.println(fib2(n)));
  }
  public static int fib2(int n) {
      // 当n是2的时候，要进行1次加法运算 0   +   1   =   1
      //                          first second    num
      // 当n是3的时候，要进行2次加法运算 0   +   1   =   1  ,  1   +   1   =   1
      //                          first second    num   first second     num
      // 当n是4的时候，要进行3次加法运算 0+1 1+1 1+2
      // 当n是m的时候，要进行m-1次加法运算
      int num = 0; // 记录要求的值
      int first = 0; // 记录要处理的第一个值
      int second = 1; // 记录要处理的第二个值
      if (n <= 1) {
          return n;
      }
    for (int i = 0; i < n-1; i++) {
        num = first + second;
        first = second;
        second = num;
    }
    return num;
  }
  // 递归方法
  public static int fib(int n) {
    if(n<=1){
        return n;
    }
    return fib(n - 2) + fib(n - 1);
  }
}
