public class StringConstructors02 {
  //@ ensures \result <==> (arg != null && arg.length() == 0);
  public static boolean f(String arg) {
    String s1 = new String();
    return s1.equals(arg);
  }
}