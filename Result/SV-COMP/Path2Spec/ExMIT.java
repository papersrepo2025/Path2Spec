import java.util.Random;

public class ExMIT {
  //@ requires args != null;
  //@ ensures true;
  public static void main(String[] args) {
    if (new Random().nextInt() == 1) foo(0);
  }
  //@ requires i != 4;
  //@ ensures \result == 0;
  //@ also
  //@ requires i != 4;
  //@ requires -1073741825 <= i && i <= 1073741822;
  //@ ensures \result == 0;
  //@ also
  //@ requires 2 * (i + 1) != 10;
  //@ ensures \result == 0;
  //@ also
  //@ requires 2 * (i + 1) != 10;
  //@ ensures \result == 0 || \result == 1;
  public static int foo(int i) {
    if (2 * (i + 1) == 10) {
      assert false;
      return 1;
    }
    return 0;
  }
  //@ ensures ((i + 1) * 2 > 10) ==> \result == 1;
  //@ ensures !(((i + 1) * 2 > 10)) ==> \result == 0;
  //@ also
  //@ requires i > 4.0f;
  //@ ensures \result == 1;
  //@ also
  //@ requires i <= 4.0f;
  //@ ensures \result == 0;
  //@ also
  //@ requires i <= 4.0f || !(i == i);
  //@ ensures \result == 0;
  //@ also
  //@ requires i <= 4.0f || i != i;
  //@ ensures \result == 0;
  //@ also
  //@ ensures \result == 0 || \result == 1;
  public static int boo(float i) {
    if ((i + 1) * 2 > 10) return 1;
    return 0;
  }
}
