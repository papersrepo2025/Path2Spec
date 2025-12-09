/*
 * Origin of the benchmark:
 *     license: 4-clause BSD (see /java/jbmc-regression/LICENSE)
 *     repo: https://github.com/diffblue/cbmc.git
 *     branch: develop
 *     directory: regression/cbmc-java/instanceof2
 * The benchmark was taken from the repo: 24 January 2018
 */
class Main {
  /*@ spec_public @*/
  /*@ ensures \result == (int.class instanceof Object);
    @ assignable \nothing;
    @*/
  public static boolean f() {
    return int.class instanceof Object;
  }
}
;