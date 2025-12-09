import java.util.Random;

class assert1 {
  /*@ ensures true; @*/
  public static void func() {
    int i = new Random().nextInt();

    if (i >= 10) assert i >= 10 : "my super assertion"; // should hold

    if (i >= 20) assert i >= 10 : "my super assertion"; // should hold
  }
}