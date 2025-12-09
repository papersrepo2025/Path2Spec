public class swap1 {
  //@ ensures \result;
  /*@ pure @*/ public static boolean f() {
    byte x = 5;
    //@ assume (x + 248) >= Byte.MIN_VALUE && (x + 248) <= Byte.MAX_VALUE;
    byte result = (byte) (x + 248);
    return result == -3;
  }
}