public class OverapproximationString01 {
  /*@ public normal_behavior
    @   assignable \nothing;
    @   ensures !(("abc" + s).equals("not possible"));
    @*/
  public static void foo(String s) {
    String prefix = "abc";
    String complete = prefix + s;
    if (complete.equals("not possible")) {
      assert (true);
    } else {
      assert (false);
    }
  }

}