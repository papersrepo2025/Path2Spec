public class CharSequenceToString {

  //@ requires arg != null;
  //@ assignable \nothing;
  //@ ensures \result;
  public static boolean main(String arg) {
    CharSequence cs = (CharSequence) arg;
    String s = cs.toString();
    int i = -1;
    if (s.equals("case1")) i = cs.length();
    return i == -1 || i == 5;
  }
  
}