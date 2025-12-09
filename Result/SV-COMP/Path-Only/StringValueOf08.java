public class StringValueOf08 {
  //@ public ghost static String gtmp;

  //@ requires arg != null;
//@ assignable gtmp;
//@ ensures gtmp != null;
//@ ensures \result == gtmp.equals("2.50");
  public static boolean f(String arg) {
    float floatValue = Float.parseFloat(arg);
    String tmp = String.valueOf(floatValue);
    //@ set gtmp = tmp;
    return tmp.equals("2.50");
  }
}