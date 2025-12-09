public class StringValueOf04 {
  //@ requires booleanValue == true;
  //@ ensures \result == true;
  //@ also
  //@ requires booleanValue == false;
  //@ ensures \result == false;
  //@ also
  //@ ensures \result == booleanValue;
  public static /*@ pure @*/ boolean f(boolean booleanValue) {
    //@ assert booleanValue == true || booleanValue == false;
    String tmp = String.valueOf(booleanValue);
    return tmp.equals("true");
  }
}