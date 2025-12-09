public class StaticCharMethods04 {
  //@ requires Character.isLetter(c);
  //@ ensures \result == true;
  //@ also
  //@ requires !Character.isLetter(c);
  //@ ensures \result == false;
  public static boolean f(char c) {
    return Character.isLetter(c);
  }
}
