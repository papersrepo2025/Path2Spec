public class StringIndexMethods03 {
  //@ requires letters != null;
  //@ ensures \result == (letters.lastIndexOf('$') == 1);
  //@ assignable \nothing;
  /*@ spec_public @*/ public static boolean f(String letters) {
    return letters.lastIndexOf('$') == 1;
  }
}