class Main {
  //@ public normal_behavior;
//@ ensures (i == null) ==> (\result == false);
//@ ensures (i != null) ==> (\result == true);
//@ assignable \nothing;
  public static boolean test(//@ nullable; Integer i) {
    if (i instanceof Integer) {
      return true;
    } else {
      return false;
    }
  }

  //@ public normal_behavior;
//@ ensures \result == true;
  public static boolean f() {
    return (!test(null)) && (test(new Integer(1)));
  }
}