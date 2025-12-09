class multinewarray {
  //@ ensures \result;
  public static boolean f() {
    int[][][] some_a = new int[4][3][2];

    int x = 3;
    int y = 5;
    int[][] int_array = new int[x][y];

    //@ assume int_array[2] != null;
    int_array[2][4] = 6;

    //@ assume int_array[2] != null;
    return int_array[2][4] == 6;
  }
}