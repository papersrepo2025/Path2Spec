class A {
  public static int i = 1;
}
;

class putstatic_getstatic1 {
  //@ requires A.i != 1;
  //@ assignable \nothing;
  //@ ensures \result == false && A.i == \old(A.i);
  //@ also
  //@ requires A.i == 1;
  //@ assignable A.i;
  //@ ensures \result == true && A.i == 999;
  //@ also
  //@ ensures (\result <==> \old(A.i) == 1) && A.i == (\old(A.i) == 1 ? 999 : \old(A.i));
  public static boolean f() {
    if(A.i != 1) return false;
    //@ assert A.i == 1;
    A.i = 999;
    if (A.i != 999)
      return false;
    return true;
  }
}