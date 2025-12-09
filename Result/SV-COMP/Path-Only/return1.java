class Main {
  //@ requires true;
  //@ ensures \result == 1;
  //@ also
  //@ ensures \result == 1;
  public static short short_value() {
    short s = 1;
    return s;
  }
  //@ requires true;
  //@ ensures \result == true;
  //@ also
  //@ ensures \result == true;
  public static boolean bool_value() {
    boolean b = true;
    return b;
  }
  //@ requires true;
  //@ ensures \result == 0x10000ffffL;
  //@ also
  //@ ensures \result == 0x10000ffffL;
  public static long long_value() {
    long l = 0x10000ffffl;
    return l;
  }
  //@ requires true;
  //@ ensures \result == false;
  //@ also
  //@ requires false;
  //@ ensures \result == true;
  public static boolean f() {
    short s = short_value();
    if (s == 1)
      if (bool_value())
        if (long_value() == 0x10000ffffl)
          return false;
    return true;
  }
}
