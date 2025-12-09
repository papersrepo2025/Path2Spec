class ifxx1 {
  /*@ 
    @ requires true;
    @ ensures \result == true;
    @ assignable \nothing;
    @*/
  public static boolean f() {
    //@ assert true;
    int i = 0;
    if (i != 0) {
      return false;
    }
    //@ assert i == 0;
    i = 1;
    if (i == 0) {
      return false;
    }
    if (i < 0) {
      return false;
    }
    //@ assert i == 1;
    i = 0;
    if (i > 0) {
      return false;
    }
    //@ assert i == 0;
    i = 1;
    if (i <= 0) {
      return false;
    }
    //@ assert i == 1;
    i = -1;
    if (i >= 0) {
      return false;
    }
    return true;
  }
}