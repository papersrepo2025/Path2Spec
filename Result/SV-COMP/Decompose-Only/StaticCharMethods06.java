public class StaticCharMethods06 {
  //@ requires arg != null;
  //@ ensures (arg.length() < 1) ==> \result == -1;
  //@ also
  //@ requires arg != null && arg.length() >= 1;
  //@ ensures \result == 1;
  //@ also
  //@ ensures \result == -1 || \result == 1;
  //@ assignable \nothing;
  public static int main(String arg) {
    if (arg.length() < 1) return -1;

    //@ assert arg.length() >= 1;
    char c = arg.charAt(0);
    Character c1 = c;
    Character c2 = c;
    //@ assert c1.equals(c2);

    if (c1.equals(c2)) {
      return 1;
    } else {
      return 0;
    }
  }
}