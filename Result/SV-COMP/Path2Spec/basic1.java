class basic1 {
  //@ requires System.out != null && System.err != null && System.in != null;
  //@ also
  //@ requires System.out != null;
  //@ requires System.err != null;
  //@ requires System.in != null;
  //@ ensures true;
  //@ also
  //@ requires System.out != null && System.err != null && System.in != null;
  //@ ensures true;
  //@ also
  //@ requires !basic1.class.desiredAssertionStatus() && System.out == null;
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
