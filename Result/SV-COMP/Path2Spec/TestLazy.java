class Main {
  //@ requires arg == 0;
  //@ ensures \result == true;
  public static boolean g (int arg) {
    if (arg == 0)
      return (new Main()).f(0, arg);
    return true;
  }

  //@ requires a < 5;
  //@ ensures \result == true;
  public boolean f(int a, int b) {
    //@ nullable; Integer i = null;
    if (a < 5) {
      i = Integer.valueOf(4);
      //@ assert i != null;
      i.floatValue();
    } else
      return false;
    return true;
  }
}