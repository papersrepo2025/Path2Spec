class ArrayWhatNot {

  /*@ spec_public @*/ class what_not {
  };

  //@ ensures size < 8 ==> \result == -1;
  //@ ensures size >= 8 ==> \result == 1;
  /*@ spec_public @*/ public static int func(int size) {
    if (size < 8) return -1;

    int int_array[] = new int[size];

    //@ maintaining 0 <= i && i <= size;
    //@ maintaining int_array.length == size;
    //@ maintaining (\forall int j; 0 <= j && j < i; int_array[j] == j);
    //@ decreases size - i;
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