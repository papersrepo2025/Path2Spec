public class StringValueOf09 {
  /*@
    @ public normal_behavior
    @   assignable \nothing;
    @   ensures \result == ("33.3333".equals(String.valueOf(Double.parseDouble(arg))));
    @ also
    @ exceptional_behavior
    @   assignable \nothing;
    @   signals (NullPointerException e) true;
    @ also
    @ exceptional_behavior
    @   assignable \nothing;
    @   signals (NumberFormatException e) true;
    @*/
  public static boolean f(String arg) {
    double doubleValue = Double.parseDouble(arg); // no suffix, double is default
    String tmp = String.valueOf(doubleValue);
    return tmp.equals("33.3333");
  }
}