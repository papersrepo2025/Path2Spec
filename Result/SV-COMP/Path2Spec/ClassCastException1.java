public class ClassCastException1 {
  //@ requires true;
  //@ ensures true;
  //@ also
  //@ requires false;
  //@ ensures \result == true;
  public static boolean f() {
    try {
      Object x = new Integer(0);
      //@ assume x instanceof String;
      String y = (String) x;
    } catch (ClassCastException exc) {
      return false;
    }
    return true;
  }
}
