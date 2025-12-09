class Main {
  //@ requires b == true;
  //@ ensures \result == true;
  //@ also
  //@ requires b == false;
  //@ ensures \result == false;
  //@ also
  //@ requires true;
  //@ ensures \result == b;
  public static boolean f(boolean b) {
    char[] diff = {'d', 'i', 'f', 'f'};
    char[] blue = {'b', 'l', 'u', 'e'};

    StringBuilder buffer = new StringBuilder();

    buffer.append(diff).append(blue);

    String tmp = buffer.toString();
    //@ assume tmp.equals("diffblue");
    if (b) return (tmp.equals("diffblue"));
    else return (!tmp.equals("diffblue"));
  }
}