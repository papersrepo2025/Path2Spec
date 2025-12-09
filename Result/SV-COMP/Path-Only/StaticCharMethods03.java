public class StaticCharMethods03 {
  //@ requires arg != null;
  //@ ensures \old(arg.length()) < 1 ==> \result == -1;
  //@ also
  //@ requires arg != null;
  //@ ensures (arg.length() < 1) ==> \result == -1;
  //@ ensures (arg.length() >= 1 && Character.isDefined(arg.charAt(0)) == true) ==> \result == 0;
  //@ ensures (arg.length() >= 1 && Character.isDefined(arg.charAt(0)) == false) ==> \result == 1;
  public static int f(String arg) {
    if (arg.length() < 1) return -1;

    char c = arg.charAt(0);
    return (Character.isDefined(c) == false) ? 1 : 0;
  }
}
