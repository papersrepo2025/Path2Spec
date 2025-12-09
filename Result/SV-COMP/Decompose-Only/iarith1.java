/*
 * Origin of the benchmark:
 *     license: 4-clause BSD (see /java/jbmc-regression/LICENSE)
 *     repo: https://github.com/diffblue/cbmc.git
 *     branch: develop
 *     directory: regression/cbmc-java/iarith1
 * The benchmark was taken from the repo: 24 January 2018
 */
class Main {
  //@ requires args != null;
  //@ assignable \nothing;
  //@ ensures true;
  public static void main(String[] args) {
    int i = 99;
    ++i;
    int tmp = i + 2;
    i = tmp;
    tmp = i - 3;
    i = tmp;
    i += 3;
    i -= 3;
    tmp = i * 2;
    i = tmp;
    //@ assert 3 != 0;
    tmp = i / 3;
    i = tmp;
    //@ assert 34 != 0;
    i %= 34;
    i = -i;
    assert i == -32;
    long l = 99;
    ++l;
    l += 2L;
    l -= 3L;
    l *= 2L;
    //@ assert 3L != 0L;
    l /= 3L;
    //@ assert 34L != 0L;
    l %= 34L;
    l = -l;
    assert l == -32L;
  }
}