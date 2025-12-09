import java.util.Random;

class assert6 {
  //@ requires true;
  //@ ensures true;
  public static void func() {
    int i = new Random().nextInt();
    //@ assume i < 1000;
    if (i >= 1000) if (!(i >= 1000)) assert false;
  }
}
