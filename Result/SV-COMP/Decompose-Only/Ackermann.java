public class Ackermann {

  //@ requires m == 0;
  //@ assignable \nothing;
  //@ ensures \result == n + 1;
  //@ also
  //@ requires m != 0 && n == 0;
  //@ assignable \nothing;
  //@ ensures true;
  //@ also
  //@ requires m != 0 && n != 0;
  //@ assignable \nothing;
  //@ ensures true;
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