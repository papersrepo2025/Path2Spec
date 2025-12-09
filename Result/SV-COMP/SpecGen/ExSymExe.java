class Main {

  /*@ spec_public @*/ static int field;

  //@ requires a != null && a.length >= 2;
  //@ ensures field == \old(field);
  public void testa(int b, boolean[] a) {
    b++;
    if (a[0]) System.out.println("array0");
    if (a[1]) System.out.println("array1");
  }

  //@ ensures field == \old(field);
  public void test5(double xm, double ym) {
    if (xm < ym && xm > ym) {
      System.out.println("unreachable");
      assert (false);
    } else assert (true);
  }

  //@ ensures field == \old(field);
  public void test3(double x, double y) {
    if (Math.sin(x) + Math.cos(y) == 1) System.out.println("eq");
    else System.out.println("neq");
  }

 
  //@ ensures field == \old(field);
  public static int test4(int x, int y) {
    if (x > 0 && y > 0) {
      return y + x;
    } else {
      return y - x;
    }
  }

  //@ ensures field == \old(field);
  public static void test1(int x, boolean b) {
    System.out.println("test1");
    Integer z = new Integer((int) x);
    if (z <= 1200) System.out.println("le 1200");
    if (z >= 1200) System.out.println("ge 1200");
    if (b) {
      System.out.println("b true");
    } else {
      System.out.println("b false");
    }
  }

  //@ ensures field == \old(field);
  public void test(int x, int z) {
    // x = x >>> 1;
    x = x + z;
    if (x > z) {
      // if (z > x)
      System.out.println("unreachable");
      assert false;
    }
    if (x + 6 > 0) System.out.println("br3");
    else System.out.println("br2");
  }

  //@ ensures field == \old(field);
  public void test2(int x, int z) {
    System.out.println("in test2 " + x + " " + z);
    x = z++;
    // z=5;
    if (z > 0) {
      System.out.println("branch2 FOO1");
    } else System.out.println("branch2 FOO2");
    if (x > 0) System.out.println("branch2 BOO1");
    else System.out.println("branch2 BOO2");
  }
}
