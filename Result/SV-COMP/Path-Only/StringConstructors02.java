public class StringConstructors02 {
  //@ requires arg != null;
  //@ requires arg.equals("");
  //@ ensures \result == true;
  //@ also
  //@ requires arg == null || !("".equals(arg));
  //@ ensures \result == false;
  public static boolean f(String arg) {
    String s1 = new String();
    return s1.equals(arg);
  }
}
