public class StringValueOf08 {
  //@ requires arg != null;
  //@ assignable \nothing;
  //@ ensures \result == true || \result == false;
  //@ also
  //@ requires arg == null;
  //@ assignable \nothing;
  //@ ensures true;
  public static /*@ pure @*/ boolean f(String arg) {
    float floatValue = Float.parseFloat(arg);
    String tmp = String.valueOf(floatValue);
    return tmp.equals("2.50");
  }
}