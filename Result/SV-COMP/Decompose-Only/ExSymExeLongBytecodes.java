class Main {

  //@ requires x > 0;
  //@ ensures \result == ((-\old(x) - 5L) <= 34565L);
  //@ also
  //@ requires x <= 0;
  //@ ensures \result == ((\old(x) - 5L) <= 34565L);
  //@ also
  //@ ensures \result == (((\old(x) > 0) ? -\old(x) : \old(x)) - 5L <= 34565L);
  public static boolean f(long x) {
    //@ assert true;
    x = x > 0 ? -x : x;
    //@ assert true;
    long y = 5;
    //@ assert true;
    Main inst = new Main();
    //@ assert true;
    return inst.test(x, y);
  }

  //@ requires x - z > 34565L;
  //@ ensures \result == false;
  //@ also
  //@ requires x - z <= 34565L;
  //@ ensures \result == true;
  //@ also
  //@ ensures \result == (x - z <= 34565L);
  public static boolean test(long x, long z) { // invokestatic
    //@ assert true;
    long a = x;
    //@ assert true;
    long b = z;
    //@ assert true;
    long c = 34565;

    //@ assert true;
    long negate = -z; // LNEG

    //@ assert true;
    long sum = a + b; // LADD
    //@ assert true;
    long sum2 = z + 9090909L; // LADD
    //@ assert true;
    long sum3 = 90908877L + z; // LADD

    //@ assert true;
    long diff = a - b; // LSUB
    //@ assert true;
    long diff2 = b - 19999999999L; // LSUB
    //@ assert true;
    long diff3 = 9999999999L - a; // LSUB

    //@ assert true;
    long mul = a * b; // LMUL
    //@ assert true;
    long mul2 = a * 19999999999L; // LMUL
    //@ assert true;
    long mul3 = 19999999999L * b; // LMUL

    //@ assert true;
    if (diff > c) {
      return false;
    } else System.out.println("branch diff <= c");
    //@ assert true;
    if (sum < z) System.out.println("branch sum < z");
    else
      System.out.println("branch sum >= z");
    return true;
  }
}