public class StringConcatenation04 {
  //@ requires s1 != null;
  //@ assignable \nothing;
  //@ ensures \result <==> s1.equals("Happy  at");
  //@ also
  //@ requires s1 != null && s1.equals("Happy  at");
  //@ assignable \nothing;
  //@ ensures \result;
  //@ also
  //@ requires s1 != null && !s1.equals("Happy  at");
  //@ assignable \nothing;
  //@ ensures !\result;
  public static boolean f(String s1){
    //@ assert s1 != null;
    String tmp = s1;
    return tmp.equals("Happy  at");
  }
}