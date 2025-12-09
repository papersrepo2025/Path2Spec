class char1 {

      //@ requires arg != null;
      //@ requires arg.length() < 1;
      //@ ensures \result == -1;
      //@ also
      //@ requires arg != null;
      //@ requires !(arg.length() < 1);
      //@ assignable \nothing;
      //@ ensures \result == 0;
  public static int fun(String arg) {
    if (arg.length() < 1)
      return -1;
    char my_char = arg.charAt(0);
    int x = my_char;
        //@ assert x >= 0 && x <= '\uffff';
    if (!(x >= 0 && x <= '\uffff'))
      return 0;

    my_char = '\uffff';
        //@ assume my_char != '\uffff';
    my_char++;
        //@ assert my_char == 0;
    if (my_char == 0)
      return 0;
    return 1;
  }
  
}