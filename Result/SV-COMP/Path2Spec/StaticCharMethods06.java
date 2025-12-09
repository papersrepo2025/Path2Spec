public class StaticCharMethods06 {
  //@ requires arg != null && arg.length() == 0;
  //@ ensures \result == -1;
  //@ also
  //@ requires arg != null;
  //@ requires arg.length() >= 1;
  //@ ensures \result == 1;
  public static int main(String arg) {
    if (arg.length() < 1) return -1;

    char c = arg.charAt(0);
    Character c1 = c;
    Character c2 = c;

    if (c1.equals(c2)) {
      return 1;
    } else {
      return 0;
    }
  }
}
