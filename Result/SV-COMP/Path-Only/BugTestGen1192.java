public class BugTestGen1192 {
  //@ requires true;
  //@ ensures true;
  public static boolean fun() {
    long longValue = 10000000000L; // L suffix indicates long
    String tmp = String.valueOf(longValue);
    return tmp.equals("10000000000");
  }
}
