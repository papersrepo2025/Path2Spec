class Main {

  static int field;
  //@ requires a != null;
  //@ requires a.length >= 2;
  //@ requires b < Integer.MAX_VALUE;
  //@ ensures true;
  //@ also
  //@ requires a != null && a.length >= 2;
  //@ also
  //@ requires a != null;
  //@ requires a.length >= 2;
  //@ also
  //@ requires a != null && a.length >= 2;
  //@ requires a[0] == false && a[1] == true;
  //@ ensures a[0] == \old(a[0]) && a[1] == \old(a[1]);
  //@ also
  //@ requires a != null && a.length >= 2;
  //@ ensures true;
  //@ also
  //@ requires a == null;
  public void testa(int b, boolean[] a) {
    b++;
    if (a[0]) System.out.println("array0");
    if (a[1]) System.out.println("array1");
  }
  //@ requires !(xm < ym && xm > ym);
  //@ ensures true;
  //@ also
  //@ ensures true;
  public void test5(double xm, double ym) {
    if (xm < ym && xm > ym) {
      System.out.println("unreachable");
      assert (false);
    } else assert (true);
  }
  //@ ensures true;
  public void test3(double x, double y) {
    if (Math.sin(x) + Math.cos(y) == 1) System.out.println("eq");
    else System.out.println("neq");
  }
  //@ requires Integer.MIN_VALUE <= x + y && x + y <= Integer.MAX_VALUE;
  //@ requires Integer.MIN_VALUE <= y - x && y - x <= Integer.MAX_VALUE;
  //@ ensures \result == ((x > 0 && y > 0) ? y + x : y - x);
  //@ also
  //@ requires x > 0 && y > 0;
  //@ ensures \result == x + y;
  //@ also
  //@ requires (x > 0 && y > 0) ==> (Integer.MIN_VALUE <= x + y && x + y <= Integer.MAX_VALUE);
  //@ requires !(x > 0 && y > 0) ==> (Integer.MIN_VALUE <= y - x && y - x <= Integer.MAX_VALUE);
  //@ ensures (x > 0 && y > 0) ==> \result == x + y;
  //@ ensures !(x > 0 && y > 0) ==> \result == y - x;
  //@ also
  //@ requires (x > 0 && y > 0) ==> (Integer.MIN_VALUE <= x + y && x + y <= Integer.MAX_VALUE);
  //@ requires !(x > 0 && y > 0) ==> (Integer.MIN_VALUE <= y - x && y - x <= Integer.MAX_VALUE);
  //@ ensures (x > 0 && y > 0) ==> \result == y + x;
  //@ ensures !(x > 0 && y > 0) ==> \result == y - x;
  //@ also
  //@ requires Integer.MIN_VALUE <= (\bigint)x + (\bigint)y && (\bigint)x + (\bigint)y <= Integer.MAX_VALUE;
  //@ requires Integer.MIN_VALUE <= (\bigint)y - (\bigint)x && (\bigint)y - (\bigint)x <= Integer.MAX_VALUE;
  //@ ensures (x > 0 && y > 0) ==> \result == y + x;
  //@ ensures !(x > 0 && y > 0) ==> \result == y - x;
  //@ also
  //@ requires Integer.MIN_VALUE <= y + x && y + x <= Integer.MAX_VALUE;
  //@ requires Integer.MIN_VALUE <= y - x && y - x <= Integer.MAX_VALUE;
  //@ ensures \result == ((x > 0 && y > 0) ? y + x : y - x);
  //@ also
  //@ requires Integer.MIN_VALUE <= y + x && y + x <= Integer.MAX_VALUE;
  //@ requires Integer.MIN_VALUE <= y - x && y - x <= Integer.MAX_VALUE;
  //@ ensures (x > 0 && y > 0) ==> \result == y + x;
  //@ ensures !(x > 0 && y > 0) ==> \result == y - x;
  //@ also
  //@ requires Integer.MIN_VALUE <= x + y && x + y <= Integer.MAX_VALUE;
  //@ requires Integer.MIN_VALUE <= y - x && y - x <= Integer.MAX_VALUE;
  //@ ensures (x > 0 && y > 0) ==> \result == y + x;
  //@ ensures !(x > 0 && y > 0) ==> \result == y - x;
  public static int test4(int x, int y) {
    if (x > 0 && y > 0) {
      return y + x;
    } else {
      return y - x;
    }
  }
  //@ ensures true;
  //@ also
  //@ requires x < 1200 && b == false;
  //@ also
  //@ requires x == 1200 && b == true;
  //@ ensures x == 1200 && b == true;
  //@ also
  //@ ensures x == \old(x);
  //@ ensures b == \old(b);
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
  //@ requires Integer.MIN_VALUE <= x + z && x + z <= Integer.MAX_VALUE;
  //@ requires Integer.MIN_VALUE <= (x + z) + 6 && (x + z) + 6 <= Integer.MAX_VALUE;
  //@ requires x <= 0;
  //@ ensures true;
  //@ also
  //@ requires x <= 0;
  //@ also
  //@ requires x <= 0;
  //@ requires Integer.MIN_VALUE <= x + z && x + z <= Integer.MAX_VALUE;
  //@ also
  //@ requires x <= 0;
  //@ requires Integer.MIN_VALUE <= (\bigint)x + (\bigint)z && (\bigint)x + (\bigint)z <= Integer.MAX_VALUE;
  //@ requires Integer.MIN_VALUE <= ((\bigint)x + (\bigint)z) + 6 && (((\bigint)x + (\bigint)z) + 6) <= Integer.MAX_VALUE;
  //@ ensures true;
  //@ also
  //@ requires x <= 0 && x + z + 6 <= 0;
  //@ requires Integer.MIN_VALUE <= x + z && x + z <= Integer.MAX_VALUE;
  //@ requires Integer.MIN_VALUE <= x + z + 6 && x + z + 6 <= Integer.MAX_VALUE;
  //@ also
  //@ requires x <= 0;
  //@ requires z >= 0;
  //@ also
  //@ requires Integer.MIN_VALUE - z <= x && x <= Integer.MAX_VALUE - z && x <= 0;
  //@ also
  //@ requires Integer.MIN_VALUE <= x + z && x + z <= Integer.MAX_VALUE;
  //@ requires x <= 0;
  //@ also
  //@ requires x <= 0;
  //@ requires Integer.MIN_VALUE - z <= x && x <= Integer.MAX_VALUE - z;
  //@ requires Integer.MIN_VALUE <= x + z + 6 && x + z + 6 <= Integer.MAX_VALUE;
  //@ ensures true;
  //@ also
  //@ requires Integer.MIN_VALUE <= x + z && x + z <= Integer.MAX_VALUE;
  //@ requires x <= 0;
  //@ ensures true;
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
  //@ requires z < Integer.MAX_VALUE;
  //@ ensures true;
  //@ also
  //@ ensures true;
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
