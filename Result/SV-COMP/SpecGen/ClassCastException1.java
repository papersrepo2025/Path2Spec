public class ClassCastException1 {
  //@ ensures true;
  public static boolean f() {
    try {
      Object x = new Integer(0);
      //@ assume false;
      String y = (String) x;
    } catch (ClassCastException exc) {
      return false;
    }
    return true;
  }
}