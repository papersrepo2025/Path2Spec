public class charArray {
  //@ ensures \result == \old(c);
  //@ ensures c != null && c.length > 0 ==> (c[0] == 's' && (\forall int i; 1 <= i && i < c.length; c[i] == \old(c[i])));
  //@ ensures c != null && c.length == 0 ==> (\forall int i; 0 <= i && i < c.length; c[i] == \old(c[i]));
  public static char[] f(char c[]) {
    if (c != null && c.length > 0) {
      c[0] = 's';
    }
    return c;
  }

  //@ ensures arg != null && arg.length() != 5 ==> \result == -1;
  //@ ensures arg != null && arg.length() == 5 ==> \result == 1;
  public static int fun(String arg) {
    if (arg.length() != 5) return -1;
    char[] c = f(arg.toCharArray());
    String s = new String("HELLO") + new String(c, 0, c.length);
    return (s.charAt(5) == 's') ? 1 : 0;
  }
}