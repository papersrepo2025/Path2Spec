public class StringMiscellaneous03 {
  //@ requires s1 != null && s2 != null;
  //@ requires s2.length() >= s1.length();
  //@ ensures \result == true || \result == false;
  //@ also
  //@ requires s1 != null && s2 != null && s2.length() >= s1.length() && s1.length() == 0;
  //@ ensures \result == true;
  public static boolean f(String s1, String s2) {
    int i = 0;
    for (int count = s1.length() - 1; count >= 0; count--) {
      if (s1.charAt(count) == s2.charAt(i))
        return false;
      ++i;
    }
    return true;
  }
}