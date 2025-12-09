public class StringConcatenation04 {
  //@ requires s1 != null && s1.equals("Happy  at");
  //@ ensures \result == true;
  //@ also
  //@ requires s1 != null && !s1.equals("Happy  at");
  //@ ensures \result == false;
  //@ also
  //@ requires s1 == null;
  //@ also
  //@ requires s1 != null;
  //@ ensures \result == s1.equals("Happy  at");
  public static boolean f(String s1){
    String tmp = s1;
    return tmp.equals("Happy  at");
  }
}
