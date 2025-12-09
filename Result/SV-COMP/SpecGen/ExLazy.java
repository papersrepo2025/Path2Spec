class Main {

  //@ ensures x == \old(x) && y == \old(y);
  /*@ spec_public @*/ public static void test(Main x, int y) {
    if (x == null && y == 0) {
      System.out.println(1);
    } else if (x != null) {
      System.out.println(2);
      assert false;
    }
  }

  //@ ensures n1 == \old(n1) && n2 == \old(n2);
  /*@ spec_public @*/ public static void f(int n1, int n2) {
    if (n1 == 0) test(null, 0);
    else test(null, n2);
  }
}