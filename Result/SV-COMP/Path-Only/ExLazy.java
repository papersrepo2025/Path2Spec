class Main {

  //@ requires x == null;
  public static void test(//@ nullable; Main x, int y) {
    if (x == null && y == 0) {
      System.out.println(1);
    } else if (x != null) {
      System.out.println(2);
      assert false;
    }
  }

  public static void f(int n1, int n2) {
    if (n1 == 0) test(null, 0);
    else test(null, n2);
  }
}