public class addition01 {

  //@ requires (n >= 0 ==> c + n <= 150) && (n <= 0 ==> c - n <= 150);
  //@ assignable \nothing;
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

  //@ assignable \nothing;
  //@ ensures (m < 0 || m >= 10000 || n < 0 || n >= 10000) ==> \result == -1;
  //@ ensures (0 <= m && m < 10000 && 0 <= n && n < 10000 && n <= 150) ==> \result == 1;
  public static int f(int m, int n) {
    if (m < 0 || m >= 10000) {
      return -1;
    }
    if (n < 0 || n >= 10000) {
      return -1;
    }
    int c = 0;
    //@ assume (n >= 0 ==> c + n <= 150) && (n <= 0 ==> c - n <= 150);
    int result = addition(m, n, c);
    return (result == m + n) ? 1 : 0;
  }
}