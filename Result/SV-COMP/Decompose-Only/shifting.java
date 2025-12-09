public class shifting {

      //@ public normal_behavior
      //@   requires i < 0;
      //@   ensures \result == -1;
      //@ also
      //@ public normal_behavior
      //@   requires i > 100;
      //@   ensures \result == -1;
      //@ also
      //@ public normal_behavior
      //@   requires 0 <= i && i <= 30;
      //@ assignable \nothing;
      //@ ensures \result == 1;
      //@ also
      //@ public normal_behavior
      //@   requires 31 <= i && i <= 62;
      //@ assignable \nothing;
      //@ ensures \result == 0;
      //@ also
      //@ public normal_behavior
      //@   requires i == 63;
      //@ assignable \nothing;
      //@ ensures \result == 1;
      //@ also
      //@ public normal_behavior
      //@   requires 64 <= i && i <= 94;
      //@ assignable \nothing;
      //@ ensures \result == 1;
      //@ also
      //@ public normal_behavior
      //@   requires 95 <= i && i <= 100;
      //@ assignable \nothing;
      //@ ensures \result == 0;
  public static int f(int i) {
    if (i < 0 || i > 100) {
      return -1;
    }

        //@ assume 0 <= i && i < 64;
    if ((1L << i) > Integer.MAX_VALUE) {
      return 0;
    }
    return 1;
  }
}