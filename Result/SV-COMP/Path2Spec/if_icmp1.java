public class if_icmp1 {
  //@ ensures \result == (j > i);
  //@ also
  //@ ensures (i > j) ==> (\result == false);
  //@ also
  //@ requires i > j;
  //@ ensures \result == false;
  //@ also
  //@ requires i < j;
  //@ ensures \result == true;
  //@ also
  //@ ensures \result == (i < j);
  private static boolean f(int i, int j) {
    if (i == j) {
      return false;
    }
    if (i >= j) {
      return false;
    }
    if (2 > 1) {
      if (j > i) {
        return true;
      } else {
        return false;
      }
    }
    if (j <= i) {
      return false;
    }
    if (j < i) {
      return false;
    } else {
      return true;
    }
  }
  //@ requires Integer.MIN_VALUE <= i + 1 && i + 1 <= Integer.MAX_VALUE;
  //@ ensures \result;
  //@ also
  //@ ensures \result == true;
  //@ also
  //@ requires i <= -2;
  //@ ensures \result == true;
  //@ also
  //@ requires i < Integer.MAX_VALUE;
  //@ requires i + 1 >= 0;
  //@ ensures \result == true;
  //@ also
  //@ ensures (i + 1 >= 0) ==> \result == true;
  public static boolean fun(int i) {
    if (i + 1 < 0) return true;
    return f(i, i + 1);
  }
}
