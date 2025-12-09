class basic1 {
  //@ public normal_behavior
  //@   requires true;
  //@   assignable \nothing;
  //@   ensures true;
  public static void main(String[] args) {
    assert (System.out != null);
    System.out.println("Hello World!");
    assert (System.err != null);
    System.err.println("Hello World!");
    assert (System.in != null);
    try {
      int avail = System.in.available();
    } catch (java.io.IOException e) {
    }
  }
}