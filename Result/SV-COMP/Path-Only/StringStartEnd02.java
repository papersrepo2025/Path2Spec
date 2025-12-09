public class StringStartEnd02 {

  //@ requires args != null && args.length < 4;
  //@ ensures \result == false;
  public static boolean f(String[] args) {
    if (args.length < 4)
      return false;
    int i = 0;

    for (int j = 0; j < 4; j++) {
      if (args[j].startsWith("te"))
        ++i;
    }

    return i == 1;
  }
  
}