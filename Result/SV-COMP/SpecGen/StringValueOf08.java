public class StringValueOf08 {
  /*@ public normal_behavior
    @   assignable \nothing;
    @   ensures \result == (java.lang.String.valueOf(java.lang.Float.parseFloat(arg)).equals("2.50"));
    @ also
    @ public exceptional_behavior
    @   signals (java.lang.NullPointerException e) (arg == null);
    @   signals (java.lang.NumberFormatException e) (arg != null);
    @*/
  /*@ spec_public @*/ public static boolean f(String arg) {
    float floatValue = Float.parseFloat(arg);
    String tmp = String.valueOf(floatValue);
    return tmp.equals("2.50");
  }
}