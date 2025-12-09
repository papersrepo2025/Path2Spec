class char1 {

  //@ requires arg != null;
  //@ assignable \nothing;
  //@ ensures (\old(arg.length()) < 1) ==> \result == -1;
  //@ ensures (\old(arg.length()) >= 1) ==> \result == 0;
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