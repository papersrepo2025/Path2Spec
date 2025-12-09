class NullPointerException1 {
  //@ requires true;
  //@ ensures \result == true;
  //@ signals (Exception e) false;
  public /*@ spec_public @*/ static boolean f() {
    Object o = null;
    try {
      o.hashCode();
      // should pass
      return false;
    } catch (Exception e) {
    }
    return true;
  }
}
;