public class StaticCharMethods04 {
  //@ ensures \result == Character.isLetter(c);
  public static boolean f(char c) {
    return Character.isLetter(c);
  }
}