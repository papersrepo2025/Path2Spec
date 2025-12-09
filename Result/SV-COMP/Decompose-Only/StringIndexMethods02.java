public class StringIndexMethods02 {
  //@ requires letters != null && letters.indexOf('a', 1) == 6;
  //@ ensures \result == true;
  //@ also
  //@ requires letters != null && letters.indexOf('a', 1) != 6;
  //@ ensures \result == false;
  public /*@ pure @*/ static boolean f(String letters) {
    return letters.indexOf('a', 1) == 6;
  }
}