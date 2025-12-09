public class double2long {
  //@ requires l > -100L && l < 100L;
  //@ ensures \result == (double) l;
  //@ also
  //@ requires l < 100L && l > -100L;
  //@ ensures \result == (double) l;
  //@ also
  //@ requires -100L < l && l < 100L;
  //@ ensures \result == (double) l;
  public static double bar(long l) {
    assert l < 100L && l > -100L;
    return (double) l;
  }
  //@ requires -10.0 < x && x < 10.0;
  //@ ensures \result == (double) ((long) (x < 0.0 ? x * 10.0 : x / 10.0));
  //@ also
  //@ requires -100.0 < ((x < 0.0) ? (x * 10.0) : (x / 10.0)) && ((x < 0.0) ? (x * 10.0) : (x / 10.0)) < 100.0;
  //@ ensures \result == (double)((long)((x < 0.0) ? (x * 10.0) : (x / 10.0)));
  //@ also
  //@ requires -100L < (long)((x < 0.0) ? x * 10.0 : x / 10.0) && (long)((x < 0.0) ? x * 10.0 : x / 10.0) < 100L;
  //@ ensures \result == (double)((long)((x < 0.0) ? x * 10.0 : x / 10.0));
  //@ also
  //@ requires -10.0 < x && x < 1000.0;
  //@ ensures (\old(x) < 0.0 ==> \result == (double)((long)(\old(x) * 10.0))) && (\old(x) >= 0.0 ==> \result == (double)((long)(\old(x) / 10.0)));
  //@ also
  //@ requires (x != x) || (0.0 <= x && x < 1000.0);
  //@ ensures \result == (double)((long)(\old(x) / 10.0));
  //@ also
  //@ requires x > -9.0 && x < 990.0;
  public static double foo(double x) {
    if (x < 0.0) {
      x *= 10.0;
    } else {
      x /= 10.0;
    }
    long l = (long) x;
    return bar(l);
  }

}
