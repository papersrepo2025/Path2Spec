import java.util.Random;

class assert2 {
  //@ requires true;
  //@ ensures true;
  public static void main() {
    int i = new Random().nextInt();

    //@ assume i < 1000 || i > 1000;
    if (i >= 1000) assert i > 1000 : "i is greater 1000";
  }
}