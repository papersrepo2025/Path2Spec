public class StringConstructors02 {
  //@ requires arg == null;
  //@ ensures \result == false;
  //@ also
  //@ requires arg != null && arg.length() == 0;
  //@ ensures \result == true;
  //@ also
  //@ requires arg != null && arg.length() > 0;
  //@ ensures \result == false;
  public static boolean f(String arg) {
    //@ assert true;
    String s1 = new String();
    return s1.equals(arg);
  }
}