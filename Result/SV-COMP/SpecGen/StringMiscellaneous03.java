public class StringMiscellaneous03 {
  //@ requires s1 != null && s2 != null;
  //@ requires s2.length() >= s1.length();
  //@ assignable \nothing;
  //@ ensures \result <==> (\forall int k; 0 <= k && k < s1.length(); s1.charAt(s1.length() - 1 - k) != s2.charAt(k));
  public static boolean f(String s1, String s2) {
    int i = 0;
    //@ maintaining 0 <= i && i <= s1.length();
    //@ maintaining i <= s2.length();
    //@ maintaining i + count + 1 == s1.length();
    //@ maintaining (\forall int k; 0 <= k && k < i; s1.charAt(s1.length() - 1 - k) != s2.charAt(k));
    //@ decreasing count + 1;
    for (int count = s1.length() - 1; count >= 0; count--) {
      if (s1.charAt(count) == s2.charAt(i))
        return false;
      ++i;
    }
    return true;
  }
}