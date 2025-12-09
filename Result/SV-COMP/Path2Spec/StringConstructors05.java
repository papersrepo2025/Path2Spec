public class StringConstructors05 {
  //@ ensures \result == "diffblue".equals(arg);
  //@ also
  //@ ensures arg == null ==> \result == false;
  //@ ensures arg != null && !arg.equals("diffblue") ==> \result == false;
  public static boolean f(String arg) {
    char[] charArray = {'d', 'i', 'f', 'f', 'b', 'l', 'u', 'e'};
    String s3 = new String(charArray);
    //@ assume s3.equals(arg) == "diffblue".equals(arg);
    return s3.equals(arg);
  }
}
