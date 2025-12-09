import java.util.Random;

class assert6 {
  //@ ensures true;
  public static void func() {
    int i = new Random().nextInt();

    if (i >= 1000) if (!(i >= 1000)) assert false;
  }
}