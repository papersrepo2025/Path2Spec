public class classtest1 {
  //@ ensures true;
  public static void main(String[] args) {
    g(classtest1.class);
  }

  //@ ensures true;
  static void g(Object c) {
    assert true;
  }
}