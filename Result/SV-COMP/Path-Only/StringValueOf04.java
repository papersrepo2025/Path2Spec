public class StringValueOf04 {
  //@ requires booleanValue == false;
  //@ ensures \result == false;
  //@ also
  //@ requires booleanValue == true;
  //@ ensures \result == true;
  public static boolean f(boolean booleanValue) {
    String tmp = String.valueOf(booleanValue);
    //@ assert tmp != null;
    return tmp.equals("true");
  }
}
