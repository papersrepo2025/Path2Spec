public class StringValueOf09 {
  //@ public static ghost String lastTmp;

  //@ requires arg != null;
//@ ensures \result == lastTmp.equals("33.3333");
//@ signals (NumberFormatException e) true;
  public static boolean f(String arg) {
    double doubleValue = Double.parseDouble(arg); // no suffix, double is default
    String tmp = String.valueOf(doubleValue);
    //@ set lastTmp = tmp;
    return tmp.equals("33.3333");
  }
}