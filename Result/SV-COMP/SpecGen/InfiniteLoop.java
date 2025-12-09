import java.util.Random;

public class InfiniteLoop {

  //@ assignable \nothing;
  //@ diverges true;
  //@ ensures true;
  public static void infiniteLoop(String[] arg) {
    int i = 0;
    boolean b = new Random().nextBoolean();

    //@ maintaining i >= 0;
 
    while (b) {
      i++;
    }
  }

}
