/*
 * Origin of the benchmark:
 *     license: 4-clause BSD (see /java/jbmc-regression/LICENSE)
 *     repo: https://github.com/diffblue/cbmc.git
 *     branch: develop
 *     directory: regression/cbmc-java/exceptions7
 * The benchmark was taken from the repo: 24 January 2018
 */
class A extends RuntimeException {}

class B extends A {}

class C extends B {}

class exceptions7 {
  /*@ public normal_behavior
    @   requires true;
    @   ensures true;
    @*/
  public static void main(String[] args) {
    try {
      try {
        C c = new C();
        A a = new A();
        throw a;
      } catch (C exc) {
        //@ assume false;
        assert false;
      } catch (B exc) {
        //@ assume false;
        assert false;
      }
    } catch (A exc) {
      //@ assume false;
      assert false;
    }
  }
}