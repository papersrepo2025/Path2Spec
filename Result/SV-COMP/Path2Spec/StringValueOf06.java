public class StringValueOf06 {
  //@ requires integerValue != 77;
  //@ ensures \result == false;
  public static boolean f(int integerValue) {
    String tmp = String.valueOf(integerValue);
    //@ assume integerValue != 77 ==> !tmp.equals("77");
    return tmp.equals("77");
  }
}