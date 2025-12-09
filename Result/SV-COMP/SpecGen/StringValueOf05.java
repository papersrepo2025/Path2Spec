public class StringValueOf05 {
  //@ requires arg != null;
  //@ assignable \nothing;
  //@ ensures (arg.length() < 1) ==> (\result == false);
  //@ ensures (arg.length() >= 1) ==> (\result == (arg.charAt(0) == 'A'));
  //@ ensures \result <==> (arg.length() >= 1 && arg.charAt(0) == 'A');
  public static boolean f(String arg) {
    if (arg.length() < 1) return false;

    char characterValue = arg.charAt(0);
    String tmp = String.valueOf(characterValue);
    return tmp.equals("A");
  }
}