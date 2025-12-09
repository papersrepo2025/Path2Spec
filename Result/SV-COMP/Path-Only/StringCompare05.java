public class StringCompare05 {
  //@ requires string != null;
  //@ ensures \result == false;
  //@ also
  //@ requires string == null;
  public static boolean f (String string) {
    String s1 = new String(string);
    return s1 == string;
  }
}
