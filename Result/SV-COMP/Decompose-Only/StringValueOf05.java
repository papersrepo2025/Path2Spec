public class StringValueOf05 {
  //@ requires arg != null && arg.length() < 1;
  //@ ensures \result == false;
  //@ also
  //@ requires arg != null && arg.length() >= 1;
  //@ ensures \result == (arg.charAt(0) == 'A');
  public static boolean f(String arg) {
    if (arg.length() < 1) return false;

    char characterValue = arg.charAt(0);
    String tmp = String.valueOf(characterValue);
    return tmp.equals("A");
  }
}