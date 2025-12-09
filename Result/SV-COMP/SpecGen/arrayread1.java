class arrayread1 {

  /*@ spec_public @*/ /*@ nullable @*/ static arrayread1 readback;

  //@ assignable readback;
  //@ ensures (c != 1) ==> (\result == -1 && readback == \old(readback));
 
  public static int func(int c) {
    if (c != 1) return -1;
    arrayread1[] a = new arrayread1[c];
    readback = a[0];
    return ((readback == null) ? 1 : 0);
  }
}
