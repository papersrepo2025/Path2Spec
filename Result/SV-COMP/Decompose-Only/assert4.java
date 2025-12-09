class assert4 {
  //@ requires i >= 20;
  //@ ensures \result == 1;
  //@ also
  //@ requires 10 <= i && i < 20;
  //@ ensures \result == 0;
  //@ also
  //@ requires i < 10;
  //@ ensures \result == -1;
  //@ also
  //@ ensures \result == ((i >= 20) ? 1 : ((i >= 10) ? 0 : -1));
  public static int func(int i) {
    if (i >= 10)
      return (i >= 20) ? 1 : 0;
    else
      return -1;
  }
}