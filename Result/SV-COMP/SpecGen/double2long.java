public class double2long {

  /*@ public normal_behavior
    @   requires l > -100L && l < 100L;
    @   assignable \nothing;
    @   ensures \result == (double) l;
    @*/
  public static double bar(long l) {
    assert l < 100L && l > -100L;
    return (double) l;
  }

  /*@ public normal_behavior
    @   requires (x < 0.0 ==> -100.0 < x*10.0 && x*10.0 < 100.0)
    @         && (x >= 0.0 ==> -100.0 < x/10.0 && x/10.0 < 100.0);
    @   assignable \nothing;
    @   ensures \result == (double)((long)((x < 0.0) ? x*10.0 : x/10.0));
    @*/
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