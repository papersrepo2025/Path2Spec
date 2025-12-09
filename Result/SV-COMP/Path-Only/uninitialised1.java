class Main {
  //@ requires true;
  //@ ensures \result == true;
  public static boolean f() {
    int i[] = new int[10];
    //@ assert 0 <= 3 && 3 < i.length;
    //@ assume (\forall int k; 0 <= k && k < i.length; i[k] == 0);
    return i[3] == 0;
  }
}