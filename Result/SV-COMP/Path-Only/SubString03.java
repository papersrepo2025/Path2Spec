public class SubString03 {
  //@ requires letters != null;
  //@ requires letters.length() >= 13;
  //@ requires letters.substring(9, 13).equals("teest");
  //@ ensures \result == true;
  //@ also
  //@ requires letters != null && letters.length() >= 13 && !letters.substring(9, 13).equals("teest");
  //@ ensures \result == false;
  public static boolean f(String letters) {
    String tmp = letters.substring(9, 13);
    return tmp.equals("teest");
  }
}
