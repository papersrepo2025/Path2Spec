import java.util.Random;

public class boolean2 {
  //@ ensures \result;
  public static boolean fun() {
    boolean b = new Random().nextBoolean();
    boolean result = f(b);
    return result == !b;
  }

  //@ ensures \result == !b;
  public static boolean f(boolean b) {
    return !b;
  }
}