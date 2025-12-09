import java.util.Random;

class return2 {
  //@ ensures true;
  public static boolean f() {
    int v1 = new Random().nextInt();
    int v2 = new Random().nextInt();
    return v1 == v2;
  }
}