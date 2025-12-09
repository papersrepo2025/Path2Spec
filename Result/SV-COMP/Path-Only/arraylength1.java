class arraylength1 {
  //@ requires size < 0;
  //@ ensures \result == -1;
  //@ also
  //@ requires false;
  //@ ensures \result == 0;
  //@ also
  //@ requires size >= 0;
  //@ ensures \result == 1;
  public static int func(int size) {
    if (size < 0) return -1;
    int int_array[] = new int[size];

    if(int_array.length != size)
      return 0;
    return 1;
  }
}
