public class StringConstructors03 {
  //@ requires arg0 != null;
  //@ ensures arg0.equals(arg1) ==> \result == true;
  //@ also
  //@ requires arg0 != null;
  //@ ensures !arg0.equals(arg1) ==> \result == false;
  public static boolean f(String arg0, String arg1) {
    String s = new String(arg0);
    String s2 = new String(s);
    //@ assume s.equals(arg0);
    //@ assume s2.equals(s);
    //@ assume (\forall String a, b, c; a != null && b != null && c != null && a.equals(b) && b.equals(c) ==> a.equals(c));
    return s2.equals(arg1);
  }
}
