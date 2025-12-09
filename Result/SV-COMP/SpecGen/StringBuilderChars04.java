public class StringBuilderChars04 {
  //@ requires arg != null;
  //@ ensures \result <==> (arg.length() == 0);
  public static boolean f(String arg) {
    StringBuilder buffer = new StringBuilder(arg);

    char[] charArray = new char[buffer.length()];
    buffer.getChars(0, buffer.length(), charArray, 0);

    int i = 0;
    //@ maintaining 0 <= i && i <= charArray.length && charArray.length == buffer.length();
    //@ decreases charArray.length - i;
    for (char character : charArray) {
      System.out.print(character);
      if (character == buffer.charAt(i))
        return false;
      ++i;
    }
    return true;
  }
}