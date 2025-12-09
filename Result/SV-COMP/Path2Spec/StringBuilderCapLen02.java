public class StringBuilderCapLen02 {
  //@ requires arg != null;
  //@ ensures \result == arg.equals("Diffblue  is leader in automatic test case generation");
  //@ also
  //@ requires arg == null;
  public static boolean f(String arg){
    StringBuilder buffer = new StringBuilder(arg);
    //@ assume buffer.toString().equals(arg);
    return buffer.toString().equals("Diffblue  is leader in automatic test case generation");
  }
}
