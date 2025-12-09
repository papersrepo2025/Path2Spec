public class MultCommutative {
  //@ requires m != Integer.MIN_VALUE;
  //@ requires (m >= 0 ==> (\forall int k; 0 <= k && k <= m; Integer.MIN_VALUE <= n * k && n * k <= Integer.MAX_VALUE));
  //@ requires (m < 0 ==> (\forall int k; 0 <= k && k <= -m; Integer.MIN_VALUE <= n * k && n * k <= Integer.MAX_VALUE));
  //@ ensures (m < 0) ==> \result == n * (-m);
  //@ ensures (m == 0) ==> \result == 0;
  //@ ensures (m > 0) ==> \result == n * m;
  //@ also
  //@ requires m == 0;
  //@ ensures \result == 0;
  static int mult(int n, int m) {
    if (m < 0) {
      return mult(n, -m);
    }
    if (m == 0) {
      return 0;
    }
    return n + mult(n, m - 1);
  }
  
}
