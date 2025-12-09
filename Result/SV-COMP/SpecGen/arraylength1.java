class arraylength1 {
  //@ ensures \result == -1 <==> size < 0;
  //@ ensures \result == 1  <==> size >= 0;
  public /*@ spec_public @*/ static int func(int size) {
    if (size < 0) return -1;
    int int_array[] = new int[size];

    if(int_array.length != size)
      return 0;
    return 1;
  }
}