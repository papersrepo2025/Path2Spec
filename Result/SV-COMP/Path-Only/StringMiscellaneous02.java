public class StringMiscellaneous02 {
  //@ requires s1 != null;
  //@ requires s1.length() == 24;
  //@ ensures \result == true;
  //@ also
  //@ requires s1 != null && s1.length() != 24;
  //@ ensures \result == false;
  public static boolean f(String s1) {
    return s1.length() == 24;
  }
}
