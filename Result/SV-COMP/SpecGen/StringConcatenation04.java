public class StringConcatenation04 {
  /*@ public normal_behavior
    @   requires s1 != null;
    @   ensures \result <==> s1.equals("Happy  at");
    @ also
    @ public exceptional_behavior
    @   requires s1 == null;
    @   signals (NullPointerException e) true;
    @*/
  public static /*@ pure @*/ boolean f(String s1){
    String tmp = s1;
    return tmp.equals("Happy  at");
  }
}