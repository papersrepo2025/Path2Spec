class ArrayWhatNot {

  class what_not {
  };
  //@ requires true;
  //@ ensures size < 8 ==> \result == -1;
  //@ ensures size >= 8 ==> \result == 1;
  //@ also
  //@ requires size < 8;
  //@ ensures \result == -1;
  //@ also
  //@ requires size >= 8;
  //@ ensures \result == 1;
  public static int func(int size) {
    if (size < 8) return -1;

    int int_array[] = new int[size];

    //@ decreases int_array.length - i;
    //@ maintaining 0 <= i && i <= int_array.length;
    //@ maintaining (\forall int j; 0 <= j && j < i; int_array[j] == j);
    for (int i = 0; i < size; i++)
      int_array[i] = i;

    if (int_array[7] != 7)
      return 0;

    what_not what_not_array[] = new what_not[size];

    if (what_not_array.length != size)
      return 0;
    return 1;
  }
}
