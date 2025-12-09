public class StringCompare05 {
  //@ assignable \nothing;
  //@ ensures \result == false;
  //@ signals (NullPointerException) string == null;
  public static boolean f (String string) {
    String s1 = new String(string);
    return s1 == string;
  }
}