public class MultCommutative {

  //@ requires m != Integer.MIN_VALUE;
  //@ requires ((long)(n < 0 ? -n : n)) * ((long)(m < 0 ? -m : m)) <= Integer.MAX_VALUE;
  //@ assignable \nothing;
  //@ ensures \result == n * (m < 0 ? -m : m);
  /*@ pure @*/ static int mult(int n, int m) {
    if (m < 0) {
      return mult(n, -m);
    }
    if (m == 0) {
      return 0;
    }
    return n + mult(n, m - 1);
  }
  
}