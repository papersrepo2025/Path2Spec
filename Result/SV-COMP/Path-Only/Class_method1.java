public class Class_method1 {
  //@ requires true;
  //@ ensures \result == true;
  public static boolean fun() {
    return f(String.class, true);
  }
  //@ requires true;
  //@ ensures \result == b;
  public static boolean f(Class c, boolean b) {
    return b;
  }
}
