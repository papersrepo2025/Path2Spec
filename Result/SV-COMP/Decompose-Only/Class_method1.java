public class Class_method1 {
  //@ ensures \result == true;
  public static /*@ pure @*/ boolean fun() {
    return f(String.class, true);
  }

  //@ requires b == true;
  //@ ensures \result == true;
  //@ also
  //@ requires b == false;
  //@ ensures \result == false;
  public static /*@ pure @*/ boolean f(Class c, boolean b) {
    return b;
  }
}