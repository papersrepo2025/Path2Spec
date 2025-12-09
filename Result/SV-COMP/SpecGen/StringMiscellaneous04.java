public class StringMiscellaneous04 {
  // This is a model of the String.toCharArray method
  //@ requires s != null;
  //@ ensures \result != null;
  //@ ensures \result.length == s.length();
  //@ ensures (\forall int j; 0 <= j && j < ((s.length() < 10) ? s.length() : 10); \result[j] == s.charAt(j));
  //@ ensures s.length() > 10 ==> (\forall int j; 10 <= j && j < s.length(); \result[j] == '\u0000');
  /*@ spec_public @*/ public static char[] toCharArray(String s) {
    int length = s.length();
    assert (length < 10);
    char arr[] = new char[s.length()];
    // We limit arbitrarly the loop unfolding to 10
    //@ maintaining 0 <= i && i <= length && i <= 10 && arr != null && arr.length == s.length();
    //@ maintaining (\forall int j; 0 <= j && j < i; arr[j] == s.charAt(j));
    //@ maintaining (\forall int j; i <= j && j < arr.length; arr[j] == '\u0000');
    //@ decreases ((length < 10) ? length : 10) - i;
    for (int i = 0; i < length && i < 10; i++) arr[i] = s.charAt(i);
    return arr;
  }

  //@ requires true;
  //@ ensures true;
  /*@ spec_public @*/ public static void main(String[] args) {
    String s1 = "diffblue";
    String s2 = "TESTGENERATION";
    String s3 = "   automated   ";

    assert s1.equals("diffblue");
    assert s2.equals("TESTGENERATION");
    assert s3.equals("   automated   ");

    System.out.printf("Replace 'f' with 'F' in s1: %s\n\n", s1.replace('f', 'F'));
    String tmp = s1.replace('f', 'F');
    assert tmp.equals("diFFblue");

    tmp = s1.toUpperCase();
    assert tmp.equals("DIFFBLUE");

    tmp = s2.toLowerCase();
    assert tmp.equals("testgeneration");

    tmp = s3.trim();
    assert tmp.equals("automated");

    // test toCharArray method
    char[] charArray = toCharArray(s1);
    System.out.print("s1 as a character array = ");

    int i = 0;
    //@ maintaining 0 <= i && i <= charArray.length && (\forall int j; 0 <= j && j < i; charArray[j] == "diffblue".charAt(j));
    //@ decreases charArray.length - i;
    for (char character : charArray) {
      assert character == "diffblue".charAt(i);
      ++i;
    }

    System.out.println();
  }
}