public class StringIndexMethods02 {
  //@ requires letters != null;
  //@ ensures \result <==> (letters.indexOf('a', 1) == 6);
  //@ ensures \result ==> (letters.length() > 6 && letters.charAt(6) == 'a' && (\forall int i; 1 <= i && i < 6; letters.charAt(i) != 'a'));
  public /*@ spec_public pure @*/ static boolean f(String letters) {
    return letters.indexOf('a', 1) == 6;
  }
}