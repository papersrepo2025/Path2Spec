import java.util.Random;

public class ExMIT {
  /*@ requires true;
    @ ensures true;
    @*/
  public static void main(String[] args) {
    if (new Random().nextInt() == 1) foo(0);
  }

  /*@ requires 2 * (i + 1) != 10;
    @ ensures \result == 0;
    @*/
  public static int foo(int i) {
    if (2 * (i + 1) == 10) {
      assert false;
      return 1;
    }
    return 0;
  }

  /*@ requires i > 4;
    @ ensures \result == 1;
    @ also
    @ requires i <= 4;
    @ ensures \result == 0;
    @*/
  public static int boo(float i) {
    if ((i + 1) * 2 > 10) return 1;
    return 0;
  }
}