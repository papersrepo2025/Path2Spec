class Main {

  //@ requires x != 3 && x != 0; //@ ensures \result == true;
  //@ also //@ requires x == 3 || x == 0; //@ ensures \result == true;
  public static boolean f(int x) {
    if (x != 3 && x != 0)
      return true;

    Main inst = new Main();
    return inst.test(x);
  }

  //@ requires x >= 0 && x != Integer.MAX_VALUE; //@ ensures \result == (\old(x) >= 0 && \old(x) != Integer.MAX_VALUE);
  //@ also //@ requires x < 0 || x == Integer.MAX_VALUE; //@ ensures \result == (\old(x) >= 0 && \old(x) != Integer.MAX_VALUE);
  public boolean test(int x) {
    float res = (float) ++x;
    if (res > 0) System.out.println("x >0");
    else {
      return false;
    }
    return true;
  }
}