public class StringCompare05 {
  //@ requires string != null;
  //@ ensures \result == false;
  //@ also //@ requires string == null;
  //@ signals (NullPointerException) true;
  public static boolean f (String string) {
    String s1 = new String(string);
    return s1 == string;
  }
}