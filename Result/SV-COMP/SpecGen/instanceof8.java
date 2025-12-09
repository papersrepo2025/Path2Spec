class Main {
  //@ ensures \result <==> (i != null);
  //@ assignable \nothing;
  public static /*@ pure @*/ boolean test(/*@ nullable @*/ Integer i) {
    if (i instanceof Integer) {
      return true;
    } else {
      return false;
    }
  }

  //@ ensures \result;
  //@ assignable \nothing;
  public static /*@ pure @*/ boolean f() {
    return (!test(null)) && (test(new Integer(1)));
  }
}