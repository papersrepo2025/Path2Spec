public class Ackermann {
  //@ requires m == 0 && n < Integer.MAX_VALUE;
  //@ ensures \result == n + 1;
  public static int ack(int m, int n) {
    if (m == 0) {
      return n + 1;
    }
    if (n == 0) {
      return ack(m - 1, 1);
    }
    return ack(m - 1, ack(m, n - 1));
  }

}
