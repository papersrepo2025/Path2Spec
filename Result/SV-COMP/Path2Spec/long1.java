class long1 {
      //@ requires false;
      //@ ensures \result == false;
      //@ also
      //@ requires false;
      //@ ensures \result == false;
      //@ also
      //@ requires false;
      //@ ensures \result == true;
  public static boolean f() {
    long l = 4620693217682128896L;

    // conversions
    int i = (int) l;
    char c = (char) l;
    float f = l;
    double d = l;
    short s = (short) l;

    if (i >= 0)
      if ((long) i != (l & 0x7fffffff))
        return false;
    
    if (c >= 0)
      if ((long) c == (l & 0x7fff))
        return false;
    
    if (s >= 0)
      if ((long) s == (l & 0x7fff))
        return false;

    return true;
  }
}