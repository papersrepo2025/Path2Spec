public class StringBuilderChars05 {
  //@ requires arg != null && arg.length() >= 7;
  //@ ensures \result ==> arg.length() == "HiffBllTe Limited".length();
  //@ ensures \result ==> (\forall int i; 0 <= i && i < arg.length(); (i != 0 && i != 6) ==> arg.charAt(i) == "HiffBllTe Limited".charAt(i));
  /*@ spec_public @*/ public static boolean f(String arg) {
    StringBuilder buffer = new StringBuilder(arg);
    buffer.setCharAt(0, 'H');
    buffer.setCharAt(6, 'T');
    return buffer.toString().equals("HiffBllTe Limited");
  }
}