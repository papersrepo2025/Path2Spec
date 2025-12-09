public class double2long {

  /*@ public normal_behavior
    @   requires -100 < l && l < 100;
    @   assignable \nothing;
    @   ensures \result == (double) l;
    @*/
  public static /*@ pure @*/ double bar(long l) {
    assert l < 100L && l > -100L;
    return (double) l;
  }

  /*@ public normal_behavior
    @   requires x < 0.0;
    @   requires -100 < (long)(x * 10.0) && (long)(x * 10.0) < 100;
    @   assignable \nothing;
    @   ensures \result == (double) (long) (\old(x) * 10.0);
    @ also
    @   public normal_behavior
    @   requires 0.0 <= x;
    @   requires 10.0 != 0.0;
    @   requires -100 < (long)(x / 10.0) && (long)(x / 10.0) < 100;
    @   assignable \nothing;
    @   ensures \result == (double) (long) (\old(x) / 10.0);
    @*/
  public static /*@ pure @*/ double foo(double x) {
    if (x < 0.0) {
      x *= 10.0;
    } else {
      //@ assert 10.0 != 0.0;
      x /= 10.0;
    }
    long l = (long) x;
    //@ assert -100 < l && l < 100;
    return bar(l);
  }

}