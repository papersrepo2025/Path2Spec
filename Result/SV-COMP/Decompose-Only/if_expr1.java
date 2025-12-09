class if_expr1 {
  //@ requires x == 10;
  //@ ensures \result;
  //@ also
  //@ requires x != 10;
  //@ ensures \result;
  //@ also
  //@ ensures \result;
  public static boolean main(int x) {
    //@ assert x == 10 || x != 10;
    int y = x == 10 ? 11 : 9;
    //@ assert (x == 10 ==> y == 11) && (x != 10 ==> y == 9);
    if (x == 10) return y == 11;
    else return y == 9;
  }
}
;