import java.util.Random;

public class boolean2 {
  //@ requires true;
  //@ ensures \result;
  //@ also
  //@ ensures \result == true;
  public static boolean fun() {
    boolean b = new Random().nextBoolean();
    boolean result = f(b);
    return result == !b;
  }
  //@ requires true;
  //@ ensures \result == !b;
  //@ also
  //@ ensures \result == !b;
  //@ also
  //@ requires b == false;
  //@ ensures \result == true;
  public static boolean f(boolean b) {
    return !b;
  }
}
