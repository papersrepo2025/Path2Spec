public class StaticCharMethods04 {
  //@ requires Character.isLetter(c);
  //@ assignable \nothing;
  //@ ensures \result == true;
  //@ also
  //@ requires !Character.isLetter(c);
  //@ assignable \nothing;
  //@ ensures \result == false;
  public /*@ pure @*/ static boolean f(char c) {
    return Character.isLetter(c);
  }
}