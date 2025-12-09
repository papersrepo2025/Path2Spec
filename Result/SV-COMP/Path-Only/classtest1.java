public class classtest1 {
  //@ requires true;
  //@ ensures true;
  //@ also
  //@ requires true; ensures true;
  public static void main(String[] args) {
    g(classtest1.class);
  }
  //@ requires true;
  //@ ensures true;
  //@ also
  //@ requires true; ensures true;
  static void g(Object c) {
    assert true;
  }
}
