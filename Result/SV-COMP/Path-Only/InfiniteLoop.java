import java.util.Random;

public class InfiniteLoop {
  //@ requires true;
  //@ ensures true;
  public static void infiniteLoop(String[] arg) {
    int i = 0;
    boolean b = new Random().nextBoolean();

    //@ assume false;
    while (b) {
      i++;
    }
  }

}
