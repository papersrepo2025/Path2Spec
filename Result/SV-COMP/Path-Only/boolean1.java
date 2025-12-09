import java.util.Random;

class boolean1 {
  //@ requires true;
  //@ ensures \result == true;
  public static boolean fun() {
    boolean my_boolean = new Random().nextBoolean();
    // Boolean shall be either true or false.
    if (my_boolean == true) return true;
    if (my_boolean == false) return true;

    return false;
  }
}
