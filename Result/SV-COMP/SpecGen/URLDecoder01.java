public class URLDecoder01 {

  //@ requires true;
  //@ ensures \result;
  public static /*@ spec_public @*/ boolean f(String arg) {

    String s1 = "some-url+" + arg;

    try {
      String s2 = java.net.URLDecoder.decode(s1, "UTF-8");
      if (!s2.startsWith("some")) {
        return false;
      }
    } catch (java.io.UnsupportedEncodingException e) {
    }
    return true;
  }
}