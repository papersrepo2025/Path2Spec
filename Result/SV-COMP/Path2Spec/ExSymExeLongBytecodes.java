class Main {
  //@ requires true;
  //@ ensures \result == true;
  //@ also
  //@ ensures (((x > 0 ? -x : x) - 5L) > 34565L) ==> (\result == false);
  //@ ensures (((x > 0 ? -x : x) - 5L) <= 34565L) ==> (\result == true);
  //@ also
  //@ requires (x <= 0 ==> x - 5L <= 34565L) && (x > 0 ==> -x - 5L <= 34565L);
  //@ ensures \result == true;
  //@ also
  //@ requires (x > 0 ==> x <= Long.MAX_VALUE - 4L) && (x <= 0 ==> x >= Long.MIN_VALUE + 5L);
  //@ ensures \result == true;
  public static boolean f(long x) {
    x = x > 0 ? -x : x;
    long y = 5;
    Main inst = new Main();
    return inst.test(x, y);
  }
  //@ requires System.out != null;
  //@ ensures \result == (x - z <= 34565L);
  //@ also
  //@ requires true;
  //@ ensures (x - z > 34565L) ==> \result == false;
  //@ ensures (x - z <= 34565L) ==> \result == true;
  //@ also
  //@ ensures (x - z > 34565L) ==> (\result == false);
  //@ ensures (x - z <= 34565L) ==> (\result == true);
  //@ also
  //@ requires x - z <= 34565L;
  //@ ensures \result == true;
  //@ also
  //@ requires (z >= 0 ==> x >= Long.MIN_VALUE + z) && (z <= 0 ==> x <= Long.MAX_VALUE + z);
  //@ requires x - z <= 34565L;
  //@ ensures \result == true;
  public static boolean test(long x, long z) { // invokestatic
    long a = x;
    long b = z;
    long c = 34565;

    long negate = -z; // LNEG

    long sum = a + b; // LADD
    long sum2 = z + 9090909L; // LADD
    long sum3 = 90908877L + z; // LADD

    long diff = a - b; // LSUB
    long diff2 = b - 19999999999L; // LSUB
    long diff3 = 9999999999L - a; // LSUB

    long mul = a * b; // LMUL
    long mul2 = a * 19999999999L; // LMUL
    long mul3 = 19999999999L * b; // LMUL

    if (diff > c) {
      return false;
    } else System.out.println("branch diff <= c");
    if (sum < z) System.out.println("branch sum < z");
    else
      System.out.println("branch sum >= z");
    return true;
  }
}
