public class Conjunction {
    /*@ 
      public normal_behavior
      requires true;
      ensures \result == (b1 && b2);
      assignable \nothing;
    @*/
    public static /*@ pure @*/ boolean conjunctOf(boolean b1, boolean b2) {
        if(b1 == false)
            return false;
        if(b2 == false)
            return false;
        return true;
    }
}