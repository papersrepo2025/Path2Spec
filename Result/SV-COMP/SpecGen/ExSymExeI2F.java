class Main {

  /*@ public normal_behavior
    @   assignable \everything;
    @   ensures \result;
    @*/
  public static boolean f(int x) {
    if (x != 3 && x != 0)
      return true;

    Main inst = new Main();
    return inst.test(x);
  }

  /*@ public normal_behavior
    @   assignable \everything;
    @   ensures \result <==> (x == Integer.MAX_VALUE ? false : x + 1 > 0);
    @*/
  public boolean test(int x) {
    float res = (float) ++x;
    if (res > 0) System.out.println("x >0");
    else {
      return false;
    }
    return true;
  }
}