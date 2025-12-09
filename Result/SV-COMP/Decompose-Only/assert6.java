import java.util.Random;

class assert6 {
  //@ requires true;
  //@ ensures true;
  //@ signals (Exception e) false;
  public static void func() {
    //@ assert true;
    int i = new Random().nextInt();

    //@ assert !((i >= 1000) && !(i >= 1000));
    if (i >= 1000) if (!(i >= 1000)) assert false;
  }
}