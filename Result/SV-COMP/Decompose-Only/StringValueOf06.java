public class StringValueOf06 {
  //@ requires integerValue == 77;
  //@ assignable \nothing;
  //@ ensures \result == true || \result == false;
  //@ also
  //@ requires integerValue != 77;
  //@ assignable \nothing;
  //@ ensures \result == true || \result == false;
  //@ also
  //@ assignable \nothing;
  //@ ensures \result == true || \result == false;
  public static boolean f(int integerValue) {
    String tmp = String.valueOf(integerValue);
    return tmp.equals("77");
  }
}