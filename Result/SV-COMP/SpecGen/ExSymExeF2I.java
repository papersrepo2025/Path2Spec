class Main {

  //@ ensures !(x >= 0.0f && x <= Integer.MAX_VALUE / 2) ==> \result == true;
  //@ ensures (x >= 0.0f && x <= Integer.MAX_VALUE / 2) ==> \result == (((int)(x + 1.0f)) > 0);
  public /*@ spec_public @*/ static boolean f(float x) {
    if (x >= 0.0f && x <= Integer.MAX_VALUE / 2) {
      Main inst = new Main();
      return inst.test(x);
    }
    return true;
  }

  //@ ensures \result == (((int)(x + 1.0f)) > 0);
  public /*@ spec_public @*/ boolean test(float x) {

    int res = (int) ++x;
    if (res > 0) System.out.println("x >0");
    else {
      return false;
    }
    return true;
  }
}