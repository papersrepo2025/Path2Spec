public class StringMiscellaneous01 {
  //@ requires true;
  //@ ensures \result == true || \result == false;
  public static boolean f() {
    String s1 = "Automatic Test Generation";
    String s2 = "noitareneG tseT citamotuA";
    String s3 = "Autom";
    char[] charArray = new char[5];

    if(s1.length() != 25) return false;

    int i = 0;
    for (int count = s1.length() - 1; count >= 0; count--) {
      System.out.printf("%c ", s1.charAt(count));
      if(s1.charAt(count) != s2.charAt(i)) return false;
      ++i;
    }

    // copy characters from string into charArray
    s1.getChars(0, 5, charArray, 0);
    i = 0;
    for (char character : charArray) {
      System.out.print(character);
      if(s3.charAt(i) != character) return false;
      ++i;
    }
    return true;
  }
}