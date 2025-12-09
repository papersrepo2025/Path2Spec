public class SubString03 {
  //@ requires letters != null && letters.length() >= 13;
  //@ ensures \result == letters.substring(9, 13).equals("teest");
  public static boolean f(String letters) {
    String tmp = letters.substring(9, 13);
    return tmp.equals("teest");
  }
}