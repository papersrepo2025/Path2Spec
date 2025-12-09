class Main {

  //@ ensures \result <==> x != Long.MAX_VALUE - 1L;
  /*@ spec_public @*/ public static boolean f(long x) {
    Main inst = new Main();
    return inst.test(x, 5);
  }

  //@ ensures \result <==> x != Long.MAX_VALUE - 1L;
  /*@ spec_public @*/ public boolean test(long x, long y) {

    long res = x;
    if (res + 1 > res + 2) {
      return false;
    } else
      System.out.println("x <=0");
    return true;
  }
}