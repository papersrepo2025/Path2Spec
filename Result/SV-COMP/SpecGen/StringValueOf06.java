public class StringValueOf06 {
  //@ ensures \result <==> integerValue == 77;
  //@ assignable \nothing;
  public static boolean f(int integerValue) {
    String tmp = String.valueOf(integerValue);
    return tmp.equals("77");
  }
}