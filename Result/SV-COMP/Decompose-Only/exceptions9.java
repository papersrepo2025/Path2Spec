/*
 * Origin of the benchmark:
 *     license: 4-clause BSD (see /java/jbmc-regression/LICENSE)
 *     repo: https://github.com/diffblue/cbmc.git
 *     branch: develop
 *     directory: regression/cbmc-java/exceptions9
 * The benchmark was taken from the repo: 24 January 2018
 */
class A extends RuntimeException {
  /*@ spec_public @*/ int i = 1;
  //@ public invariant i == 1;
}
;

class B extends A {}
;

class exceptions9 {
  /*@
    @ public exceptional_behavior
    @   requires k == 0;
    @   assignable \nothing;
    @   signals_only A;
    @   signals (A e) e.i == 1;
    @ also
    @ public exceptional_behavior
    @   requires k != 0;
    @   assignable \nothing;
    @   signals_only A;
    @   signals (A e) e.i == 1;
    @*/
  static int foo(int k) {
    try {
      if (k == 0) {
        A a = new A();
        throw a;
      } else {
        A b = new A();
        throw b;
      }

    } catch (B exc) {
      assert exc.i == 1;
    }
    return 1;
  }

  /*@
    @ public normal_behavior
    @   requires args != null;
    @   assignable \everything;
    @   ensures true;
    @*/
  public static void main(String[] args) {
    try {
      A a = new A();
      foo(6);
    } catch (A exc) {
      assert exc.i == 1;
    }
  }
}