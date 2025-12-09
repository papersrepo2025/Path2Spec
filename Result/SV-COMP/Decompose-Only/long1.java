class long1 {
      //@ public normal_behavior
      //@   requires true;
      //@ assignable \nothing;
      //@ ensures \result == true;
  public static boolean f() {
        //@ assert Long.MIN_VALUE <= 4620693217682128896L && 4620693217682128896L <= Long.MAX_VALUE;
    long l = 4620693217682128896L;

    // conversions
        //@ assert true;
        //@ assume Integer.MIN_VALUE <= l && l <= Integer.MAX_VALUE;
    int i = (int) l;
        //@ assume 0L <= l && l <= 65535L;
    char c = (char) l;
    float f = l;
    double d = l;
        //@ assume -32768L <= l && l <= 32767L;
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