public class MultCommutative {

  //@ requires m < 0;
  //@ ensures \result == MultCommutative.mult(n, -m);
  //@ also
  //@ requires m == 0;
  //@ ensures \result == 0;
  //@ also
  //@ requires m > 0;
  //@ ensures \result == n + MultCommutative.mult(n, m - 1);
  static /*@ pure @*/ int mult(int n, int m) {
    if (m < 0) {
      return mult(n, -m);
    }
    if (m == 0) {
      return 0;
    }
    return n + mult(n, m - 1);
  }
  
}