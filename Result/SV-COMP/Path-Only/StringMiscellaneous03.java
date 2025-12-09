public class StringMiscellaneous03 {
  //@ requires s1 != null && s2 != null;
  //@ requires s2.length() >= s1.length();
  //@ requires (\exists int k; 0 <= k && k < s1.length() && k < s2.length(); s1.charAt(s1.length() - 1 - k) == s2.charAt(k) && (\forall int j; 0 <= j && j < k; s1.charAt(s1.length() - 1 - j) != s2.charAt(j)));
  //@ ensures \result == false;
  public static boolean f(String s1, String s2) {
    int i = 0;
    //@ loop_invariant s1 != null && s2 != null;
//@ loop_invariant 0 <= count && count < s1.length();
//@ loop_invariant 0 <= i && i <= s1.length();
//@ loop_invariant i == s1.length() - 1 - count;
//@ loop_invariant (\forall int j; 0 <= j && j < i; s1.charAt(s1.length() - 1 - j) != s2.charAt(j));
//@ loop_invariant s2.length() >= s1.length();
//@ decreases count + 1;
    for (int count = s1.length() - 1; count >= 0; count--) {
      if (s1.charAt(count) == s2.charAt(i))
        return false;
      ++i;
    }
    return true;
  }
}