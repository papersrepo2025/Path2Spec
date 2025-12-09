class basic1 {
  //@ requires args != null;
  //@ ensures args == \old(args);
  public static void main(String[] args) {
    assert (System.out != null);
    //@ assert System.out != null;
    System.out.println("Hello World!");
    assert (System.err != null);
    //@ assert System.err != null;
    System.err.println("Hello World!");
    assert (System.in != null);
    //@ assert System.in != null;
    try {
      //@ assert System.in != null;
      int avail = System.in.available();
      //@ assert avail >= 0;
    } catch (java.io.IOException e) {
      //@ assert e != null;
    }
  }
}