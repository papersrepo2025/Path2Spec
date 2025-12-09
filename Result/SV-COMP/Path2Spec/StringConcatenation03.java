public class StringConcatenation03 {
  //@ requires s1 != null && s2 != null && s1.concat(s2).equals("Happy at DiffBllue") && !s1.equals("Happy at");
  //@ ensures \result == false;
  //@ also
  //@ requires s1 != null && s2 != null;
  //@ requires s1.equals("Happy at") && s2.equals(" DiffBllue");
  //@ ensures \result == true;
  //@ also
  //@ requires s1 != null && s2 != null;
  //@ ensures true;
  //@ also
  //@ requires s1 == null;
  //@ also
  //@ requires s1 != null && s2 == null;
  public static boolean f(String s1, String s2) {
    System.out.printf("Result of s1.concat(s2) = %s\n", s1.concat(s2));
    String tmp = s1.concat(s2);
    if (!tmp.equals("Happy at DiffBllue"))
      return false;

    tmp = s1;
    System.out.printf("s1 after concatenation = %s\n", s1);
    if (!tmp.equals("Happy at"))
      return false;
    return true;
  }

}
