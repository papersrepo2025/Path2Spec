public class StringIndexMethods05 {
  /*@
    public normal_behavior
      requires letters != null;
      ensures \result == (letters.lastIndexOf("diffblue", 25) == 1);
  @*/
  public /*@ pure @*/ static boolean f(String letters) {
    return letters.lastIndexOf("diffblue", 25) == 1;
  }
}