import java.util.Random;

class assert1 {
  //@ requires true;
  //@ assignable \everything;
  //@ ensures true;
  public static void func() {
    //@ assert true;
    int i = new Random().nextInt();

    if (i >= 10) {
      //@ assert i >= 10;
      assert i >= 10 : "my super assertion"; // should hold
    }

    if (i >= 20) {
      //@ assert i >= 10;
      assert i >= 10 : "my super assertion"; // should hold
    }
  }
}