public class StringBuilderChars01 {
  //@ ensures \result;
  public static boolean f() {
    StringBuilder buffer = new StringBuilder("DiffBlue Limited");

    if(!(buffer.toString().equals("DiffBlue Limited"))) return false;
    if(!(buffer.charAt(0) != buffer.charAt(4))) return false;

    char[] charArray = new char[buffer.length()];
    buffer.getChars(0, buffer.length(), charArray, 0);

    int i = 0;
    //@ maintaining 0 <= i && i <= charArray.length;
    //@ maintaining (\forall int k; 0 <= k && k < i; charArray[k] == buffer.charAt(k));
    //@ decreases charArray.length - i;
    for (char character : charArray) {
      System.out.print(character);
      if(!(character == buffer.charAt(i))) return false;
      ++i;
    }

    buffer.setCharAt(0, 'H');
    buffer.setCharAt(6, 'T');
    if(!(buffer.toString().equals("HiffBlTe Limited"))) return false;

    buffer.reverse();
    if (!(buffer.toString().equals("detimiL eTlBffiH")))
      return false;
    return true;
  }
}