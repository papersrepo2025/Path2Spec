class ifxx1 {
  //@ ensures \result == true;
  public static boolean f() {
    int i = 0;
    if (i != 0) {
      return false;
    }
    i = 1;
    if (i == 0) {
      return false;
    }
    if (i < 0) {
      return false;
    }
    i = 0;
    if (i > 0) {
      return false;
    }
    i = 1;
    if (i <= 0) {
      return false;
    }
    i = -1;
    if (i >= 0) {
      return false;
    }
    return true;
  }
}