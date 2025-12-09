class assert3 {

  /*@ public normal_behavior
    @   assignable \nothing;
    @   ensures true;
    @*/
  public static void func() {
    int i = Verifier.nondetInt();

    if (i >= 1000)
      if (!(i > 1000))
        assert false;
  }
  
}