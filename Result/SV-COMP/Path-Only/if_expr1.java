class if_expr1 {
  //@ requires x == 10;
  //@ ensures \result == true;
  //@ also
  //@ requires x != 10;
  //@ ensures \result == true;
  public static boolean main(int x) {
    int y = x == 10 ? 11 : 9;
    if (x == 10) return y == 11;
    else return y == 9;
  }
}
