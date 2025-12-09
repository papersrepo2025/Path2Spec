public class URLDecoder02 {
  //@ requires true;
  //@ ensures \result == true || \result == false;
  //@ also
  //@ requires true;
  //@ ensures \result == true ==> true;
  //@ also
  //@ requires arg == null || (\forall int i; 0 <= i && i < arg.length(); arg.charAt(i) != '%');
  //@ ensures (\result == false) || (\result == true);
  public static boolean f(String arg) {

    String s1 = arg + "some-url";

    try {
      String s2 = java.net.URLDecoder.decode(s1, "UTF-8");
      //@ assume s2 != null;
      if (!s2.startsWith("some")) {
        return false;
      }
    } catch (java.io.UnsupportedEncodingException e) {
    }
    return true;
  }
}
