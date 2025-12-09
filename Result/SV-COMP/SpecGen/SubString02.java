public class SubString02 {
  //@ requires letters != null && letters.length() >= 20;
  //@ ensures \result == letters.substring(20).equals("erationatdifffblue");
  //@ assignable \nothing;
  public static boolean f(String letters) {
    String tmp = letters.substring(20);
    return tmp.equals("erationatdifffblue");
  }
}