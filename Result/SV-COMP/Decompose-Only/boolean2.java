import java.util.Random;

public class boolean2 {
  //@ ensures \result == true;
  public static boolean fun() {
    boolean b = new Random().nextBoolean();
    boolean result = f(b);
    return result == !b;
  }

  //@ requires b;
  //@ ensures \result == false;
  //@ also
  //@ requires !b;
  //@ ensures \result == true;
  //@ also
  //@ ensures \result == !\old(b);
  public static boolean f(boolean b) {
    return !b;
  }
}