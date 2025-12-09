public class StringValueOf10 {
  //@ requires true;
//@ ensures true;
  public static boolean f(String arg) {
    Object objectRef = arg; // assign string to an Object reference
    String tmp = String.valueOf(objectRef);
    return tmp.equals(arg + "s");
  }
}