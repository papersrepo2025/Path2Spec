public class Class_method1 {
  //@ assignable \nothing;
  //@ ensures \result == true;
  public static boolean fun() {
    return f(String.class, true);
  }

  //@ assignable \nothing;
  //@ ensures \result == b;
  public static boolean f(/*@ nullable @*/ Class c, boolean b) {
    return b;
  }
}