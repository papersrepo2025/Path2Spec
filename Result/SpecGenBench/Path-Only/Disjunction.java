
public class Disjunction {
    //@ requires b1 == true;
    //@ ensures \result == true;
    //@ also
    //@ requires b1 == false && b2 == true;
    //@ ensures \result == true;
    //@ also
    //@ requires b1 == false && b2 == false;
    //@ ensures \result == false;
    public static boolean disjunctOf(boolean b1, boolean b2) {
        if(b1 == true)
            return true;
        if(b2 == true)
            return true;
        return false;
    }
}
