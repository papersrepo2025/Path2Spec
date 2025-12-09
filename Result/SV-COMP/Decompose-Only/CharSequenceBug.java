public class CharSequenceBug {
  //@ requires s != null;
  //@ assignable \nothing;
  //@ ensures \result == false;
  public static boolean main(String s) {
    CharSequence target = "b";
    String replaced = "";
    if (target.length() == 1) replaced = s.replace('b', 'c');
    //@ assume replaced.indexOf('b') == -1;
    return replaced.indexOf('b') != -1;
  }
}