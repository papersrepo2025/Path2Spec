public class StaticCharMethods03 {
  //@ requires arg != null;
  //@ ensures arg.length() < 1 ==> \result == -1;
  //@ ensures arg.length() >= 1 && !Character.isDefined(arg.charAt(0)) ==> \result == 1;
  //@ ensures arg.length() >= 1 && Character.isDefined(arg.charAt(0)) ==> \result == 0;
  //@ ensures \result == -1 || \result == 0 || \result == 1;
  public static int f(String arg) {
    if (arg.length() < 1) return -1;

    char c = arg.charAt(0);
    return (Character.isDefined(c) == false) ? 1 : 0;
  }
}