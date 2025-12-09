class arraylength1 {
  //@ requires size < 0;
  //@ assignable \nothing;
  //@ ensures \result == -1;
  //@ also
  //@ requires size >= 0;
  //@ assignable \nothing;
  //@ ensures \result == 1;
  public static int func(int size) {
    if (size < 0) return -1;
    //@ assert size >= 0;
    int int_array[] = new int[size];
    //@ assert int_array.length == size;
    if(int_array.length != size)
      return 0;
    return 1;
  }
}