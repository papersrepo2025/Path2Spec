public class Disjunction {
    /*@ public normal_behavior
      @   ensures \result == (b1 || b2);
      @   assignable \nothing;
      @*/
    public static boolean disjunctOf(boolean b1, boolean b2) {
        if(b1 == true)
            return true;
        if(b2 == true)
            return true;
        return false;
    }
}