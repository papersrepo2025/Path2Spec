class Main {
  /*@ spec_public @*/ static int[] a;

  //@ ensures \result == true;
  //@ ensures a != null && a.length == 1 && a[0] == -3 && \fresh(a);
  public static boolean f() {
    a = new int[1];
    int x = -3;

    Main inst = new Main();

    return inst.test(x);
  }

  //@ ensures a != null && a.length >= 1 && a[0] == x;
  //@ ensures \result <==> x < 0;
  public boolean test(int x) {
    a[0] = x;
    if (a[0] >= 0) {
      return false;
    } else System.out.println("branch2 <0");
    return true;
  }
}