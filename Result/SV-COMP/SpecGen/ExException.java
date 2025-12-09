public class ExException {
  //@ assignable \nothing;
  //@ ensures \result == 0;
  /*@ spec_public @*/ /*@ pure @*/ int zero() {
    return 0;
  }

  //@ requires secret > 0;
  //@ assignable \nothing;
  //@ ensures \result == 0;
  /*@ spec_public @*/ static int test(int secret) {
    /*@ nullable @*/ ExException o = null;
    if (secret > 0) {
      o = new ExException();
    } else assert false;
    //@ assert o != null;
    int i = o.zero();
    return i;
  }
}