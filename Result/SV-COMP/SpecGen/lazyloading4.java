class Problem {
  /*@ spec_public @*/ private static final Object[] DEFAULT = {};
  /*@ spec_public @*/ private Object data;

  //@ public static invariant DEFAULT != null;
  //@ public invariant data != null;

  /*@ public normal_behavior
    @ ensures data == DEFAULT && data != null;
    @ assignable \everything;
    @*/
  Problem() {
    this.data = DEFAULT;
  }

  /*@ public normal_behavior
    @ ensures \result == (data != null);
    @ assignable \nothing;
    @*/
  /*@ pure @*/ boolean checkInvariant() {
    return data != null;
  }
}

public class lazyloading4 {
  /*@ public normal_behavior
    @ ensures \result != null && \fresh(\result);
    @ assignable \nothing;
    @*/
  public static Problem Problem() { return new Problem(); }

  /*@ public normal_behavior
    @ ensures \result == true;
    @ assignable \nothing;
    @*/
  public static boolean f() {
    return Problem().checkInvariant();
  }
}