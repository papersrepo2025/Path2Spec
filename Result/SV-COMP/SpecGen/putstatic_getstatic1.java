class A {
  /*@ spec_public @*/
  public static int i = 1;
}
;

class putstatic_getstatic1 {
  /*@ requires true;
    @ assignable A.i;
    @ ensures (\old(A.i) == 1 ==> (\result == true && A.i == 999)) &&
    @         (\old(A.i) != 1 ==> (\result == false && A.i == \old(A.i)));
    @*/
  public static boolean f() {
    if(A.i != 1) return false;
    A.i = 999;
    if (A.i != 999)
      return false;
    return true;
  }
}