import java.util.Random;

class return2 {
  //@ public static ghost int r1;
  //@ public static ghost int r2;

  //@ requires true;
//@ ensures (r1 != r2) ==> \result == false;
  public static boolean f() {
    int v1 = new Random().nextInt();
    //@ set r1 = v1;
    int v2 = new Random().nextInt();
    //@ set r2 = v2;
    return v1 == v2;
  }
}