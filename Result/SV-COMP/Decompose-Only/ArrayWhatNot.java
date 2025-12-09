class ArrayWhatNot {

  class what_not {
  };

  //@ assignable \nothing;
  //@ requires size < 8;
  //@ ensures \result == -1;
  //@ also
  //@ requires size >= 8;
  //@ ensures \result == 1;
  public static int func(int size) {
    if (size < 8) return -1;

    //@ assert size >= 0;
    int int_array[] = new int[size];

    //@ maintaining 0 <= i && i <= size;
    //@ maintaining (\forall int k; 0 <= k && k < i; int_array[k] == k);
    //@ decreases size - i;
    for (int i = 0; i < size; i++)
      //@ assert 0 <= i && i < int_array.length;
      int_array[i] = i;

    //@ assert int_array.length == size;
    //@ assert int_array[7] == 7;
    if (int_array[7] != 7)
      return 0;

    //@ assert size >= 0;
    what_not what_not_array[] = new what_not[size];

    //@ assert what_not_array.length == size;
    if (what_not_array.length != size)
      return 0;
    return 1;
  }
}