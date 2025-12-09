public class StringBuilderCapLen01 {
  //@ requires true;
  //@ ensures true;
  public static void main(String[] args) {
    StringBuilder buffer =
        new StringBuilder("Diffblue is leader in automatic test case generation");

    //@ assume buffer != null;
    //@ assume buffer.toString().equals("Diffblue is leader in automatic test case generation");
    //@ assume buffer.length() == 52;
    //@ assume buffer.capacity() == 68;
    assert buffer.toString().equals("Diffblue is leader in automatic test case generation");
    assert buffer.length() == 52;
    assert buffer.capacity() == 68;

    buffer.ensureCapacity(75);
    //@ assume buffer.capacity() == 138;
    assert buffer.capacity() == 138;

    buffer.setLength(8);
    //@ assume buffer.length() == 8;
    //@ assume buffer.toString().equals("Diffblue");
    assert buffer.length() == 8;
    assert buffer.toString().equals("Diffblue");
  }
}