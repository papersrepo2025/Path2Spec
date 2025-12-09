public class StringStartEnd01 {
  //@ requires args != null;
//@ ensures true;
  public static void main(String[] args) {
    String[] strings = {"tested", "testing", "passed", "passing"};

    int i = 0;
    for (String string : strings) {
      if (string.startsWith("te")) ++i;
    }
    //@ assume i == 2;
    assert i == 2;

    i = 0;
    for (String string : strings) {
      if (string.startsWith("ste", 2)) ++i;
    }
    //@ assume i == 1;
    assert i == 1;

    i = 0;
    for (String string : strings) {
      if (string.endsWith("ed")) ++i;
    }
    //@ assume i == 2;
    assert i == 2;
  }
}