import java.util.Random;

public class ExMIT {
  //@ ensures true;
  public static void main(String[] args) {
    if (new Random().nextInt() == 1) foo(0);
  }

  //@ requires 2 * (i + 1) != 10;
  //@ ensures \result == 0;
  public static int foo(int i) {
    if (2 * (i + 1) == 10) {
      assert false;
      return 1;
    }
    return 0;
  }

  //@ ensures \result == (((i + 1) * 2 > 10) ? 1 : 0);
  public static int boo(float i) {
    if ((i + 1) * 2 > 10) return 1;
    return 0;
  }
}