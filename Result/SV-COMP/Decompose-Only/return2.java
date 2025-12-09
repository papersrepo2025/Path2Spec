import java.util.Random;

class return2 {
  //@ requires true;
  //@ assignable \everything;
  //@ ensures \result == true || \result == false;
  public static boolean f() {
    //@ assert true;
    int v1 = new Random().nextInt();
    //@ assert true;
    int v2 = new Random().nextInt();
    //@ assert true;
    return v1 == v2;
  }
}