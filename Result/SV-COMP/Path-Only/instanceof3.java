/*
 * Origin of the benchmark:
 *     license: 4-clause BSD (see /java/jbmc-regression/LICENSE)
 *     repo: https://github.com/diffblue/cbmc.git
 *     branch: develop
 *     directory: regression/cbmc-java/instanceof3
 * The benchmark was taken from the repo: 24 January 2018
 */
class Main {
  public static Object args;
  //@ requires args instanceof Object[];
  //@ ensures \result == true;
  //@ also
  //@ requires !(args instanceof Object[]);
  //@ ensures \result == false;
  public static boolean f() {
    return args instanceof Object[];
  }
}
