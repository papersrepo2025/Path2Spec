class Main {
  //@ requires x >= 0 && System.out != null;
  //@ ensures \result == true;
  //@ also
  //@ ensures (x < 0) || Float.isNaN(x) ==> \result;
  //@ also
  //@ ensures \result;
  //@ also
  //@ requires true;
  //@ ensures \result;
  public static boolean f(float x) {
    Main inst = new Main();
    if (x >= 0)
      return inst.test(x);
    return true;
  }
  //@ requires System.out != null;
  //@ ensures x >= 0 ==> \result;
  //@ also
  //@ requires x >= 0;
  //@ ensures \result;
  //@ also
  //@ ensures \result <==> !(x < 0);
  //@ also
  //@ requires true;
  //@ ensures (x >= 0.0f || x != x) ==> \result;
  //@ ensures (x < 0.0f) ==> !\result;
  public boolean test(float x) {
    System.out.println("Testing FNEG");
    float y = -x;
    if (y > 0) {
      return false;
    } else
      System.out.println("branch -x <= 0");
    return true;
  }
}
