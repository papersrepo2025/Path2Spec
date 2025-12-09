class recursion2 {
  //@ requires true;
  //@ ensures \result == true;
  //@ also
  //@ ensures \result;
  public static boolean f() {
    return recursion_test(0) == 10;
  }
  //@ requires true;
  //@ ensures (depth <= 10 ==> \result == 10 - depth) && (depth >= 10 ==> \result == 0);
  //@ also
  //@ requires depth >= 10;
  //@ ensures \result == 0;
  //@ also
  //@ requires true;
  //@ ensures true;
  //@ also
  //@ requires 10 - Integer.MAX_VALUE <= depth && depth <= 10;
  //@ ensures depth < 10 ==> \result == 10 - depth;
  //@ ensures depth >= 10 ==> \result == 0;
  static int recursion_test(int depth) {
    if (depth < 10) return recursion_test(depth + 1) + 1;
    else return 0;
  }
}
