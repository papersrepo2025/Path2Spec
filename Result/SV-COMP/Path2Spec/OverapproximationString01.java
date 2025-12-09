public class OverapproximationString01 {
  //@ requires ("abc" + s).equals("not possible");
  public static void foo(String s) {
    String prefix = "abc";
    String complete = prefix + s;
    //@ assume complete.equals("not possible");
    if (complete.equals("not possible")) {
      assert (true);
    } else {
      assert (false);
    }
  }

}