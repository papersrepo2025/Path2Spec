class recursion2 {
  /*@ 
    @ ensures \result;
    @ assignable \nothing;
    @*/
  public /*@ spec_public pure @*/ static boolean f() {
    return recursion_test(0) == 10;
  }

  /*@ 
    @ ensures (depth < 10 ==> \result == 10 - depth) && (depth >= 10 ==> \result == 0);
    @ assignable \nothing;
    @*/
  static /*@ spec_public pure @*/ int recursion_test(int depth) {
    if (depth < 10) return recursion_test(depth + 1) + 1;
    else return 0;
  }
}