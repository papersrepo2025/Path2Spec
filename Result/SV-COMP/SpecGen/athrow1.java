class A extends Exception {}

class athrow1 {
  /*@ public normal_behavior
    @   assignable \nothing;
    @   ensures \result == false;
    @*/
  public static boolean func() {
    A a = new A();
    try {
      throw a;
    } catch (Exception e) {
      return false;
    }
    // return true;
  }
}