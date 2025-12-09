class Main {
  //@ requires true;
  //@ ensures x == 9223372036854775806L ==> \result == false;
  //@ also
  //@ requires x != 9223372036854775806L;
  //@ ensures \result == true;
  //@ also
  //@ ensures (x == 9223372036854775806L) ==> \result == false;
  //@ ensures (x != 9223372036854775806L) ==> \result == true;
  public static boolean f(long x) {
    Main inst = new Main();
    return inst.test(x, 5);
  }
  //@ requires System.out != null;
  //@ ensures x == 9223372036854775806L ==> \result == false;
  //@ also
  //@ requires x != 9223372036854775806L;
  //@ ensures \result == true;
  //@ also
  //@ requires true;
  //@ ensures (x == 9223372036854775806L) ==> \result == false;
  //@ ensures (x != 9223372036854775806L) ==> \result == true;
  public boolean test(long x, long y) {

    long res = x;
    if (res + 1 > res + 2) {
      return false;
    } else
      System.out.println("x <=0");
    return true;
  }
}
