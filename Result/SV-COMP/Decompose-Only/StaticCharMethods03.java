public class StaticCharMethods03 {
  /*@ public normal_behavior
    @   requires arg != null && arg.length() < 1;
    @   assignable \nothing;
    @   ensures \result == -1;
    @ also
    @ public normal_behavior
    @   requires arg != null && arg.length() >= 1;
    @   assignable \nothing;
    @   ensures \result == (Character.isDefined(arg.charAt(0)) ? 0 : 1);
    @*/
  public static int f(String arg) {
    if (arg.length() < 1) return -1;

    char c = arg.charAt(0);
    return (Character.isDefined(c) == false) ? 1 : 0;
  }
}