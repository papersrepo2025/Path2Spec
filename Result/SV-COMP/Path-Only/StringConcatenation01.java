public class StringConcatenation01 {
  //@ requires arg1 != null && arg2 != null;
//@ requires arg1.equals(arg1);
//@ requires arg2.equals(arg2);
//@ requires arg1.concat(arg2).equals(arg1 + arg2);
//@ ensures \result == true;
  public static boolean f(String arg1, String arg2) {
    String[] args = new String[2];
    args[0] = arg1;
    args[1] = arg2;

    String s1 = args[0];
    String s2 = args[1];

    if (!(s1.equals(args[0]))) return false;
    if (!(s2.equals(args[1]))) return false;

    String tmp = s1.concat(s2);
    if (!(tmp.equals(args[0] + args[1]))) return false;

    tmp = s1;
    if (!(tmp.equals(args[0])))
      return false;
    return true;
  }
}