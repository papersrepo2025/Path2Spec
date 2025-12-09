public class StringMiscellaneous01 {
  //@ ensures \result ==> ("Automatic Test Generation".length() == 25
  //@                   && (\forall int k; 0 <= k && k < "Automatic Test Generation".length();
  //@                         "Automatic Test Generation".charAt("Automatic Test Generation".length() - 1 - k)
  //@                           == "noitareneG tseT citamotuA".charAt(k))
  //@                   && (\forall int k; 0 <= k && k < 5;
  //@                         "Automatic Test Generation".charAt(k) == "Autom".charAt(k)));
  public static boolean f() {
    String s1 = "Automatic Test Generation";
    String s2 = "noitareneG tseT citamotuA";
    String s3 = "Autom";
    char[] charArray = new char[5];

    if(s1.length() != 25) return false;

    int i = 0;
    //@ maintaining -1 <= count && count < s1.length();
    //@ maintaining 0 <= i && i <= s1.length();
    //@ maintaining i == s1.length() - 1 - count;
    //@ maintaining (\forall int k; 0 <= k && k < i; s1.charAt(s1.length() - 1 - k) == s2.charAt(k));
    //@ decreases count + 1;
    for (int count = s1.length() - 1; count >= 0; count--) {
      System.out.printf("%c ", s1.charAt(count));
      if(s1.charAt(count) != s2.charAt(i)) return false;
      ++i;
    }

    // copy characters from string into charArray
    s1.getChars(0, 5, charArray, 0);
    i = 0;
    //@ maintaining 0 <= i && i <= charArray.length;
    //@ maintaining (\forall int k; 0 <= k && k < i; s3.charAt(k) == charArray[k]);
    //@ decreases charArray.length - i;
    for (char character : charArray) {
      System.out.print(character);
      if(s3.charAt(i) != character) return false;
      ++i;
    }
    return true;
  }
}