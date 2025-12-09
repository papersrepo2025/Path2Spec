class assert4 {
  /*@ ensures (i >= 20) ==> \result == 1;
    @ ensures (10 <= i && i < 20) ==> \result == 0;
    @ ensures (i < 10) ==> \result == -1;
    @ ensures \result == -1 || \result == 0 || \result == 1;
    @*/
  public static int func(int i) {
    if (i >= 10)
      return (i >= 20) ? 1 : 0;
    else
      return -1;
  }
}