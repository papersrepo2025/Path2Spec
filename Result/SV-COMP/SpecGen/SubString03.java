public class SubString03 {
  //@ public normal_behavior
  //@   requires letters != null;
  //@   requires letters.length() >= 13;
  //@   assignable \nothing;
  //@   ensures \result <==> letters.substring(9, 13).equals("teest");
  public static /*@ pure @*/ boolean f(String letters) {
    String tmp = letters.substring(9, 13);
    return tmp.equals("teest");
  }
}