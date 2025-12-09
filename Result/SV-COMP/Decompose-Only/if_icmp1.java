class if_icmp1 {
  /*@
    @   requires i == j;
    @   ensures \result == false;
    @ also
    @   requires i > j;
    @   ensures \result == false;
    @ also
    @   requires i < j;
    @   ensures \result == true;
    @*/
  private static /*@ pure @*/ boolean f(int i, int j) {
    if (i == j) {
      return false;
    }
    if (i >= j) {
      return false;
    }
    if (2 > 1) {
      if (j > i) {
        return true;
      } else {
        return false;
      }
    }
    if (j <= i) {
      return false;
    }
    if (j < i) {
      return false;
    } else {
      return true;
    }
  }

  /*@
    @   requires i + 1 < 0;
    @   ensures \result == true;
    @ also
    @   requires !(i + 1 < 0);
    @   ensures \result == true;
    @*/
  public static /*@ pure @*/ boolean fun(int i) {
    if (i + 1 < 0) return true;
    return f(i, i + 1);
  }
}