public class StaticCharMethods02 {
  //@ requires arg != null && arg.length() < 1;
  //@ ensures \result == -1;
  //@ also
  //@ requires arg != null && arg.length() >= 1 && Character.toUpperCase(arg.charAt(0)) != Character.toLowerCase(arg.charAt(0));
  //@ ensures \result == 1;
  //@ also
  //@ requires arg != null && arg.length() >= 1 && Character.toUpperCase(arg.charAt(0)) == Character.toLowerCase(arg.charAt(0));
  //@ ensures \result == 0;
  public static /*@ pure @*/ int f(String arg) {
    if (arg.length() < 1) return -1;
    //@ assert arg != null && arg.length() >= 1;
    char c = arg.charAt(0);
    return (Character.toUpperCase(c) != Character.toLowerCase(c)) ? 1 : 0;
  }
}