class recursion2 {

  /*@ public normal_behavior
    @   ensures \result == true;
    @*/
  public static /*@ pure @*/ boolean f() {
    return recursion_test(0) == 10;
  }

  /*@ public normal_behavior
    @   requires depth < 10;
    @   ensures \result == 10 - \old(depth);
    @ also
    @ public normal_behavior
    @   requires depth >= 10;
    @   ensures \result == 0;
    @*/
  static /*@ pure @*/ int recursion_test(int depth) {
    if (depth < 10) return recursion_test(depth + 1) + 1;
    else return 0;
  }
}