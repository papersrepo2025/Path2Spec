public class StringIndexMethods02 {
  //@ requires letters != null;
//@ requires letters.length() < 7;
//@ || (letters.length() >= 7;
//@ && !(letters.charAt(6) == 'a';
//@ && (\forall int j; 1 <= j && j <= 5; letters.charAt(j) != 'a')));
//@ ensures \result == false;
  public static boolean f(String letters) {
    return letters.indexOf('a', 1) == 6;
  }
}