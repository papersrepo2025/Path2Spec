public class charArray {
  public static char[] f(char c[]) {
    if (c != null && c.length > 0) {
      c[0] = 's';
    }
    return c;
  }

  //@ requires arg != null && arg.length() != 5;
//@ assignable \nothing;
//@ ensures \result == -1;
  public static int fun(String arg) {
    if (arg.length() != 5) return -1;
    char[] c = f(arg.toCharArray());
    String s = new String("HELLO") + new String(c, 0, c.length);
    return (s.charAt(5) == 's') ? 1 : 0;
  }
}