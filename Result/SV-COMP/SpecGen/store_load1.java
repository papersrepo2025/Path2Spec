class store_load1 {
  //@ ensures \result;
  /*@ spec_public @*/ public static boolean f() {
    if (true) {
      int a0 = 0;
      int a1 = 1;
      int a2 = 2;
      int a3 = 3;
      int a4 = 4;
      if(0 != a0) return false;
      if(1 != a1) return false;
      if(2 != a2) return false;
      if(3 != a3) return false;
      if(4 != a4) return false;
    }
    if (true) {
      long a0 = 0L;
      long a1 = 1L;
      long a2 = 2L;
      long a3 = 3L;
      long a4 = 4L;
      if(0L != a0) return false;
      if(1L != a1) return false;
      if(2L != a2) return false;
      if(3L != a3) return false;
      if(4L != a4) return false;
    }
    if (true) {
      double a0 = 0.0;
      double a1 = 1.0;
      double a2 = 2.0;
      double a3 = 3.0;
      double a4 = 4.0;
      if(0.0 != a0) return false;
      if(1.0 != a1) return false;
      if(2.0 != a2) return false;
      if(3.0 != a3) return false;
      if(4.0 != a4) return false;
    }
    if (true) {
      float a0 = 0.5f;
      float a1 = 1.0f;
      float a2 = 2.0f;
      float a3 = 3.0f;
      float a4 = 4.0f;
      if (0.5f != a0)
        return false;
      if (1.0f != a1)
        return false;
      if (2.0f != a2)
        return false;
      if (3.0f != a3)
        return false;
      if (4.0f != a4)
        return false;
    }
    return true;
  }
}