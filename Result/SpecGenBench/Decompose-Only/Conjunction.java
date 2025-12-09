public class Conjunction {
        //@ requires b1 != false && b2 == false;
        //@ assignable \nothing;
        //@ ensures \result == false;
        //@ also
        //@ requires b1 == false;
        //@ assignable \nothing;
        //@ ensures \result == false;
        //@ also
        //@ requires b1 != false && b2 != false;
        //@ assignable \nothing;
        //@ ensures \result == true;
    public static boolean conjunctOf(boolean b1, boolean b2) {
        if(b1 == false)
            return false;
        if(b2 == false)
            return false;
        return true;
    }
}