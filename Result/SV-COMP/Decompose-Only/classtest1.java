public class classtest1 {

  //@ requires args == null;
  //@ assignable \nothing;
  //@ ensures true;
  //@ also
  //@ requires args != null;
  //@ assignable \nothing;
  //@ ensures true;
  public static void main(String[] args) {
    g(classtest1.class);
  }

  //@ requires c == null;
  //@ assignable \nothing;
  //@ ensures true;
  //@ also
  //@ requires c != null;
  //@ assignable \nothing;
  //@ ensures true;
  static void g(Object c) {
    assert true;
  }
}