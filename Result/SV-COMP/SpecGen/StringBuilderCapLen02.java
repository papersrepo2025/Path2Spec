public class StringBuilderCapLen02 {
  //@ requires arg != null;
  //@ assignable \nothing;
  //@ ensures \result <==> arg.equals("Diffblue  is leader in automatic test case generation");
  public static boolean f(String arg){
    StringBuilder buffer = new StringBuilder(arg);
    return buffer.toString().equals("Diffblue  is leader in automatic test case generation");
  }
}