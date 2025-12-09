import java.util.Random;

public class InfiniteLoop {

  /*@ 
    @ public normal_behavior
    @   requires true;
    @   assignable \nothing;
    @   ensures true;
    @ also
    @ public behavior
    @   requires true;
    @   diverges true;
    @*/
  public static void infiniteLoop(String[] arg) {
    int i = 0;
    boolean b = new Random().nextBoolean();

    //@ loop_invariant 0 <= i;
    while (b) {
      i++;
    }
  }

}