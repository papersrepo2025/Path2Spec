
public class Conjunction {
    //@ requires true;
    //@ ensures \result == false <==> !b1 || !b2;
    //@ also
    //@ requires b1 == true && b2 == false;
    //@ ensures \result == false;
    //@ also
    //@ requires b1 == true && b2 == true;
    //@ ensures \result == true;
    public static boolean conjunctOf(boolean b1, boolean b2) {
        if(b1 == false)
            return false;
        if(b2 == false)
            return false;
        return true;
    }
}
