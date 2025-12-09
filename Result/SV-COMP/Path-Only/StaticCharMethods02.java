public class StaticCharMethods02 {
  //@ requires arg != null;
  //@ requires arg.length() == 0;
  //@ ensures \result == -1;
  //@ also
  //@ requires arg != null;
  //@ requires arg.length() >= 1;
  //@ requires Character.toUpperCase(arg.charAt(0)) != Character.toLowerCase(arg.charAt(0));
  //@ ensures \result == 1;
  //@ also
  //@ requires arg != null && arg.length() >= 1 && Character.toUpperCase(arg.charAt(0)) == Character.toLowerCase(arg.charAt(0));
  //@ ensures \result == 0;
  //@ also
  //@ requires arg == null;
  //@ also
  //@ requires arg != null;
  //@ ensures (\result == -1) || (\result == 0) || (\result == 1);
  public static int f(String arg) {
    if (arg.length() < 1) return -1;
    char c = arg.charAt(0);
    return (Character.toUpperCase(c) != Character.toLowerCase(c)) ? 1 : 0;
  }
}
