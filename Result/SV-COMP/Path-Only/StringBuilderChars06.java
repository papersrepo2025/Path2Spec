public class StringBuilderChars06 {
  //@ requires arg != null;
  //@ ensures arg.equals("HiiffBlTe Limited") ==> \result == true;
  //@ also
  //@ requires arg != null;
  public static boolean f(String arg) {
    StringBuilder buffer = new StringBuilder(arg);
    buffer.reverse();
    //@ assume arg.equals("HiiffBlTe Limited") ==> buffer.toString().equals("detimiL eTlBffiiH");
    return buffer.toString().equals("detimiL eTlBffiiH");
  }
}
