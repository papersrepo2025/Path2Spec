public class Ackermann {

  /*@ public normal_behavior
    @ requires m >= 0 && n >= 0 && n < Integer.MAX_VALUE;
    @ ensures (m == 0) ==> \result == n + 1;
    @ ensures (m > 0 && n == 0) ==> \result == Ackermann.ack(m - 1, 1);
    @ ensures (m > 0 && n > 0) ==> \result == Ackermann.ack(m - 1, Ackermann.ack(m, n - 1));
    @ assignable \nothing;
    @*/
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