public class addition01 {
  //@ requires (n == 0) || (((n >= 0 ? n : -n) <= 150 - c) && c >= 0);
  //@ requires Integer.MIN_VALUE <= m + n && m + n <= Integer.MAX_VALUE;
  //@ ensures \result == m + n;
  //@ also
  //@ requires 0 <= n && 0 <= c && n + c <= 150;
  //@ requires Integer.MIN_VALUE <= m + n && m + n <= Integer.MAX_VALUE;
  //@ ensures \result == m + n;
  public static int addition(int m, int n, int c) {
    if (n == 0) {
      return m;
    }

    if (c >= 150) {
      assert false;
    }

    if (n > 0) {
      return addition(m + 1, n - 1, ++c);
    } else {
      return addition(m - 1, n + 1, ++c);
    }
  }
  //@ requires (m < 0 || m >= 10000 || n < 0 || n >= 10000) || ((n >= 0 ? n : -n) <= 150);
  //@ ensures (m < 0 || m >= 10000 || n < 0 || n >= 10000) ==> \result == -1;
  //@ ensures (0 <= m && m < 10000 && 0 <= n && n < 10000 && (n >= 0 ? n : -n) <= 150) ==> \result == 1;
  //@ also
  //@ requires 0 <= m && m < 10000 && 0 <= n && n <= 150 && Integer.MIN_VALUE <= m + n && m + n <= Integer.MAX_VALUE;
  //@ ensures \result == 1;
  public static int f(int m, int n) {
    if (m < 0 || m >= 10000) {
      return -1;
    }
    if (n < 0 || n >= 10000) {
      return -1;
    }
    int c = 0;
    int result = addition(m, n, c);
    return (result == m + n) ? 1 : 0;
  }
}
