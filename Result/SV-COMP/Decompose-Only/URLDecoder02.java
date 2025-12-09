public class URLDecoder02 {

  public static boolean f(String arg) {

    String s1 = arg + "some-url";

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