class long1 {
  /*@ 
    @ ensures true;
    @ assignable \nothing;
    @*/
  public static boolean f() {
    long l = 4620693217682128896L;

    // conversions
    /*@ assume Integer.MIN_VALUE <= l && l <= Integer.MAX_VALUE; @*/
    int i = (int) l;
    /*@ assume 0 <= l && l <= Character.MAX_VALUE; @*/
    char c = (char) l;
    float f = l;
    double d = l;
    /*@ assume Short.MIN_VALUE <= l && l <= Short.MAX_VALUE; @*/
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