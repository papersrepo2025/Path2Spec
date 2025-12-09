/*
 * Origin of the benchmark:
 *     license: 4-clause BSD (see /java/jbmc-regression/LICENSE)
 *     repo: https://github.com/diffblue/cbmc.git
 *     branch: develop
 *     directory: regression/cbmc-java/exceptions10
 * The benchmark was taken from the repo: 24 January 2018
 */
class A extends RuntimeException {}

class B extends A {}

class C extends B {}

public class exceptions10 {
  //@ requires true;
  //@ also
  //@ requires true;
  //@ ensures true;
  static void foo() {
    try {
      A b = new A();
      throw b;
    } catch (A exc) {
      //@ assume false;
      assert false;
    }
  }
  //@ requires true;
  //@ also
  //@ requires true;
  //@ ensures true;
  public static void main(String[] args) {
    try {
      A a = new A();
      foo();
    } catch (B exc) {
      //@ assume false;
      assert false;
    }
  }
}
