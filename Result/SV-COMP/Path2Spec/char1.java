class char1 {
  //@ requires arg != null && arg.length() < 1;
  //@ ensures \result == -1;
  //@ also
  //@ requires arg != null;
  //@ requires arg.length() >= 1;
  //@ requires !(arg.charAt(0) >= '\u0000' && arg.charAt(0) <= '\uffff');
  //@ ensures \result == 0;
  //@ also
  //@ requires false;
  //@ ensures \result == 1;
  public static int fun(String arg) {
    if (arg.length() < 1)
      return -1;
    char my_char = arg.charAt(0);
    int x = my_char;
    if (!(x >= 0 && x <= '\uffff'))
      return 0;

    my_char = '\uffff';
    my_char++;
    if (my_char == 0)
      return 0;
    return 1;
  }
  
}
